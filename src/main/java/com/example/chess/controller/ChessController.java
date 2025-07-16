package com.example.chess.controller;

import com.example.chess.GameStatus;
import com.example.chess.Manager.ChessGameManager;
import com.example.chess.dto.CellDTO;
import com.example.chess.dto.GameMoveDTO;
import com.example.chess.dto.GameUpdateDTO;
import com.example.chess.dto.initDTO;
import com.example.chess.entity.Cell;
import com.example.chess.entity.ChessGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/chess")
public class ChessController {

    @Autowired
    private ChessGameManager gameManager;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/join")
    public ResponseEntity<initDTO> joinGame(@RequestBody String playerName) {
        System.out.println("Player try to join :" + playerName);
        ChessGame game = gameManager.joinGame(playerName);
        initDTO output;
        if (game.getBlack() != null) {
            output = new initDTO(game.getGameid(), false);
        } else {
            output = new initDTO(game.getGameid(), true);
        }
        return ResponseEntity.ok(output);
    }

    @MessageMapping("/game/{gameId}/move") // Receives from client
    public void handleMove(@DestinationVariable String gameId, GameMoveDTO move) {
        // Optional: validate against GameManager
        System.out.println("Move for game " + gameId + ": " + move);
        ChessGame game = gameManager.getGame(gameId);
        if (game != null) {
            if (game.move(move.playerId(), move.pieceId(), move.endCellId())) {

            } else {

            }
        } else {

        }
        // Broadcast only to players in that game
        List<CellDTO> changedCell = parseCell(game);
        notifyGameChange(gameId, new GameUpdateDTO(game.getGameStatus().getValue(), game.isCurrentPlayerIsWhite(), changedCell));
        game.getBoard().clearChangeCell();
        if (game.getGameStatus() == GameStatus.COMPLETED) {
            gameManager.removeGame(gameId);
        }
    }

    @MessageMapping("/game/{gameId}/init")
    public void sendInitialGameState(@DestinationVariable String gameId) {

        ChessGame game = gameManager.getGame(gameId);
        if (game == null) return;
        List<CellDTO> changedCell = parseCell(game);
        notifyGameChange(gameId, new GameUpdateDTO(game.getGameStatus().getValue(), game.isCurrentPlayerIsWhite(), changedCell));
    }


    @MessageMapping("/game/{gameId}/requestMoves")
    @SendToUser("/queue/game/legalMoves")
    public List<CellDTO> handleMoveRequest(@DestinationVariable String gameId, GameMoveDTO request, Principal principal) {
        ChessGame game = gameManager.getGame(gameId);
        if (game == null) return null;
        //TODO check si joueur a le droit de bouger la piece
        List<CellDTO> legalMoves = parseCell(game.getMoves(request.playerId(), request.pieceId(), request.startCellId()));
        return legalMoves;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

    private void notifyGameChange(String gameId, GameUpdateDTO gameUpdateDTO) {
        messagingTemplate.convertAndSend("/topic/game/" + gameId + "/gameUpdate", gameUpdateDTO);
    }

    private List<CellDTO> parseCell(ChessGame chessGame) {
        return parseCell(chessGame.getBoard().getChangeCell());
    }

    private List<CellDTO> parseCell(Collection<Cell> cells) {
        if (cells != null) {

            return cells.stream().map(CellDTO::new).toList();
        } else {
            return null;
        }
    }

}
