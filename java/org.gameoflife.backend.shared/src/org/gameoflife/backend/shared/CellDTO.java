package org.gameoflife.backend.shared;

import java.io.Serializable;

public interface CellDTO extends Serializable {

    public abstract CellState getState();

}
