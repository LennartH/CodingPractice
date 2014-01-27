package org.gameoflife.backend.shared.dto;

import java.io.Serializable;

public interface GameBoardDTO extends Serializable {

	public CellDTO getCellDTO(int widthIndex, int heightIndex);
	
    public int getHeight();
    public int getWidth();

}
