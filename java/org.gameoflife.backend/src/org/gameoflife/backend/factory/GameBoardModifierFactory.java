package org.gameoflife.backend.factory;

import org.gameoflife.backend.CellState;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.impl.FixStateInitialGenerationCreator;

public class GameBoardModifierFactory {
    
    public static InitialGenerationCreator createFixStateInitialGenerationCreator(int width, int height, CellState initialState) {
        return new FixStateInitialGenerationCreator(width, height, CellState.DEAD);
    }

}
