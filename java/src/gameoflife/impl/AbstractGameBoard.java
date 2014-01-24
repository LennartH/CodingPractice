package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.GameBoard;
import gameoflife.InitialGenerationCreator;
import gameoflife.RuleApplier;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractGameBoard implements GameBoard {
	
	private final Cell[][] board;

	public AbstractGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
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
