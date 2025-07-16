package com.example.chess.entity.moves;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;

import java.util.ArrayList;
import java.util.List;

public class PawnMove implements Move {

    @Override
    public List<Cell> getValidMoves(Piece piece, Cell start) {
        List<Cell> moves = new ArrayList<>();
        boolean isWhite = piece.isWhite();
        if (isWhite) {

            Cell current = start.getTop();
            if (!current.isVoid() && current.isEmpty()) {
                moves.add(current);
            }
            current = start.getTopL();
            if (!current.isVoid() && ((!current.isEmpty()) && (current.getCurrentPiece().isWhite() != isWhite))) {
                moves.add(current);
            }
            current = start.getTopR();
            if (!current.isVoid() && ((!current.isEmpty()) && (current.getCurrentPiece().isWhite() != isWhite))) {
                moves.add(current);
            }
        } else {
            Cell current = start.getBot();
            if (!current.isVoid() && current.isEmpty()) {
                moves.add(current);
            }
            current = start.getBotL();
            if (!current.isVoid() && ((!current.isEmpty()) && (current.getCurrentPiece().isWhite() != isWhite))) {
                moves.add(current);
            }
            current = start.getBotR();
            if (!current.isVoid() && ((!current.isEmpty()) && (current.getCurrentPiece().isWhite() != isWhite))) {
                moves.add(current);
            }
        }
        return moves;
    }
}
