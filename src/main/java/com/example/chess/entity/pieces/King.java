package com.example.chess.entity.pieces;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;
import com.example.chess.entity.moves.KingMove;
import com.example.chess.entity.moves.StraightMove;

public class King extends Piece {
    public King(Cell cell, boolean isWhite) {
        super(new Move[]{new KingMove()}, cell, isWhite,"K");
    }


}