package gameoflife.test.util;

import gameoflife.Cell;
import gameoflife.CellState;
import gameoflife.InitialGenerationCreator;
import gameoflife.impl.SimpleCell;

public class FixStateInitialGenerationCreator implements InitialGenerationCreator {

	private Cell[][] initialGeneration;
	private CellState initialState;

	public FixStateInitialGenerationCreator(int width, int height, CellState initialState) {
		this.initialState = initialState;
		initialGeneration = new Cell[height][width];
	}
	
	@Override
	public Cell[][] createInitialGeneration() {
		for (int heightIndex = 0; heightIndex < initialGeneration.length; heightIndex++) {
			 for (int widthIndex = 0; widthIndex < initialGeneration[0].length; widthIndex++) {
				initialGeneration[heightIndex][widthIndex] = new SimpleCell(initialState);
			}
		}
		return initialGeneration;
	}

}
