package org.gameoflife.backend.factory;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.impl.DeadEndGameBoard;

public class GameBoardFactory {
    
    public static GameBoard createDeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
        return new DeadEndGameBoard(ruleApplier, initialGenerationCreator);
    }

}
