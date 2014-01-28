package org.gameoflife.backend.test.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.shared.CellState;

public class Util {

    public static void assertThatAmountOfDeadAndAliveCellsIsCorrect(GameBoard board, int expectedAmountOfDeadCells, int expectedAmountOfLivingCells) {
        assertThatAmountOfDeadAndAliveCellsIsCorrect(getCellsAsFlatCollection(board), expectedAmountOfDeadCells, expectedAmountOfLivingCells);
    }

    public static void assertThatAmountOfDeadAndAliveCellsIsCorrect(Collection<Cell> cells, int expectedAmountOfDeadCells, int expectedAmountOfLivingCells) {
        int amountOfDeadCells = 0;
        int amountOfLivingCells = 0;
        for (Cell cell : cells) {
            if (cell.getState() == CellState.DEAD) {
                amountOfDeadCells++;
            }
            if (cell.getState() == CellState.ALIVE) {
                amountOfLivingCells++;
            }
        }
        
        assertThat(createDeadCellsMessage(expectedAmountOfDeadCells, amountOfDeadCells), amountOfDeadCells, is(expectedAmountOfDeadCells));
        assertThat(createLivingCellsMessage(expectedAmountOfLivingCells, amountOfLivingCells), amountOfLivingCells, is(expectedAmountOfLivingCells));
    }

    private static String createDeadCellsMessage(int expectedAmountOfDeadCells, int amountOfDeadCells) {
        return "Expected were " + expectedAmountOfDeadCells + " dead cells, but got " + amountOfDeadCells;
    }

    private static String createLivingCellsMessage(int expectedAmountOfLivingCells, int amountOfLivingCells) {
        return "Expected were " + expectedAmountOfLivingCells + " living cells, but got " + amountOfLivingCells;
    }

    public static Collection<Cell> getCellsAsFlatCollection(GameBoard board) {
        Collection<Cell> cells = new ArrayList<>();
        for (Cell[] row : board.getCells()) {
            cells.addAll(Arrays.asList(row));
        }
        return cells;
    }

}
