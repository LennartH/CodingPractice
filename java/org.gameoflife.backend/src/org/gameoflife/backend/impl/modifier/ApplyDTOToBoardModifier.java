package org.gameoflife.backend.impl.modifier;

import org.gameoflife.backend.GameBoard;
import org.gameoflife.backend.GameBoardModifier;
import org.gameoflife.backend.shared.dto.GameBoardDTO;

public class ApplyDTOToBoardModifier implements GameBoardModifier {

    private GameBoardDTO boardDTOToApply;
    private GameBoard boardToModify;

    public ApplyDTOToBoardModifier(GameBoardDTO boardDTOToApply, GameBoard boardToModify) throws IllegalArgumentException {
        if (!boardsHaveTheSameSize(boardDTOToApply, boardToModify)) {
            throw createBoardSizesDontMatchException(boardDTOToApply, boardToModify);
        }
        
        this.boardDTOToApply = boardDTOToApply;
        this.boardToModify = boardToModify;
    }

    @Override
    public void applyModifications() {
        for (int heightIndex = 0; heightIndex < boardDTOToApply.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < boardDTOToApply.getWidth(); widthIndex++) {
                boardToModify.setCellState(widthIndex, heightIndex, boardDTOToApply.getCellDTOState(widthIndex, heightIndex));
            }
        }
    }

    private boolean boardsHaveTheSameSize(GameBoardDTO boardDTOToApply, GameBoard boardToModify) {
        return boardDTOToApply.getWidth() == boardToModify.getWidth() &&
               boardDTOToApply.getHeight() == boardToModify.getHeight();
    }

    private IllegalArgumentException createBoardSizesDontMatchException(GameBoardDTO boardDTOToApply, GameBoard boardToModify) {
        return new IllegalArgumentException(createBoardSizesDontMatchExceptionMessage(boardDTOToApply, boardToModify));
    }

    private String createBoardSizesDontMatchExceptionMessage(GameBoardDTO boardDTOToApply, GameBoard boardToModify) {
        return "BoardDTO (" + boardDTOToApply.getWidth() +"x" + boardDTOToApply.getHeight() + ") " +
               "and Board (" + boardToModify.getWidth() + "x" + boardToModify.getHeight() + ") " +
               "sizes don't match.";
    }

}
