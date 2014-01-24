package gameoflife.test.util;

import gameoflife.Cell;
import gameoflife.RuleApplier;

public class NoRuleApplier implements RuleApplier {

	@Override
	public Cell apply(Cell cell, Iterable<Cell> neighbours) {
		return cell;
	}

}
