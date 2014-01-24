package org.gameoflife.controller.listener;

import org.gameoflife.backend.shared.GameBoardDTO;

public interface NewGameCreatedListener {
    
    public void createdNewGame(GameBoardDTO boardDTO);

}
