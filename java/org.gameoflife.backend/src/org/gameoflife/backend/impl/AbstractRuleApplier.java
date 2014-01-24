package org.gameoflife.backend.impl;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.CellState;
import org.gameoflife.backend.RuleApplier;

public abstract class AbstractRuleApplier implements RuleApplier {

	protected int amountOfLivingCells(Iterable<Cell> cells) {
		int amountOfLivingCells = 0;
		for (Cell cell : cells) {
			if (cell.getState() == CellState.ALIVE) {
				amountOfLivingCells++;
			}
		}
		return amountOfLivingCells;
	}

}