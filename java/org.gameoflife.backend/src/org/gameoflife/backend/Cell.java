package org.gameoflife.backend;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;

public interface Cell extends HasDTO<CellDTO> {

	public CellState getState();

}
