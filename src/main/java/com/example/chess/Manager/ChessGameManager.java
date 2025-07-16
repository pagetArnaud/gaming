package com.example.chess.Manager;

import com.example.chess.entity.ChessGame;
import com.example.chess.entity.ChessPlayer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ChessGameManager {
    /**
     * Map of active Tic-Tac-Toe games, with the game ID as the key.
     */
    private final Map<String, ChessGame> games;

    /**
     * Map of players waiting to join a Tic-Tac-Toe game, with the player's name as the key.
     */
    protected final Queue<ChessGame> chessGameQueue;


    /**
     * Constructs a new ChessGameManager.
     */
    public ChessGameManager() {
        games = new ConcurrentHashMap<>();
        chessGameQueue = new ConcurrentLinkedQueue<ChessGame>();
    }

    /**
     * Attempts to add a player to an existing Tic-Tac-Toe game, or creates a new game if no open games are available.
     *
     * @param player the name of the player
     * @return the Tic-Tac-Toe game the player was added to
     */
    public synchronized ChessGame joinGame(String player) {
        ChessGame chessGame = chessGameQueue.poll();
        if (chessGame!=null){

            chessGame.setBlack(new ChessPlayer(false,player));
            games.put(chessGame.getGameid(), chessGame);
            chessGame.InitGame();
        }else {
            chessGame = new ChessGame(new ChessPlayer(true,player),null);
            chessGameQueue.add(chessGame);
        }
        return chessGame;
    }



    /**
     * Returns the Tic-Tac-Toe game with the given game ID.
     *
     * @param gameId the ID of the game
     * @return the Tic-Tac-Toe game with the given game ID, or null if no such game exists
     */
    public ChessGame getGame(String gameId) {
        return games.get(gameId);
    }

    /**
     * Returns the Tic-Tac-Toe game the given player is in.
     *
     * @param player the name of the player
     * @return the Tic-Tac-Toe game the given player is in, or null if the player is not in a game
     */
    public ChessGame getGameByPlayer(String player) {
        return games.values().stream().filter(game -> game.getWhite().equals(player) || (game.getBlack() != null &&
                game.getBlack().equals(player))).findFirst().orElse(null);
    }

    /**
     * Removes the Tic-Tac-Toe game with the given game ID.
     *
     * @param gameId the ID of the game to remove
     */
    public void removeGame(String gameId) {
        games.remove(gameId);
    }

}
