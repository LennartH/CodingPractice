package org.gameoflife.backend;

import org.gameoflife.backend.shared.CellDTO;
import org.gameoflife.backend.shared.CellState;

public interface Cell extends HasDTO<CellDTO> {

	public CellState getState();

}
