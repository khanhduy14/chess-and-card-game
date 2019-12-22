package vn.com.duykk.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pos {
  private int x;
  private int y;
  private Boolean isActive;

  public static List<Pos> initStandardPos() {
    List<Pos> posList = new ArrayList<>();
    posList.add(of(1, 1));
    posList.add(of(1, -1));
    posList.add(of(-1, 1));
    posList.add(of(-1, -1));
    return posList;
  }

  public static Pos of(int x, int y) {
    Pos pos = new Pos();
    pos.setX(x);
    pos.setY(y);
    pos.setIsActive(true);
    return pos;
  }

  public static Pos of(int posInt) {
    Pos pos = new Pos();
    int y = posInt/10;
    pos.setX(posInt - y * 10);
    pos.setY(y);
    return pos;
  }

  public String toString() {
    return "(" + this.getX() + "," + this.getY() + ")";
  }
}
