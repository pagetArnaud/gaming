package com.example.chess.entity.moves;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Direction;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;

import java.util.ArrayList;
import java.util.List;

public class StraightMove implements Move {


    @Override
    public List<Cell> getValidMoves(Piece piece, Cell start) {
        List<Cell> moves = new ArrayList<>();
        boolean isWhite = piece.isWhite();
        getMovesFromDirection(isWhite, start, Direction.TOP, moves);
        getMovesFromDirection(isWhite, start, Direction.BOT, moves);
        getMovesFromDirection(isWhite, start, Direction.LEFT, moves);
        getMovesFromDirection(isWhite, start, Direction.RIGHT, moves);
        return moves;
    }

    private void getMovesFromDirection(boolean isWhite, Cell start, Direction direction, List<Cell> moves) {
        Cell current = start.getCellFromDirection(direction);
        boolean enemyfound = false;
        while (!enemyfound && !current.isVoid() && ((current.isEmpty()) || (current.getCurrentPiece().isWhite() != isWhite))) {
            moves.add(current);
            enemyfound = !current.isEmpty() && (current.getCurrentPiece().isWhite() != isWhite);
            current = current.getCellFromDirection(direction);
        }
    }


}
