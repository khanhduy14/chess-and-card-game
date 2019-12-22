package vn.com.duykk.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vn.com.duykk.constant.ChessCode;

@Data
@Slf4j
public class Move extends ChessEntity{
  private int xTo;
  private int yTo;
  private Boolean validateStatus;

  public static Move of(int xFrom, int yFrom, int xTo, int yTo, ChessCode chessCode) {
    Move move = new Move();
    move.setChessCode(chessCode);
    move.setXTo(xTo);
    move.setX(xFrom);
    move.setYTo(yTo);
    move.setY(yFrom);
    move.setValidateStatus(validateMove(move));
    return move;
  }

  private static boolean validateMove(Move move) {
    // TODO: Add more case
    switch (move.getChessCode()) {
      case BISHOP:
        return Math.abs(move.getY() - move.getYTo()) == Math.abs(move.getX() - move.getXTo());
      default:
        return false;
    }
  }
}
