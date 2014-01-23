package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.CellState;

public class OverpopulationRuleApplier extends AbstractRuleApplier {

	@Override
	public Cell apply(Cell cell, Iterable<Cell> neighbours) {
		return amountOfLivingCells(neighbours) > 3 ? new SimpleCell(CellState.DEAD) : cell;
	}

}
