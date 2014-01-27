package org.gameoflife.controller;

import org.gameoflife.backend.shared.dto.GameBoardDTO;

public interface ControllerListenerRegistry extends ListenerRegistry {

    public void notifyGameHasBeenCreated(GameBoardDTO newBoardDTO);

    public void notifyGameHasStarted();

    public void notifyGameBoardHasChanged(GameBoardDTO newBoardDTO);

}
