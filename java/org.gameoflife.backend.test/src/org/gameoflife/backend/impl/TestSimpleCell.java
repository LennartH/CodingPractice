package org.gameoflife.backend.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.gameoflife.backend.Cell;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleCellDTO;
import org.junit.Test;

public class TestSimpleCell {

    @Test
    public void testAliveCellToDTOConversion() {
        Cell cell = new SimpleCell(CellState.ALIVE);
        CellDTO expectedCellDTO = new SimpleCellDTO(CellState.ALIVE);
        assertThat(cell.asDTO(), is(expectedCellDTO));
    }

    @Test
    public void testDeadCellToDTOConversion() {
        Cell cell = new SimpleCell(CellState.DEAD);
        CellDTO expectedCellDTO = new SimpleCellDTO(CellState.DEAD);
        assertThat(cell.asDTO(), is(expectedCellDTO));
    }

}
