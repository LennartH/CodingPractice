package gameoflife.test;

import gameoflife.CellState;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleApplier;
import gameoflife.impl.StandardRuleApplier;
import gameoflife.test.util.FixStateInitialGenerationCreator;
import gameoflife.test.util.OpenDeadEndGameBoard;
import gameoflife.test.util.Util;

import org.junit.Test;

public class TestEvolutionInDeadEndGameBoardTest {

    @Test
    public void testNextGenerationOfDeadBoard() {
        RuleApplier standardRules = new StandardRuleApplier();
        InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(25, 25, CellState.DEAD);
        OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(standardRules, initialGenerationCreator);
        
        board.evolve();
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(board.getCellsAsFlatCollection(), board.getWidth() * board.getHeight(), 0);
    }

    @Test
    public void testNextGenerationOfBoardWithASingleLivingCell() {
        RuleApplier standardRules = new StandardRuleApplier();
        InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(25, 25, CellState.DEAD);
        OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(standardRules, initialGenerationCreator);
        board.setCellState(0, 0, CellState.ALIVE);
        
        board.evolve();
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(board.getCellsAsFlatCollection(), board.getWidth() * board.getHeight(), 0);
    }

    @Test
    public void testNextGenerationOfBoardWithABlockOfLivingCells() {
        RuleApplier standardRules = new StandardRuleApplier();
        InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(25, 25, CellState.DEAD);
        OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(standardRules, initialGenerationCreator);
        setCellBlockToState(board, 3, 3, 3, 3, CellState.ALIVE);
        
        board.evolve();
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(board.getCellsAsFlatCollection(), board.getWidth() * board.getHeight() - 4, 4);
    }

    private void setCellBlockToState(OpenDeadEndGameBoard board, int leftUpperWidthIndex, int leftUpperHeightIndex, int width, int height, CellState state) {
        for (int heightIndex = leftUpperHeightIndex; heightIndex <= leftUpperHeightIndex + height; heightIndex++) {
            for (int widthIndex = leftUpperWidthIndex; widthIndex < leftUpperWidthIndex + width; widthIndex++) {
                board.setCellState(widthIndex, heightIndex, state);
            }
        }
    }

}
