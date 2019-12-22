package vn.com.duykk.constant;

public enum CardRank {
  TWO((byte)2, "Two"),
  THREE((byte)3, "Three"),
  FOUR((byte)4, "Four"),
  FIVE((byte)5, "Five"),
  SIX((byte)6, "Six"),
  SEVEN((byte)7, "Seven"),
  EIGHT((byte)8, "Eight"),
  NINE((byte)9, "Nine"),
  TEN((byte)10, "Ten"),
  JACK((byte)11, "Jack"),
  QUEEN((byte)12, "Queen"),
  KING((byte)13, "King"),
  ACE((byte)14, "Ace");

  private final byte rankValue;
  private final String rankValueName;

  CardRank(byte rankValue, String rankValueName){
    this.rankValue=rankValue;
    this.rankValueName = rankValueName;
  }

  public byte getRankValue(){
    return rankValue;
  }

  public String getRankValueName(){
    return rankValueName;
  }
}
