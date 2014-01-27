package org.gameoflife.backend.shared.impl.dto;

import org.gameoflife.backend.shared.CellState;
import org.gameoflife.backend.shared.dto.CellDTO;

public class SimpleCellDTO implements CellDTO {
    private static final long serialVersionUID = 8289759487983926535L;
    
    private CellState state;

    public SimpleCellDTO(CellState state) {
        this.state = state;
    }
    
    @Override
    public CellState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "SimpleCellDTO [" + state + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        SimpleCellDTO other = (SimpleCellDTO) obj;
        if (state != other.state)
            return false;
        return true;
    }

}
