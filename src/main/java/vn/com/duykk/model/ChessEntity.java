package vn.com.duykk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import vn.com.duykk.constant.ChessCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChessEntity {

  private ChessCode chessCode;

  @Min(0)
  @Max(7)
  private int x;

  @Min(0)
  @Max(7)
  private int y;
}
