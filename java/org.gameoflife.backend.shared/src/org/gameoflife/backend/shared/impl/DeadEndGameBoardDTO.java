package org.gameoflife.backend.shared.impl;

import java.util.List;

import org.gameoflife.backend.shared.CellDTO;
import org.gameoflife.backend.shared.GameBoardDTO;

public class DeadEndGameBoardDTO implements GameBoardDTO {
    private static final long serialVersionUID = -7003662362032081957L;
    
    private List<List<CellDTO>> board;

    public DeadEndGameBoardDTO(List<List<CellDTO>> board) {
        this.board = board;
    }
    
    @Override
    public CellDTO getCellDTO(int widthIndex, int heightIndex) {
    	return board.get(heightIndex).get(widthIndex);
    }

    @Override
    public int getHeight() {
        return board.size();
    }

    @Override
    public int getWidth() {
        return board.get(0).size();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((board == null) ? 0 : board.hashCode());
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
        DeadEndGameBoardDTO other = (DeadEndGameBoardDTO) obj;
        if (board == null) {
            if (other.board != null)
                return false;
        } else if (!board.equals(other.board))
            return false;
        return true;
    }

}
