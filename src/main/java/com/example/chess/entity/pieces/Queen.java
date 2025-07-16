package com.example.chess.entity.pieces;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;
import com.example.chess.entity.moves.CrossMove;
import com.example.chess.entity.moves.StraightMove;

public class Queen extends Piece {
    public Queen(Cell cell, boolean isWhite) {
        super(new Move[]{new StraightMove(),new CrossMove()}, cell, isWhite,"Q");
    }


}