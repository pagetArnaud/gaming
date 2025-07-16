package com.example.chess.dto;

public record GameMoveDTO(String playerId, String pieceId,String startCellId, String endCellId) {
}
