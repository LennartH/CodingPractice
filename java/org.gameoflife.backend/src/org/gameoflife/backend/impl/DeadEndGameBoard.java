package org.gameoflife.backend.impl;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.CellState;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.RuleApplier;

public class DeadEndGameBoard extends AbstractGameBoard {

	public DeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}
	
	@Override
	protected Cell getCellOutOfBounds(int w, int h) {
		return new SimpleCell(CellState.DEAD);
	}

}
