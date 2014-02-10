package org.gameoflife.backend.shared.factories;

import java.util.ArrayList;
import java.util.List;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;
import org.gameoflife.backend.shared.dto.GameBoardDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleCellDTO;
import org.gameoflife.backend.shared.impl.dto.SimpleGameBoardDTO;

public class GameBoardDTOFactory {
    
    private GameBoardDTOFactory() {
    }
    
    public static GameBoardDTO createDeadGameBoardDTO(int width, int height) {
        List<List<CellDTO>> board = new ArrayList<>();
        for (int heightIndex = 0; heightIndex < height; heightIndex++) {
            List<CellDTO> row = new ArrayList<>();
            board.add(row);
            for (int widthIndex = 0; widthIndex < width; widthIndex++) {
                row.add(new SimpleCellDTO(CellState.DEAD));
            }
        }
        return new SimpleGameBoardDTO(board);
    }

}
