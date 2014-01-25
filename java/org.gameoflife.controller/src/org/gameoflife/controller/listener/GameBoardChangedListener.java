package org.gameoflife.controller.listener;

import org.gameoflife.backend.shared.GameBoardDTO;

public interface GameBoardChangedListener {
    
    public void gameBoardHasChanged(GameBoardDTO newBoardDTO);

}
