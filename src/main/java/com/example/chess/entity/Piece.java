package com.example.chess.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public abstract class Piece {

    private List<Move> moves;
    private List<Move> toSupress;
    private Cell cell;
    private boolean isWhite;
    private String name;
    private String id;

    public Piece(Move[] moves, Cell cell, boolean isWhite, String name) {
        this.moves = new ArrayList<>(List.of(moves));
        this.toSupress = new ArrayList<>(this.moves.size());
        this.cell = cell;
        this.isWhite = isWhite;
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public List<Cell> getValidesMoves() {
        List<Cell> output = new ArrayList<>();
        for (Move move : this.getMoves()) {
            output.addAll(move.getValidMoves(this, this.getCell()));
        }
        return output;
    }

    public void destroy() {
        //ne fait rien pour le meoment
        System.out.println("Piece detruite " + this.toString());
    }

    public void afterInit() {
        return;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        if (this.toSupress.size() > 0) {
            this.moves.removeAll(this.toSupress);
            this.toSupress.clear();
        }
        this.cell = cell;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<Move> getToSupress() {
        return toSupress;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + (isWhite ? "W" : "B");
    }
}
