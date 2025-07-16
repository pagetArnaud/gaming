package com.example.chess.dto;

import com.example.chess.entity.Cell;

public record CellDTO(PieceDTO currentPiece, boolean iswhite, boolean isVoid, String id) {

    public CellDTO(Cell cell){
        this(cell.isEmpty() ? null : new PieceDTO(cell.getCurrentPiece()), cell.isIswhite(), cell.isVoid(), cell.getId());
    }
}
