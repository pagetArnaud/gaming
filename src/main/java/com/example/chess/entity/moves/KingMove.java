package com.example.chess.entity.moves;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;

import java.util.ArrayList;
import java.util.List;

public class KingMove implements Move {
    @Override
    public List<Cell> getValidMoves(Piece piece, Cell start) {
        List<Cell> moves= new ArrayList<>();
        List<Cell> possibleMoves= start.getNeighborCells();
        for (Cell current:
            possibleMoves ) {
            if(canAddMove(piece, current)){
                moves.add(current);
            }
        }
        return moves;
    }

    private boolean canAddMove(Piece piece,Cell current){
        return (!current.isVoid() && ((current.isEmpty()) || (current.getCurrentPiece().isWhite() != piece.isWhite())));

    }

    private boolean isCheck(Piece piece,Cell current){
        //TODO regarder si echec
        return current.isEnemyInCell(piece);
    }
}
