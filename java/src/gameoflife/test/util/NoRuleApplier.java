package gameoflife.test.util;

import gameoflife.Cell;
import gameoflife.RuleChecker;

public class NoRuleApplier implements RuleChecker {

	@Override
	public Cell check(Cell cell, Iterable<Cell> neighbours) {
		return cell;
	}

}
