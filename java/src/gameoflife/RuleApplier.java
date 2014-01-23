package gameoflife;

public interface RuleApplier {

	public Cell apply(Cell cell, Iterable<Cell> neighbours);

}
