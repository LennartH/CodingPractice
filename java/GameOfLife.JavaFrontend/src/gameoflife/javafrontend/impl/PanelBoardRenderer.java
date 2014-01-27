package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.BoardRenderer;
import gameoflife.javafrontend.CellRenderer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameBoardChangedListener;
import org.gameoflife.controller.listener.GameStartedListener;

public class PanelBoardRenderer implements BoardRenderer, GameBoardChangedListener, GameStartedListener {

    private static final Dimension STANDARD_PREFERRED_SIZE = new Dimension(600, 600);

    private final JPanel board;
    private final List<List<CellRenderer>> cellRenderer;

    private boolean gameStarted;
    
    public PanelBoardRenderer(GameController gameController) {
        gameController.registerListener(this);
        
        board = new JPanel();
        cellRenderer = new ArrayList<>();
    }
    
    @Override
    public void gameBoardHasChanged(GameBoardDTO boardDTO) {
        if (isGameStarted()) {
            initializeBoard(boardDTO);
        }
        applyBoard(boardDTO);
    }

    private boolean isGameStarted() {
        return gameStarted;
    }

    private void applyBoard(GameBoardDTO boardDTO) {
        for (int heightIndex = 0; heightIndex < boardDTO.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < boardDTO.getWidth(); widthIndex++) {
                CellState newState = boardDTO.getCellDTO(widthIndex, heightIndex).getState();
                getCellRenderer(widthIndex, heightIndex).setState(newState);
            }
        }
    }

    private CellRenderer getCellRenderer(int widthIndex, int heightIndex) {
        return cellRenderer.get(heightIndex).get(widthIndex);
    }

    private void initializeBoard(GameBoardDTO boardDTO) {
        board.setPreferredSize(STANDARD_PREFERRED_SIZE);
        GridLayout layout = new GridLayout(boardDTO.getHeight(), boardDTO.getWidth(), 0, 0);
        board.setLayout(layout);
        
        this.cellRenderer.clear();
        
        for (int heightIndex = 0; heightIndex < boardDTO.getHeight(); heightIndex++) {
            this.cellRenderer.add(new ArrayList<CellRenderer>());
            for (int widthIndex = 0; widthIndex < boardDTO.getWidth(); widthIndex++) {
				CellRenderer cellRenderer = new PanelCellRenderer();
                board.add(cellRenderer.getComponent());
                this.cellRenderer.get(heightIndex).add(cellRenderer);
            }
        }
    }
    
    @Override
    public void gameHasStarted() {
        gameStarted = true;
    }

    @Override
    public Component getComponent() {
        return board;
    }

}
