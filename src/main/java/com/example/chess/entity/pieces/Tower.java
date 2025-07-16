package com.example.chess.entity.pieces;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;
import com.example.chess.entity.moves.StraightMove;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Piece {
    public Tower(Cell cell, boolean isWhite) {
        super(new Move[]{new StraightMove()}, cell, isWhite,"T");
    }


}
