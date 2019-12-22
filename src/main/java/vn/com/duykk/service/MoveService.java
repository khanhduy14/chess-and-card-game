package vn.com.duykk.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.duykk.constant.ChessCode;
import vn.com.duykk.model.Move;
import vn.com.duykk.model.Pos;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MoveService {

  private static final Scanner in = new Scanner(System.in);

  public boolean validateBishopMove() {
    log.info("Nhap toa do");
    int x = in.nextInt();
    int y = in.nextInt();
    int xTo = in.nextInt();
    int yTo = in.nextInt();
    Move move = Move.of(x, y, xTo, yTo, ChessCode.BISHOP);
    return move.getValidateStatus();
  }

  public void findShortestPath() {
    log.info("Nhap toa do");
    int x = in.nextInt();
    int y = in.nextInt();
    int xTo = in.nextInt();
    int yTo = in.nextInt();
    log.info("Nhap toa do cac quan can (VD : 2,3;3,4)");
    String blockString = in.next();
    String[] blockList = blockString.split(";");
    List<Pos> blockPos = new ArrayList<>();
    for (String string : blockList) {
      Pos pos = Pos.of(Integer.parseInt(string.split(",")[0]), Integer.parseInt(string.split(",")[1]));
      blockPos.add(pos);
    }
    if (Math.abs(x - xTo) % 2 != Math.abs(y - yTo) % 2) {
      log.warn("Not exist path");
      return;
    }

    Pos currentPos = Pos.of(x, y);
    Pos targetPos = Pos.of(xTo, yTo);

    Integer targetPosInt = posToInT(targetPos);

    Multimap<Integer, Integer> resultMap = searchBFS(currentPos, targetPos, blockPos);

    List<Integer> path = new ArrayList<>();
    path.add(targetPosInt);
    int tempPostInt = targetPosInt;
    while (!resultMap.get(tempPostInt).isEmpty()) {
      List<Integer> pathTemp = new ArrayList<>(resultMap.get(tempPostInt));
      path.add(pathTemp.get(pathTemp.size() - 1));
      tempPostInt = pathTemp.get(pathTemp.size() - 1);
    }
    this.showPath(path);
  }

  private void showPath(List<Integer> path) {
    for (int i = path.size() - 1; i >= 0; i--) {
      log.info("Step {} : {}", path.size() - i, intToPos(path.get(i)).toString());
    }
  }

  private Multimap<Integer, Integer> searchBFS(Pos currentPos, Pos targetPos, List<Pos> blockPos) {
    Pos startPos = Pos.of(0,0);
    Map<Integer, List<Integer>> verticesMap = new HashMap<>();
    Multimap<Integer, Integer> resultMap = ArrayListMultimap.create();
    if (Math.abs(currentPos.getX() - targetPos.getX()) % 2 != Math.abs(currentPos.getY() - targetPos.getY()) % 2) {
      startPos.setX(1);
    }
    int targetPosInt = posToInT(targetPos);

    for (int i = 0; i < 32; i ++) {
      int startPosInt = posToInT(startPos);
      if (!(startPos.getX() < 0 || startPos.getX() > 7 || startPos.getY() < 0 || startPos.getY() > 7)) {
        verticesMap.put(startPosInt, findAllPossibleMove(blockPos, startPos));
      }
      startPos.setX(startPos.getX() + 2);
      if (startPos.getX() > 7) {
        int x = startPos.getX() % 2 == 0 ? 1 : 0;
        startPos.setX(x);
        startPos.setY(startPos.getY() + 1);
      }
    }

    List<Integer> processList = new ArrayList<>();
    processList.add(posToInT(currentPos));
    List<Integer> exploredList = new ArrayList<>();
    exploredList.add(posToInT(currentPos));

    while (!processList.isEmpty()) {
      int posInt = processList.get(0);
      processList.remove(0);
      List<Integer> possibleMoveIntList = verticesMap.get(posInt);
      if (possibleMoveIntList == null) {
        continue;
      }
      for (Integer possibleMoveInt : possibleMoveIntList) {
        if (!exploredList.contains(possibleMoveInt)) {
          resultMap.put(possibleMoveInt, posInt);
          exploredList.add(possibleMoveInt);
          if (targetPosInt == possibleMoveInt) {
            break;
          } else {
            processList.add(possibleMoveInt);
          }
        }
      }
    }
    return resultMap;
  }

  private int posToInT(Pos pos) {
    return pos.getY() * 10 + pos.getX();
  }

  private Pos intToPos(int posInt) {
    int y = posInt/10;
    return Pos.of(posInt - y*10, y);
  }

  private List<Integer> findAllPossibleMove(List<Pos> blockPos, Pos currentPos) {
    List<Integer> possibleMove = new ArrayList<>();
    List<Pos> posList = Pos.initStandardPos();
    List<Boolean> isActiveList = posList.stream().map(Pos::getIsActive).collect(Collectors.toList());
    int shift = 1;
    List<Integer> blockPosX = blockPos.stream().map(Pos::getX).collect(Collectors.toList());
    List<Integer> blockPosY = blockPos.stream().map(Pos::getY).collect(Collectors.toList());
    while (isActiveList.contains(true)) {
      for (Pos pos : posList) {
        if (pos.getIsActive()) {
          Pos possiblePos = addPos(currentPos, pos, shift);
          if (possiblePos.getX() < 0 || possiblePos.getX() > 7
                  || possiblePos.getY() < 0 || possiblePos.getY() > 7
                  || (blockPosX.contains(possiblePos.getX()) && blockPosY.contains(possiblePos.getY()))) {
            pos.setIsActive(false);
          } else {
            possibleMove.add(posToInT(possiblePos));
          }
        }
        isActiveList = posList.stream().map(Pos::getIsActive).collect(Collectors.toList());
      }
      shift++;
    }
    return possibleMove;
  }

  private Pos addPos(Pos pos, Pos addPos, int shift) {
    Pos newPos = new Pos();
    newPos.setX(pos.getX() + shift * addPos.getX());
    newPos.setY(pos.getY() + shift * addPos.getY());
    return newPos;
  }
}
