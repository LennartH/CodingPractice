package gameoflife;

public interface RuleChecker {

	public Cell check(Cell cell, Iterable<Cell> neighbours);

}
