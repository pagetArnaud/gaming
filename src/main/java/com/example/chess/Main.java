package com.example.chess;

import com.example.chess.entity.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ChessPlayer white = new ChessPlayer(true,"white");
        ChessPlayer black = new ChessPlayer(false,"black");
        ChessGame game = new ChessGame(white,black);
        game.InitGame();
        Board board = game.getBoard();

        System.out.println("Initial board\n"+board);
        Piece temp = white.getPieces().get(2);
        List<Cell> moves = temp.getValidesMoves();
        //System.out.println(moves);
        Cell endCell = moves.get(1);
        board.clearChangeCell();
        System.out.println(game.move(white.getId(),temp.getId(),endCell.getId()));
        System.out.println("Moved board\n"+board);
        System.out.println(board.getChangeCell());

        /*
        moves = temp.getValidesMoves();
        endCell = moves.get(0);
        System.out.println(moves);
        game.move(white,temp,endCell);

        temp = white.getPieces().get(0);
        moves = temp.getValidesMoves();
        endCell = moves.get(0);
        game.move(white,temp,endCell);
        temp = white.getPieces().get(0);
        moves = temp.getValidesMoves();
        endCell = moves.get(0);
        game.move(white,temp,endCell);
        temp = white.getPieces().get(0);
        moves = temp.getValidesMoves();
        endCell = moves.get(0);
        game.move(white,temp,endCell);

        temp = white.getPieces().get(0);
        moves = temp.getValidesMoves();
        System.out.println(moves);
        endCell = moves.get(0);
        game.move(white,temp,endCell);

         */


        System.out.println(board);
        System.out.println(board.getBlackPiece());


    }
}
