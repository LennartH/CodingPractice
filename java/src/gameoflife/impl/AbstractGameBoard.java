package gameoflife.impl;

import java.util.ArrayList;
import java.util.Collection;

import gameoflife.Cell;
import gameoflife.GameBoard;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleChecker;

public abstract class AbstractGameBoard implements GameBoard {
	
	private final Cell[][] board;

	public AbstractGameBoard(RuleChecker ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		board = initialGenerationCreator.createInitialGeneration();
	}

	protected Collection<Cell> getNeighbours(int widthIndex, int heightIndex) {
		Collection<Cell> neighbours = new ArrayList<>();
		return neighbours;
	}

	@Override
	public int getHeight() {
		return board.length;
	}

	@Override
	public int getWidth() {
		return board[0].length;
	}

}
