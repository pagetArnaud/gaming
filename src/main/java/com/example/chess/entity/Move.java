package com.example.chess.entity;


import java.util.List;

public interface Move {



    List<Cell> getValidMoves(Piece piece, Cell start);




}
