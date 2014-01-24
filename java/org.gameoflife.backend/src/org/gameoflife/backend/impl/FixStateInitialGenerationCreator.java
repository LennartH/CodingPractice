package org.gameoflife.backend.impl;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.shared.CellState;

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
