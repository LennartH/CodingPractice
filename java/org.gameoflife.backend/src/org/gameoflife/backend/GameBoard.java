package org.gameoflife.backend;

import org.gameoflife.backend.shared.dto.GameBoardDTO;

public interface GameBoard extends HasDTO<GameBoardDTO> {
    
    public void evolve();

    Cell[][] getCells();

	public int getHeight();
	public int getWidth();

}
