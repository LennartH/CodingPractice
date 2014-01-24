package org.gameoflife.controller.listener;

import org.gameoflife.backend.shared.GameBoardDTO;

public interface NewGameCreatedListener {
    
    public void newGameHasBeenCreated(GameBoardDTO boardDTO);

}
