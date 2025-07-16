package com.example.chess.dto;

import java.util.List;

public record GameUpdateDTO (int gameStatus, boolean isWhiteTurn, List<CellDTO> changedCell){
}
