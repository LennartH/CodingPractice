package org.gameoflife.backend.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.impl.rule.StandardRuleApplier;
import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleGameBoardDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleCellDTO;
import org.junit.Test;

public class TestDeadEndGameBoard {

    @Test
    public void testToDTOConversion() {
        GameBoard board = new DeadEndGameBoard(new StandardRuleApplier(), new FixStateInitialGenerationCreator(25, 25, CellState.ALIVE));
        GameBoardDTO expectedBoardDTO = createBoardDTO(board.getWidth(), board.getHeight(), CellState.ALIVE);
        assertThat(board.asDTO(), is(expectedBoardDTO));
    }

    private GameBoardDTO createBoardDTO(int width, int height, CellState state) {
        List<List<CellDTO>> board = new ArrayList<>();
        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            ArrayList<CellDTO> row = new ArrayList<CellDTO>();
            board.add(row);
            for (int heightIndex = 0; heightIndex < height; heightIndex++) {
                row.add(new SimpleCellDTO(state));
            }
        }
        return new SimpleGameBoardDTO(board);
    }

}
