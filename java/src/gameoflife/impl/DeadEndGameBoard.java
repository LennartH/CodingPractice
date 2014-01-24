package gameoflife.impl;

import gameoflife.InitialGenerationCreator;
import gameoflife.RuleApplier;

public class DeadEndGameBoard extends AbstractGameBoard {

	public DeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}

}
