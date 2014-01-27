package org.gameoflife.backend.factory;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.impl.DeadEndGameBoard;
import org.gameoflife.backend.shared.CellState;

public class GameBoardFactory {
    
    public static GameBoard createStandardDeadEndGameBoard(int width, int height) {
        RuleApplier standardRules = RuleFactory.createStandardRuleSet();
        InitialGenerationCreator initialGenerationCreator = GameBoardModifierFactory.createFixStateInitialGenerationCreator(width, height, CellState.DEAD);
        return createDeadEndGameBoard(standardRules, initialGenerationCreator);
    }
    
    public static GameBoard createDeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
        return new DeadEndGameBoard(ruleApplier, initialGenerationCreator);
    }

}
