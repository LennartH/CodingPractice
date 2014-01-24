package gameoflife.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import gameoflife.Cell;
import gameoflife.CellState;
import gameoflife.GameBoard;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleChecker;
import gameoflife.impl.DeadEndGameBoard;
import gameoflife.impl.SimpleCell;
import gameoflife.test.util.FixStateInitialGenerationCreator;
import gameoflife.test.util.NoRuleApplier;
import gameoflife.test.util.OpenDeadEndGameBoard;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class TestDeadEndGameBoard {

	@Test
	public void testBoardInitialization() {
		RuleChecker ruleApplier = new NoRuleApplier();
		InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(50, 50, CellState.ALIVE);
		GameBoard board = new DeadEndGameBoard(ruleApplier, initialGenerationCreator);

		assertThat(board.getHeight(), is(50));
		assertThat(board.getWidth(), is(50));
	}
	
	@Test
	public void testNeighbourSelection() {
		RuleChecker ruleApplier = new NoRuleApplier();
		InitialGenerationCreator initialGenerationCreator = new FixStateInitialGenerationCreator(50, 50, CellState.ALIVE);
		OpenDeadEndGameBoard board = new OpenDeadEndGameBoard(ruleApplier, initialGenerationCreator);
		
		Collection<Cell> expectedNeighbours = createExpectedNeighboursForLeftUpperCorner();
		assertThat(board.getNeighbours(0, 0), is(expectedNeighbours));
	}

	private Collection<Cell> createExpectedNeighboursForLeftUpperCorner() {
		Collection<Cell> neighbours = new ArrayList<>();
		neighbours.add(new SimpleCell(CellState.DEAD));
		neighbours.add(new SimpleCell(CellState.DEAD));
		neighbours.add(new SimpleCell(CellState.DEAD));
		neighbours.add(new SimpleCell(CellState.ALIVE));
		neighbours.add(new SimpleCell(CellState.ALIVE));
		neighbours.add(new SimpleCell(CellState.ALIVE));
		neighbours.add(new SimpleCell(CellState.DEAD));
		neighbours.add(new SimpleCell(CellState.DEAD));
		return neighbours;
	}

}
