package gameoflife.javafrontend.impl;

import gameoflife.javafrontend.BoardRenderer;
import gameoflife.javafrontend.CellRenderer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.gameoflife.backend.shared.GameBoardDTO;
import org.gameoflife.controller.GameController;

public class PanelBoardRenderer implements BoardRenderer {

    private static final Dimension STANDARD_PREFERRED_SIZE = new Dimension(600, 600);

    private GameController gameController;
    
    private final JPanel board;
    
    public PanelBoardRenderer(GameController gameController) {
        this.gameController = gameController;
        
        board = createBoard(gameController.getBoardDTO());
    }

    private JPanel createBoard(GameBoardDTO gameBoardDTO) {
        JPanel board = new JPanel();
        board.setPreferredSize(STANDARD_PREFERRED_SIZE);
        GridLayout layout = new GridLayout(gameBoardDTO.getHeight(), gameBoardDTO.getWidth(), 0, 0);
        board.setLayout(layout);
        
        for (int heightIndex = 0; heightIndex < gameBoardDTO.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < gameBoardDTO.getWidth(); widthIndex++) {
                CellRenderer cellRenderer = new PanelCellRenderer();
                board.add(cellRenderer.getComponent());
            }
        }
        
        return board;
    }

    @Override
    public Component getComponent() {
        return board;
    }

}
