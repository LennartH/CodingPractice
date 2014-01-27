package org.gameoflife.controller.listener;

import org.gameoflife.backend.shared.dto.GameBoardDTO;

public interface GameBoardChangedListener {
    
    public void gameBoardHasChanged(GameBoardDTO newBoardDTO);

}
