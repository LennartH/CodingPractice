package org.gameoflife.backend.shared.dto;

import java.io.Serializable;

import org.gameoflife.backend.shared.CellState;

public interface CellDTO extends Serializable {

    public abstract CellState getState();

}
