package org.gameoflife.backend;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.GameBoardDTO;

public interface GameBoard extends HasDTO<GameBoardDTO> {

    Cell[][] getCells();

    public int getHeight();
    public int getWidth();
    
    public void evolve();

    public void setCellState(int widthIndex, int heightIndex, CellState state);

}
