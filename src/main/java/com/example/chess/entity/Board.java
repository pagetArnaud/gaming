package com.example.chess.entity;

import com.example.chess.entity.pieces.*;
import lombok.Data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public class Board implements PropertyChangeListener {

    List<Piece> whitePiece;
    List<Piece> blackPiece;
    Cell bottomCorner;
    Cell topCorner;

    King whiteKing;
    King blackKing;
    Set<Cell> changeCell;

    public Board() {
        whitePiece = new ArrayList<>();
        blackPiece = new ArrayList<>();
        changeCell = new HashSet<>();
    }

    public void removePiece(Piece piece) {
        if (piece.isWhite()) {
            whitePiece.remove(piece);
            if (piece == whiteKing) {
                whiteKing = null;
            }
        } else {
            blackPiece.remove(piece);
            if (piece == blackKing) {
                blackKing = null;
            }
        }
    }

    public void initBoard() {
        this.bottomCorner = new Cell(false, this);
        Cell.board.put(bottomCorner.getId(), bottomCorner);
        this.topCorner = this.bottomCorner;
        Cell current = this.bottomCorner;

        current = createCellNeighbor(current);
        Piece piece = new Tower(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new HorseRider(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Fool(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Queen(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        whiteKing = new King(current, true);
        piece = whiteKing;
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Fool(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new HorseRider(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Tower(current, true);
        piece.afterInit();
        current.setCurrentPiece(piece);
        whitePiece.add(piece);

        this.topCorner = topCorner.getTop();
        current = this.topCorner;
        for (int i = 0; i < 8; i++) {
            current = createCellNeighbor(current);
            piece = new Pawn(current, true);
            piece.afterInit();
            current.setCurrentPiece(piece);
            whitePiece.add(piece);
            current = current.getRight();
        }

        for (int j = 0; j < 4; j++) {
            this.topCorner = topCorner.getTop();
            current = this.topCorner;
            for (int i = 0; i < 8; i++) {
                current = createCellNeighbor(current);
                current = current.getRight();
            }
        }

        this.topCorner = topCorner.getTop();
        current = this.topCorner;
        for (int i = 0; i < 8; i++) {
            current = createCellNeighbor(current);
            piece = new Pawn(current, false);
            piece.afterInit();
            current.setCurrentPiece(piece);
            blackPiece.add(piece);
            current = current.getRight();
        }

        this.topCorner = topCorner.getTop();
        current = this.topCorner;

        current = createCellNeighbor(current);
        piece = new Tower(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new HorseRider(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Fool(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Queen(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        blackKing = new King(current, false);
        piece = blackKing;

        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Fool(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new HorseRider(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);
        current = current.getRight();

        current = createCellNeighbor(current);
        piece = new Tower(current, false);
        piece.afterInit();
        current.setCurrentPiece(piece);
        blackPiece.add(piece);

    }

    private void setTopCorner() {
        Cell current = bottomCorner;
        while (!current.getTop().isVoid()) {
            current = current.getTop();
        }
        this.topCorner = current;
    }

    public Cell getTopCorner() {
        return topCorner;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(King whiteKing) {
        this.whiteKing = whiteKing;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(King blackKing) {
        this.blackKing = blackKing;
    }

    private Cell createCellNeighbor(Cell start) {
        start.setVoid(false);
        Cell topL = start.getTopL();
        Cell top = start.getTop();
        Cell topR = start.getTopR();
        Cell left = start.getLeft();
        Cell right = start.getRight();
        Cell botL = start.getBotL();
        Cell bot = start.getBot();
        Cell botR = start.getBotR();
        try {
            if (topL == null) {
                topL = start.createNeighbor(Direction.TOPL);
            }
            if (top == null) {
                top = start.createNeighbor(Direction.TOP);
            }
            if (topR == null) {
                topR = start.createNeighbor(Direction.TOPR);
            }
            if (left == null) {
                left = start.createNeighbor(Direction.LEFT);
            }
            if (right == null) {
                right = start.createNeighbor(Direction.RIGHT);
            }
            if (botL == null) {
                botL = start.createNeighbor(Direction.BOTL);
            }
            if (bot == null) {
                bot = start.createNeighbor(Direction.BOT);
            }
            if (botR == null) {
                botR = start.createNeighbor(Direction.BOTR);
            }
        } catch (IllegalCellCreationExeption e) {
            throw new RuntimeException(e);
        }
        topL.setRight(top);
        topL.setBot(left);

        top.setLeft(topL);
        top.setBotL(left);
        top.setRight(topR);
        top.setRight(topR);
        top.setBotR(right);

        topR.setBot(right);
        topR.setLeft(top);

        right.setTop(topR);
        right.setBot(botR);
        right.setBotL(bot);
        right.setTopL(top);

        botR.setTop(right);
        botR.setLeft(bot);

        bot.setTopR(right);
        bot.setRight(botR);
        bot.setLeft(botL);
        bot.setTopL(left);

        botL.setTop(left);
        botL.setRight(bot);

        left.setTop(topL);
        left.setTopR(top);
        left.setBotR(bot);
        left.setBot(botL);


        return start;
    }

    @Override
    public String toString() {
        Cell current = this.topCorner;
        String output = "";
        while (!current.isVoid()) {
            for (Cell cell : getLine(current)
            ) {
                output += cell.toString();
            }
            current = current.getBot();
            output += "\n";
        }
        return output;
    }

    private List<Cell> getLine(Cell cornerCell) {
        List<Cell> output = new ArrayList<>();
        Cell current = cornerCell;
        while (!current.isVoid()) {
            output.add(current);
            current = current.getRight();
        }
        return output;
    }

    public List<Piece> getWhitePiece() {
        return whitePiece;
    }

    public void setWhitePiece(List<Piece> whitePiece) {
        this.whitePiece = whitePiece;
    }

    public List<Piece> getBlackPiece() {
        return blackPiece;
    }

    public void setBlackPiece(List<Piece> blackPiece) {
        this.blackPiece = blackPiece;
    }

    public Cell getBottomCorner() {
        return bottomCorner;
    }

    public void setBottomCorner(Cell bottomCorner) {
        this.bottomCorner = bottomCorner;
    }

    public Set<Cell> getChangeCell() {
        return changeCell;
    }

    public void clearChangeCell() {
        changeCell.clear();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Cell sourceCell = (Cell) evt.getSource();
        changeCell.add(sourceCell);
    }
}
