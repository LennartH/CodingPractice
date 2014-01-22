package gameoflife.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import gameoflife.Cell;
import gameoflife.CellState;
import gameoflife.RuleChecker;
import gameoflife.impl.ResurrectionRuleChecker;
import gameoflife.impl.SimpleCell;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class TestRuleChecker {

	@Test
	public void testResurrectionRuleChecker() {
		RuleChecker resurrectionRuleChecker = new ResurrectionRuleChecker();
		Cell cell = new SimpleCell(CellState.DEAD);
		
		for (int livingCells = 0; livingCells <= 8; livingCells++) {
			Iterable<Cell> neighbours = createNeighbours(8, livingCells);
			if (livingCells == 3) {
				assertThat(resurrectionRuleChecker.check(cell, neighbours).getState(), is(CellState.ALIVE));
			} else {
				assertThat(resurrectionRuleChecker.check(cell, neighbours).getState(), is(CellState.DEAD));
			}
		}
	}

	private Iterable<Cell> createNeighbours(int amountOfNeighbours, int livingCells) {
		Collection<Cell> neighbours = new ArrayList<>();
		for (int cellIndex = 0; cellIndex < amountOfNeighbours; cellIndex++) {
			Cell cell = new SimpleCell(getStateForCell(cellIndex, amountOfNeighbours, livingCells));
			neighbours.add(cell);
		}
		return neighbours;
	}
	
	private CellState getStateForCell(int cellIndex, int amountOfNeighbours, int livingCells) {
		if (cellIndex < amountOfNeighbours - livingCells) {
			return CellState.DEAD;
		}
		return CellState.ALIVE;
	}

}
