package org.gameoflife.controller.listener;

import org.gameoflife.backend.shared.dto.GameBoardDTO;

public interface GameCreatedListener {
    
    public void newGameHasBeenCreated(GameBoardDTO newBoardDTO);

}
