package org.gameoflife.frontend.swing.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleGameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameCreatedListener;
import org.gameoflife.controller.listener.GameStartedListener;
import org.gameoflife.frontend.swing.BoardRenderer;
import org.gameoflife.frontend.swing.CellRenderer;
import org.gameoflife.frontend.swing.impl.component.FreezableRepaintManager;

public class PanelBoardRenderer extends AbstractProvidesComponent implements BoardRenderer, GameCreatedListener, GameStartedListener, GameBoardChangedListener {

    private static final Dimension STANDARD_PREFERRED_SIZE = new Dimension(600, 600);

    private final JPanel board;
    private final List<List<CellRenderer>> cellRenderer;

    private GameController gameController;
    
    public PanelBoardRenderer(GameController gameController) {
        this.gameController = gameController;
        this.gameController.registerListener(this);
        
        board = new JPanel();
        board.setPreferredSize(STANDARD_PREFERRED_SIZE);
        cellRenderer = new ArrayList<>();
    }
    
    @Override
    public void newGameHasBeenCreated(GameBoardDTO newBoardDTO) {
        initializeBoard(newBoardDTO);
        applyBoard(newBoardDTO);
    }
    
    @Override
    public void gameHasStarted() {
        gameController.applyGameBoardDTO(getGameBoardDTO());
    }

    private GameBoardDTO getGameBoardDTO() {
        List<List<CellDTO>> board = new ArrayList<>();
        for (List<CellRenderer> cellRendererRow : this.cellRenderer) {
            ArrayList<CellDTO> row = new ArrayList<>();
            board.add(row);
            for (CellRenderer cellRenderer : cellRendererRow) {
                row.add(cellRenderer.getCellDTO());
            }
        }
        
        return new SimpleGameBoardDTO(board);
    }

    @Override
    public void gameBoardHasChanged(GameBoardDTO boardDTO) {
        applyBoard(boardDTO);
    }

    private void applyBoard(GameBoardDTO boardDTO) {
        disableRendering();
        for (int heightIndex = 0; heightIndex < boardDTO.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < boardDTO.getWidth(); widthIndex++) {
                getCellRenderer(widthIndex, heightIndex).setState(boardDTO.getCellDTOState(widthIndex, heightIndex));
            }
        }
        enableRendering();
    }

    private void disableRendering() {
        RepaintManager repaintManager = getRepaintManager();
        if (repaintManager instanceof FreezableRepaintManager) {
            ((FreezableRepaintManager) repaintManager).freeze(board);
        }
    }

    private void enableRendering() {
        RepaintManager repaintManager = getRepaintManager();
        if (repaintManager instanceof FreezableRepaintManager) {
            ((FreezableRepaintManager) repaintManager).thaw(board);
        }
    }

    private RepaintManager getRepaintManager() {
        return RepaintManager.currentManager(getComponent());
    }

    private CellRenderer getCellRenderer(int widthIndex, int heightIndex) {
        return cellRenderer.get(heightIndex).get(widthIndex);
    }

    private void initializeBoard(GameBoardDTO boardDTO) {
        board.removeAll();
        GridLayout layout = new GridLayout(boardDTO.getHeight(), boardDTO.getWidth(), 0, 0);
        board.setLayout(layout);
        
        this.cellRenderer.clear();
        
        for (int heightIndex = 0; heightIndex < boardDTO.getHeight(); heightIndex++) {
            this.cellRenderer.add(new ArrayList<CellRenderer>());
            for (int widthIndex = 0; widthIndex < boardDTO.getWidth(); widthIndex++) {
				CellRenderer cellRenderer = new PanelCellRenderer(CellState.DEAD, gameController);
                board.add(cellRenderer.getComponent());
                this.cellRenderer.get(heightIndex).add(cellRenderer);
            }
        }
    }

    @Override
    public Component getComponent() {
        return board;
    }

}
