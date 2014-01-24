package gameoflife.test.util;

import java.util.Collection;

import gameoflife.Cell;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleChecker;
import gameoflife.impl.DeadEndGameBoard;

public class OpenDeadEndGameBoard extends DeadEndGameBoard {

	public OpenDeadEndGameBoard(RuleChecker ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}

	public Collection<Cell> getNeighbours(int widthIndex, int heightIndex) {
		return super.getNeighbours(widthIndex, heightIndex);
	}

}
