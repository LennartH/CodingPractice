package org.gameoflife.controller;

import org.gameoflife.backend.shared.dto.GameBoardDTO;

public interface ControllerListenerRegistry extends ListenerRegistry {

    public void informGameHasBeenCreated(GameBoardDTO newBoardDTO);

    public void informGameHasStarted();

    public void informGameBoardHasChanged(GameBoardDTO newBoardDTO);

}
