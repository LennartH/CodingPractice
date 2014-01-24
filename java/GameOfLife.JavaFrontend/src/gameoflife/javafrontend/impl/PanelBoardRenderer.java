package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.BoardRenderer;
import gameoflife.javafrontend.CellRenderer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import org.gameoflife.backend.shared.GameBoardDTO;
import org.gameoflife.controller.GameController;
import org.gameoflife.controller.listener.GameStartedListener;
import org.gameoflife.controller.listener.NewGameCreatedListener;

public class PanelBoardRenderer implements BoardRenderer, NewGameCreatedListener, GameStartedListener {

    private static final Dimension STANDARD_PREFERRED_SIZE = new Dimension(600, 600);

    private final GameController gameController;
    
    private final JPanel board;
    private final Set<CellRenderer> cellRenderer;
    
    public PanelBoardRenderer(GameController gameController) {
        this.gameController = gameController;
        startListeningToGameController();
        board = new JPanel();
        cellRenderer = new HashSet<>();
    }
    
    private void startListeningToGameController() {
        gameController.addNewGameCreatedListener(this);
        gameController.addGameStartedListener(this);
    }

    @Override
    public void newGameHasBeenCreated(GameBoardDTO boardDTO) {
        board.setPreferredSize(STANDARD_PREFERRED_SIZE);
        GridLayout layout = new GridLayout(boardDTO.getHeight(), boardDTO.getWidth(), 0, 0);
        board.setLayout(layout);
        
        this.cellRenderer.clear();
        
        for (int heightIndex = 0; heightIndex < boardDTO.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < boardDTO.getWidth(); widthIndex++) {
                CellRenderer cellRenderer = new PanelCellRenderer();
                board.add(cellRenderer.getComponent());
                this.cellRenderer.add(cellRenderer);
            }
        }
    }
    
    @Override
    public void gameHasStarted() {
        // TODO Forward the event to the cell renderer
        
    }

    @Override
    public Component getComponent() {
        return board;
    }

}
