package com.example.chess.dto;

import com.example.chess.entity.Piece;

public record PieceDTO(boolean isWhite, String name, String id) {

    public PieceDTO(Piece piece){
        this(piece.isWhite(),piece.getName(),piece.getId());
    }
}
