package org.gameoflife.backend.factory;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.impl.FixStateInitialGenerationCreator;
import org.gameoflife.backend.impl.modifier.ApplyDTOToBoardModifier;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.GameBoardDTO;

public class GameBoardModifierFactory {

    public static InitialGenerationCreator createFixStateInitialGenerationCreator(int width, int height,
            CellState initialState) {
        return new FixStateInitialGenerationCreator(width, height, initialState);
    }

    public static ApplyDTOToBoardModifier createApplyDTOToBoardModifier(GameBoardDTO boardDTOToApply,
            GameBoard boardToModify) throws IllegalArgumentException {
        return new ApplyDTOToBoardModifier(boardDTOToApply, boardToModify);
    }

}
