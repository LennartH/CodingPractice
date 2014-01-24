package org.gameoflife.backend.test.util;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.RuleApplier;

public class NoRuleApplier implements RuleApplier {

	@Override
	public Cell apply(Cell cell, Iterable<Cell> neighbours) {
		return cell;
	}

}
