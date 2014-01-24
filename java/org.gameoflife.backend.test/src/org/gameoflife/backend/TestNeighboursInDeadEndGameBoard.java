package org.gameoflife.backend;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.gameoflife.backend.impl.DeadEndGameBoard;
import org.gameoflife.backend.impl.FixStateInitialGenerationCreator;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.test.util.NoRuleApplier;
import org.gameoflife.backend.test.util.OpenDeadEndGameBoard;
import org.gameoflife.backend.test.util.Util;
import org.junit.Test;

public class TestNeighboursInDeadEndGameBoard {

	@Test
	public void testBoardInitialization() {
		RuleApplier ruleApplier = new NoRuleApplier();
		InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(50, 50, CellState.ALIVE);
		GameBoard board = new DeadEndGameBoard(ruleApplier, initialGenerationCreator);

		assertThat(board.getHeight(), is(50));
		assertThat(board.getWidth(), is(50));
	}
    
    @Test
    public void testNeighboursOnCorners() {
        RuleApplier ruleApplier = new NoRuleApplier();
        InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(50, 50, CellState.ALIVE);
        OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(ruleApplier, initialGenerationCreator);
        
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(board.getNeighbours(0, 0), 5, 3);
    }
    
    @Test
    public void testNeighboursOnEdges() {
        RuleApplier ruleApplier = new NoRuleApplier();
        InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(50, 50, CellState.ALIVE);
        OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(ruleApplier, initialGenerationCreator);
        
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(board.getNeighbours(0, 7), 3, 5);
    }
    
    @Test
    public void testNeighboursInBounds() {
        RuleApplier ruleApplier = new NoRuleApplier();
        InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(50, 50, CellState.ALIVE);
        OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(ruleApplier, initialGenerationCreator);
        
        Util.assertThatAmountOfDeadAndAliveCellsIsCorrect(board.getNeighbours(1, 1), 0, 8);
    }

}
