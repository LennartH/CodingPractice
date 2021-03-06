package org.gameoflife.backend.impl.rule;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.RuleApplier;

public class CompoundRuleApplier implements RuleApplier {

	private Collection<RuleApplier> ruleAppliers;
	
	public CompoundRuleApplier(RuleApplier... ruleAppliers) {
		this.ruleAppliers = new HashSet<>(Arrays.asList(ruleAppliers));
	}

	@Override
	public Cell apply(Cell cell, Iterable<Cell> neighbours) {
		for (RuleApplier ruleApplier : ruleAppliers) {
			Cell nextGenerationCell = ruleApplier.apply(cell, neighbours);
			if (cellStateChanged(cell, nextGenerationCell)) {
				return nextGenerationCell;
			}
		}
		return cell;
	}

	private boolean cellStateChanged(Cell cell, Cell nextGenerationCell) {
		return cell.getState() != nextGenerationCell.getState();
	}

}
