package org.gameoflife.backend.shared;

import java.io.Serializable;

public interface GameBoardDTO extends Serializable {

    public int getHeight();
    public int getWidth();

}
