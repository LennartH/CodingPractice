package org.gameoflife.backend.impl;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.shared.CellState;

public class StarvationRuleApplier extends AbstractRuleApplier {

	@Override
	public Cell apply(Cell cell, Iterable<Cell> neighbours) {
		return amountOfLivingCells(neighbours) < 2 ? new SimpleCell(CellState.DEAD) : cell;
	}

}
