package org.gameoflife.backend.impl.modifier;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.GameBoardModifier;
import org.gameoflife.backend.factory.GameBoardFactory;
import org.gameoflife.backend.factory.GameBoardModifierFactory;
import org.gameoflife.backend.factory.RuleFactory;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.backend.test.util.Util;
import org.junit.Test;

public class TestApplyDTOToBoardModifier {

    @Test(expected = IllegalArgumentException.class)
    public void testThatBoardSizesHaveToMatch() {
        GameBoardDTO boardDTOToApply = GameBoardFactory.createDeadEndGameBoard(RuleFactory.createStandardRuleSet(),
                GameBoardModifierFactory.createFixStateInitialGenerationCreator(5, 5, CellState.ALIVE)).asDTO();
        GameBoard boardToModify = GameBoardFactory.createStandardDeadEndGameBoard(25, 25);
        GameBoardModifierFactory.createApplyDTOToBoardModifier(boardDTOToApply, boardToModify);
    }

    @Test
    public void testApplyDTOToBoardModifier() {
        GameBoardDTO boardDTOToApply = GameBoardFactory.createDeadEndGameBoard(RuleFactory.createStandardRuleSet(),
                GameBoardModifierFactory.createFixStateInitialGenerationCreator(5, 5, CellState.ALIVE)).asDTO();
        GameBoard boardToModify = GameBoardFactory.createStandardDeadEndGameBoard(5, 5);

        GameBoardModifier applyDTOToBoardModifier = GameBoardModifierFactory.createApplyDTOToBoardModifier(boardDTOToApply, boardToModify);
        applyDTOToBoardModifier.applyModifications();
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(boardToModify, 0,
                boardToModify.getWidth() * boardToModify.getHeight());
    }

}
