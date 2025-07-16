package com.example.chess.entity.pieces;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;
import com.example.chess.entity.moves.CrossMove;
import com.example.chess.entity.moves.StraightMove;

public class Fool extends Piece {
    public Fool(Cell cell, boolean isWhite) {
        super(new Move[]{new CrossMove()}, cell, isWhite,"F");
    }

}