package com.example.chess.entity;

import lombok.Data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {


    public static final Map<String, Cell> board = HashMap.newHashMap(8 * 8);
    private Cell topL;
    private Cell top;
    private Cell topR;
    private Cell left;
    private Cell right;
    private Cell botL;
    private Cell bot;
    private Cell botR;
    private Piece currentPiece;
    private boolean iswhite;
    private boolean isVoid;
    private int row;
    private int col;
    static final String id = "%d:%d";//row:col
    //private Action[] endOfTurnAction;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);


    public Cell(boolean iswhite, PropertyChangeListener listener) {
        this.iswhite = iswhite;
        this.isVoid = true;
        this.row = 1000;
        this.col = 1000;
        pcs.addPropertyChangeListener(listener);
    }

    public Cell getCellFromDirection(Direction direction) {
        Cell output = null;
        switch (direction) {
            case TOPL -> {
                output = getTopL();
            }
            case TOP -> {
                output = getTop();
            }
            case TOPR -> {
                output = getTopR();
            }
            case LEFT -> {
                output = getLeft();
            }
            case RIGHT -> {
                output = getRight();
            }
            case BOTL -> {
                output = getBotL();
            }
            case BOT -> {
                output = getBot();

            }
            case BOTR -> {
                output = getBotR();
            }
        }
        return output;
    }

    public boolean canLandHere(Piece piece) {
        return !this.isVoid() && ((this.isEmpty()) || (this.getCurrentPiece().isWhite() != piece.isWhite()));
    }

    public List<Cell> getNeighborCells() {
        List<Cell> neighbour = new ArrayList<>(8);
        if (!topL.isVoid()) {
            neighbour.add(topL);
        }
        if (!top.isVoid()) {
            neighbour.add(top);
        }
        if (!topR.isVoid()) {
            neighbour.add(topR);
        }

        if (!right.isVoid()) {
            neighbour.add(right);
        }

        if (!botR.isVoid()) {
            neighbour.add(botR);
        }
        if (!bot.isVoid()) {
            neighbour.add(bot);
        }
        if (!botL.isVoid()) {
            neighbour.add(botL);
        }
        if (!left.isVoid()) {
            neighbour.add(left);
        }

        return neighbour;
    }

    public boolean isEmpty() {
        return currentPiece == null;
    }

    public Cell createNeighbor(Direction direction) throws IllegalCellCreationExeption {
        Cell output = null;
        switch (direction) {
            case TOPL -> {
                if (this.topL != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setBotR(this);
                    output.col = this.col - 1;
                    output.row = this.row + 1;
                    this.topL = output;
                }

            }
            case TOP -> {
                if (this.top != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(!this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setBot(this);

                    output.col = this.col;
                    output.row = this.row + 1;
                    this.top = output;
                }
            }
            case TOPR -> {
                if (this.topR != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setBotL(this);

                    output.col = this.col + 1;
                    output.row = this.row + 1;
                    this.topR = output;

                }
            }
            case LEFT -> {
                if (this.left != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(!this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setRight(this);

                    output.col = this.col - 1;
                    output.row = this.row;
                    this.left = output;
                }

            }
            case RIGHT -> {

                if (this.right != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(!this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setLeft(this);

                    output.col = this.col + 1;
                    output.row = this.row;
                    this.right = output;
                }
            }
            case BOTL -> {

                if (this.botL != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setTopR(this);

                    output.col = this.col - 1;
                    output.row = this.row - 1;
                    this.botL = output;
                }
            }
            case BOT -> {
                if (this.bot != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(!this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setTop(this);
                    output.col = this.col;
                    output.row = this.row - 1;
                    this.bot = output;
                }
            }
            case BOTR -> {
                if (this.botR != null) {
                    throw new IllegalCellCreationExeption();
                } else {
                    output = new Cell(this.iswhite, pcs.getPropertyChangeListeners()[0]);
                    output.setTopL(this);
                    output.col = this.col + 1;
                    output.row = this.row - 1;
                    this.botR = output;
                }
            }
        }
        Cell.board.put(output.getId(), output);
        return output;
    }

    public Cell getTopL() {
        return topL;
    }

    public void setTopL(Cell topL) {
        this.topL = topL;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getTop() {
        return top;
    }

    public void setTop(Cell top) {
        this.top = top;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getTopR() {
        return topR;
    }

    public void setTopR(Cell topR) {
        this.topR = topR;

        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getLeft() {
        return left;
    }

    public void setLeft(Cell left) {
        this.left = left;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getRight() {
        return right;
    }

    public void setRight(Cell right) {
        this.right = right;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getBotL() {
        return botL;
    }

    public void setBotL(Cell botL) {
        this.botL = botL;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getBot() {
        return bot;
    }

    public void setBot(Cell bot) {
        this.bot = bot;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Cell getBotR() {
        return botR;
    }

    public void setBotR(Cell botR) {
        this.botR = botR;
        pcs.firePropertyChange("change", 0, 1);
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
        pcs.firePropertyChange("change", 0, 1);
    }

    public boolean isIswhite() {
        return iswhite;
    }

    public void setIswhite(boolean iswhite) {
        this.iswhite = iswhite;
        pcs.firePropertyChange("change", 0, 1);
    }

    public boolean isVoid() {
        return isVoid;
    }

    public void setVoid(boolean aVoid) {
        isVoid = aVoid;
        pcs.firePropertyChange("change", 0, 1);
    }

    public boolean isEnemyInCell(Piece piece) {
        return !isEmpty() && currentPiece.isWhite() != piece.isWhite();
    }

    public String getId() {
        return String.format(id, this.row, this.col);
    }

    @Override
    public String toString() {
        return "|" + (isVoid ? 'V' : getId()) + (isEmpty() ? " " : currentPiece.toString()) + "|";
    }
}
