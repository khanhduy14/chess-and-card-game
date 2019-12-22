package vn.com.duykk.model;

import lombok.Data;
import vn.com.duykk.constant.CardRank;
import vn.com.duykk.constant.CardType;

@Data
public class CardEntity implements Comparable<CardEntity> {
  private CardRank rank;
  private CardType type;

  public int compareTo(CardEntity o) {
    return Byte.compare(this.rank.getRankValue(), o.rank.getRankValue());
  }

  public static CardEntity of(CardRank rank, CardType type) {
    CardEntity cardEntity = new CardEntity();
    cardEntity.setRank(rank);
    cardEntity.setType(type);
    return cardEntity;
  }

  public String toString() {
    return this.getRank().toString() + "-" + this.getType();
  }
}
