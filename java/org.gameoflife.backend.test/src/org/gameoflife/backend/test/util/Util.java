package org.gameoflife.backend.test.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.shared.CellState;

public class Util {

    public static void assertThatAmountOfDeadAndAliveCellsIsCorrect(Collection<Cell> neighbours, int expectedAmountOfDeadCells, int expectedAmountOfLivingCells) {
        int amountOfDeadCells = 0;
        int amountOfLivingCells = 0;
        for (Cell cell : neighbours) {
            if (cell.getState() == CellState.DEAD) {
                amountOfDeadCells++;
            }
            if (cell.getState() == CellState.ALIVE) {
                amountOfLivingCells++;
            }
        }
        
        assertThat(amountOfDeadCells, is(expectedAmountOfDeadCells));
        assertThat(amountOfLivingCells, is(expectedAmountOfLivingCells));
    }

}
