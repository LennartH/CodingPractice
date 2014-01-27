package org.gameoflife.backend.impl.rule;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.shared.CellState;

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