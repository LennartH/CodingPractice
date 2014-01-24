package org.gameoflife.backend.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.CellState;
import org.gameoflife.backend.InitialGenerationCreator;
import org.gameoflife.backend.RuleApplier;
import org.gameoflife.backend.impl.DeadEndGameBoard;
import org.gameoflife.backend.impl.SimpleCell;

public class OpenDeadEndGameBoard extends DeadEndGameBoard {

	public OpenDeadEndGameBoard(RuleApplier ruleApplier, InitialGenerationCreator initialGenerationCreator) {
		super(ruleApplier, initialGenerationCreator);
	}
	
	public Set<Cell> getNeighbours(int widthIndex, int heightIndex) {
		return super.getNeighbours(widthIndex, heightIndex);
	}

    public Collection<Cell> getCellsAsFlatCollection() {
        Collection<Cell> cells = new ArrayList<>();
        for (Cell[] row : getCells()) {
            cells.addAll(Arrays.asList(row));
        }
        return cells;
    }

    public void setCellState(int widthIndex, int heightIndex, CellState state) {
        getCells()[heightIndex][widthIndex] = new SimpleCell(state);
    }

}
