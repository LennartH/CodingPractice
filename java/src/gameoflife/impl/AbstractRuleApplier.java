package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.CellState;
import gameoflife.RuleApplier;

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