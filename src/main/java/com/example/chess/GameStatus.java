package com.example.chess;

public enum GameStatus {
    WAITING_FOR_PLAYERS(0),
    IN_PROGRESS(1),
    PAUSED(2),
    COMPLETED(3),
    CANCELLED(4);

    private final int message;

    GameStatus(int message) {
        this.message = message;
    }

    public int getValue() {
        return message;
    }
}
