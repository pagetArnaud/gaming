package com.example.chess.entity;

import com.example.chess.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChessGame implements Game {

    private GameStatus gameStatus;
    private final String gameid;
    private ChessPlayer white;
    private ChessPlayer black;
    private boolean currentPlayerIsWhite;
    private Board board;
    private List<Cell> playerMoves;

    public ChessGame(ChessPlayer white, ChessPlayer black) {
        this.white = white;
        this.black = black;
        this.gameStatus = GameStatus.WAITING_FOR_PLAYERS;
        this.gameid = UUID.randomUUID().toString();
        this.board = new Board();
        this.currentPlayerIsWhite = true;
        this.playerMoves = new ArrayList<>();
    }

    public void InitGame() {
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.board.initBoard();
        this.white.setPieces(this.board.getWhitePiece());
        this.black.setPieces(this.board.getBlackPiece());
    }

    public void action(boolean isWhitePlayer, Piece piece, Cell endCell) {
        //Faire l'action de bouger
        ChessPlayer player = isWhitePlayer ? this.white : this.black;
    }

    public boolean move(String playerId, String pieceId, String endCellId) {
        Cell endCell = Cell.board.get(endCellId);
        ChessPlayer player = getPlayerFromString(playerId);
        if (player == null) {
            return false;
        }
        if (player.isWhite()!= currentPlayerIsWhite){
            return false;
        }
        Piece piece = getPieceFromPlayer(player, pieceId);
        if (endCell == null || piece == null) {
            return false;
        }
        boolean haveMove = move(player, piece, endCell);
        if (haveMove) {
            updateGameStatus();
        }

        return haveMove;
    }

    private void updateGameStatus() {
        this.currentPlayerIsWhite = !currentPlayerIsWhite;
        if (this.board.getBlackKing() == null) {
            this.gameStatus = GameStatus.COMPLETED;
        } else if (this.board.getWhiteKing() == null) {
            this.gameStatus = GameStatus.COMPLETED;
        }

    }

    public boolean move(ChessPlayer player, Piece piece, Cell endCell) {
        //TODO verifier que le move est valide
        Cell startCell = piece.getCell();
        if (piece.isWhite() == player.isWhite()) {
            if (endCell.isVoid()) {
                return false;
            }
            if (this.playerMoves.contains(endCell)) {
                if (endCell.isEmpty()) {
                    return this.move(piece, startCell, endCell);
                } else {
                    Piece endCellPiece = endCell.getCurrentPiece();
                    if (piece.isWhite() != endCellPiece.isWhite()) {
                        endCellPiece.destroy();
                        this.board.removePiece(endCellPiece);
                        return this.move(piece, startCell, endCell);
                    }
                }
            }
        }
        return false;
    }

    public List<Cell> getMoves(String playerId, String pieceId, String cellId) {
        ChessPlayer player = getPlayerFromString(playerId);
        if (player == null) {
            return null;
        }
        Piece piece = getPieceFromPlayer(player, pieceId);
        this.playerMoves = getMoves(piece);
        return this.playerMoves;
    }

    public List<Cell> getMoves(Piece piece) {
        return piece.getValidesMoves();
    }

    private ChessPlayer getPlayerFromString(String playerId) {
        ChessPlayer player = null;
        if (white.getId().equals(playerId)) {
            player = white;
        } else if (black.getId().equals(playerId)) {
            player = black;
        }
        return player;
    }

    private Piece getPieceFromPlayer(ChessPlayer player, String pieceId) {
        return player.getPieces().stream().filter(piece1 -> piece1.getId().equals(pieceId)).findFirst().orElse(null);
    }

    private boolean move(Piece piece, Cell start, Cell end) {
        piece.setCell(end);
        start.setCurrentPiece(null);
        end.setCurrentPiece(piece);
        return true;
    }


    public ChessPlayer getWhite() {
        return white;
    }

    public void setWhite(ChessPlayer white) {
        this.white = white;
    }

    public ChessPlayer getBlack() {
        return black;
    }

    public void setBlack(ChessPlayer black) {
        this.black = black;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getGameid() {
        return gameid;
    }

    public boolean isCurrentPlayerIsWhite() {
        return currentPlayerIsWhite;
    }
}
