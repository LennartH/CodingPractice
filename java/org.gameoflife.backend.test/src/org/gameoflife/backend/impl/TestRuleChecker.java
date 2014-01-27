package org.gameoflife.backend.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.impl.SimpleCell;
import org.gameoflife.backend.impl.rule.OverpopulationRuleApplier;
import org.gameoflife.backend.impl.rule.ResurrectionRuleApplier;
import org.gameoflife.backend.impl.rule.StandardRuleApplier;
import org.gameoflife.backend.impl.rule.StarvationRuleApplier;
import org.gameoflife.backend.shared.CellState;
import org.junit.Test;

public class TestRuleChecker {

	@Test
	public void testResurrectionRuleApplier() {
		RuleApplier resurrectionRuleApplier = new ResurrectionRuleApplier();
		Cell deadCell = new SimpleCell(CellState.DEAD);
		Cell livingCell = new SimpleCell(CellState.ALIVE);
		
		for (int livingCells = 0; livingCells <= 8; livingCells++) {
			Iterable<Cell> neighbours = createNeighbours(8, livingCells);
			
			if (resurrectionRuleApplies(livingCells)) {
				assertThat(resurrectionRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.ALIVE));
			} else {
				assertThat(resurrectionRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.DEAD));
			}
			
			assertThat(resurrectionRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.ALIVE));
		}
	}
	
	@Test
	public void testStarvationRuleApplier() {
		RuleApplier starvationRuleApplier = new StarvationRuleApplier();
		Cell deadCell = new SimpleCell(CellState.DEAD);
		Cell livingCell = new SimpleCell(CellState.ALIVE);
		
		for (int livingCells = 0; livingCells <= 8; livingCells++) {
			Iterable<Cell> neighbours = createNeighbours(8, livingCells);
			
			if (starvationRuleApplies(livingCells)) {
				assertThat(starvationRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.DEAD));
			} else {
				assertThat(starvationRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.ALIVE));
			}
			
			assertThat(starvationRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.DEAD));
		}
	}
	
	@Test
	public void testOverpopulationRuleApplier() {
		RuleApplier starvationRuleApplier = new OverpopulationRuleApplier();
		Cell deadCell = new SimpleCell(CellState.DEAD);
		Cell livingCell = new SimpleCell(CellState.ALIVE);
		
		for (int livingCells = 0; livingCells <= 8; livingCells++) {
			Iterable<Cell> neighbours = createNeighbours(8, livingCells);
			
			if (overpopulationRuleApplies(livingCells)) {
				assertThat(starvationRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.DEAD));
			} else {
				assertThat(starvationRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.ALIVE));
			}
			
			assertThat(starvationRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.DEAD));
		}
	}
	
	@Test
	public void testStandardRulesApplier() {
		RuleApplier standardRuleApplier = new StandardRuleApplier();
		Cell deadCell = new SimpleCell(CellState.DEAD);
		Cell livingCell = new SimpleCell(CellState.ALIVE);
		
		for (int livingCells = 0; livingCells <= 8; livingCells++) {
			Iterable<Cell> neighbours = createNeighbours(8, livingCells);
			
			if (resurrectionRuleApplies(livingCells)) {
				assertThat(standardRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.ALIVE));
				assertThat(standardRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.ALIVE));
			}
			if (starvationRuleApplies(livingCells)) {
				assertThat(standardRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.DEAD));
				assertThat(standardRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.DEAD));
			}
			if (overpopulationRuleApplies(livingCells)) {
				assertThat(standardRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.DEAD));
				assertThat(standardRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.DEAD));
			}
			if (noStandardRuleApplies(livingCells)) {
				assertThat(standardRuleApplier.apply(deadCell, neighbours).getState(), is(CellState.DEAD));
				assertThat(standardRuleApplier.apply(livingCell, neighbours).getState(), is(CellState.ALIVE));
			}
		}
	}

	private boolean resurrectionRuleApplies(int livingCells) {
		return livingCells == 3;
	}

	private boolean starvationRuleApplies(int livingCells) {
		return livingCells < 2;
	}

	private boolean overpopulationRuleApplies(int livingCells) {
		return livingCells > 3;
	}

	private boolean noStandardRuleApplies(int livingCells) {
		return livingCells == 2;
	}

	private Iterable<Cell> createNeighbours(int amountOfNeighbours, int livingCells) {
		Collection<Cell> neighbours = new ArrayList<>();
		for (int cellIndex = 0; cellIndex < amountOfNeighbours; cellIndex++) {
			Cell cell = new SimpleCell(getStateForCellToCreate(cellIndex, amountOfNeighbours, livingCells));
			neighbours.add(cell);
		}
		return neighbours;
	}
	
	private CellState getStateForCellToCreate(int cellIndex, int amountOfNeighbours, int livingCells) {
		if (cellIndex < amountOfNeighbours - livingCells) {
			return CellState.DEAD;
		}
		return CellState.ALIVE;
	}

}
