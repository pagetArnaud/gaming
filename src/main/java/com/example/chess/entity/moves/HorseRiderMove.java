package com.example.chess.entity.moves;

import com.example.chess.entity.Cell;
import com.example.chess.entity.Move;
import com.example.chess.entity.Piece;

import java.util.ArrayList;
import java.util.List;

public class HorseRiderMove implements Move {
    @Override
    public List<Cell> getValidMoves(Piece piece, Cell start) {
        List<Cell> moves = new ArrayList<>(8);
        Cell temp = null;
        if (!start.getTop().isVoid()){
            temp = start.getTop();
            if(temp.getTopL().canLandHere(piece)){
                moves.add(temp.getTopL());
            }
            if(temp.getTopR().canLandHere(piece)){
                moves.add(temp.getTopR());
            }
        }
        if (!start.getRight().isVoid()){
            temp = start.getRight();
            if(temp.getBotR().canLandHere(piece)){
                moves.add(temp.getBotR());
            }
            if(temp.getTopR().canLandHere(piece)){
                moves.add(temp.getTopR());
            }
        }

        if (!start.getBot().isVoid()){
            temp = start.getBot();
            if(temp.getBotL().canLandHere(piece)){
                moves.add(temp.getBotL());
            }
            if(temp.getBotR().canLandHere(piece)){
                moves.add(temp.getBotR());
            }
        }

        if (!start.getLeft().isVoid()){
            temp = start.getLeft();
            if(temp.getBotL().canLandHere(piece)){
                moves.add(temp.getBotL());
            }
            if(temp.getTopL().canLandHere(piece)){
                moves.add(temp.getTopL());
            }
        }

        return moves;
    }
}
