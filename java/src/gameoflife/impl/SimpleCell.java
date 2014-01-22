package gameoflife.impl;

import gameoflife.Cell;
import gameoflife.CellState;

public class SimpleCell implements Cell {

	private CellState state;

	public SimpleCell(CellState state) {
		this.state = state;
	}

	@Override
	public CellState getState() {
		return state;
	}

}
