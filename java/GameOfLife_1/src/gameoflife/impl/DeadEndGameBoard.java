package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.CellState;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleApplier;

public class DeadEndGameBoard extends AbstractGameBoard {

	public DeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}
	
	@Override
	protected Cell getCellOutOfBounds(int w, int h) {
		return new SimpleCell(CellState.DEAD);
	}

}
