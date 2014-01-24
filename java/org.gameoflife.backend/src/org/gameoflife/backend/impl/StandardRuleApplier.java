package org.gameoflife.backend.impl;

public class StandardRuleApplier extends CompoundRuleApplier {
	
	public StandardRuleApplier() {
		super(new ResurrectionRuleApplier(), new OverpopulationRuleApplier(), new StarvationRuleApplier());
	}

}
