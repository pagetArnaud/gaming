package com.example.chess.entity.pieces;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;
import com.example.chess.entity.moves.HorseRiderMove;
import com.example.chess.entity.moves.KingMove;

public class HorseRider extends Piece {
    public HorseRider(Cell cell, boolean isWhite) {
        super(new Move[]{new HorseRiderMove()}, cell, isWhite,"H");
    }


}