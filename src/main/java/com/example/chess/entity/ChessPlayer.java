package com.example.chess.entity;

import java.util.ArrayList;
import java.util.List;

public class ChessPlayer implements Player{


    private boolean isWhite;
    private List<Piece> pieces;

    private String id;

    public ChessPlayer(boolean isWhite, List<Piece> pieces) {
        this.isWhite = isWhite;
        this.pieces = pieces;
    }

    public ChessPlayer(boolean isWhite, String id) {
        this.isWhite = isWhite;
        this.pieces = new ArrayList<>();
        this.id = id;
    }

    public ChessPlayer(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
