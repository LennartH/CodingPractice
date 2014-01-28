package org.gameoflife.backend.shared.dto;

import java.io.Serializable;

import org.gameoflife.backend.shared.CellState;

public interface GameBoardDTO extends Serializable {

	public CellDTO getCellDTO(int widthIndex, int heightIndex);
    public CellState getCellDTOState(int widthIndex, int heightIndex);
	
    public int getHeight();
    public int getWidth();

}
