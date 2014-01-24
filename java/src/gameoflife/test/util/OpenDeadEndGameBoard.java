package gameoflife.test.util;

import gameoflife.Cell;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleApplier;
import gameoflife.impl.DeadEndGameBoard;

import java.util.Collection;

public class OpenDeadEndGameBoard extends DeadEndGameBoard {

	public OpenDeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}

	public Collection<Cell> getNeighbours(int widthIndex, int heightIndex) {
		return super.getNeighbours(widthIndex, heightIndex);
	}

}
