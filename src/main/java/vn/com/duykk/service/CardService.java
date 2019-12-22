package vn.com.duykk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.duykk.constant.CardRank;
import vn.com.duykk.constant.CardType;
import vn.com.duykk.model.CardEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class CardService {
  public List<CardEntity> sortCard(List<CardEntity> cardEntityList) {
    Collections.sort(cardEntityList);
    return cardEntityList;
  }

  public List<CardEntity> dealCard() {
    List<CardEntity> allCard = new ArrayList<>();
    List<CardEntity> cardInHand = new ArrayList<>();
    for (CardRank cardRank : CardRank.values()) {
      for (CardType cardType : CardType.values()) {
        allCard.add(CardEntity.of(cardRank, cardType));
      }
    }
    Random rand = new Random();
    List<Integer> tempList = new ArrayList<>();
    do {
      int randomNumber = rand.nextInt(52);
      if (!tempList.contains(randomNumber)) {
        cardInHand.add(allCard.get(randomNumber));
        tempList.add(randomNumber);
      }
    } while (cardInHand.size() != 10);
    return cardInHand;
  }
}
