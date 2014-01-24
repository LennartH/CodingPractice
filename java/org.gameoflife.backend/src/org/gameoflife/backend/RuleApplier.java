package org.gameoflife.backend;

public interface RuleApplier {

	public Cell apply(Cell cell, Iterable<Cell> neighbours);

}
