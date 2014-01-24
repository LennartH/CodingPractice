package org.gameoflife.backend.impl;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.shared.CellDTO;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.impl.SimpleCellDTO;

public class SimpleCell implements Cell {
	
	private static int nextID = 0;

	private final int id;
	private CellState state;

	public SimpleCell(CellState state) {
		id = getNextID();
		this.state = state;
	}

	private static int getNextID() {
		return nextID++;
	}

	@Override
	public CellState getState() {
		return state;
	}
	
	@Override
	public CellDTO asDTO() {
	    return new SimpleCellDTO(state);
	}

	@Override
	public String toString() {
		return "SimpleCell [" + state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleCell other = (SimpleCell) obj;
		if (id != other.id)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

}
