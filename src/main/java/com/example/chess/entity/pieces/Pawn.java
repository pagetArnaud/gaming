package com.example.chess.entity.pieces;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;
import com.example.chess.entity.moves.PawnMove;
import com.example.chess.entity.moves.PawnSpecialMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Cell cell, boolean isWhite) {
        super(new Move[]{new PawnMove(), new PawnSpecialMove()}, cell, isWhite, "P");
    }

    public void afterInit(){
        Move special = this.getMoves().get(1);
        this.getToSupress().add(special);
    }
}
