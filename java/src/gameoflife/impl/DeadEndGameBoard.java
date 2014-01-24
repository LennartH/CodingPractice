package gameoflife.impl;

import gameoflife.InitialGenerationCreator;
import gameoflife.RuleChecker;

public class DeadEndGameBoard extends AbstractGameBoard {

	public DeadEndGameBoard(RuleChecker ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}

}
