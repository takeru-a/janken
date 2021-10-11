package oit.is.z1017.kaizi.janken.model;

public class Janken{
  String cpu_hand = "gu";
  String result;
  String hand;
  public Janken(){
  }
  public void setHand(String hand){
    this.hand = hand;
  }
  public String getResult(){
    if (this.hand.equals("pa")){
      this.result = "WIN";
   }else if(this.hand.equals("gu")){
      this.result = "DRAW";
    }else{
      this.result = "LOST";
    }
    return this.result;
  }
  public String getCpuhand(){
    return this.cpu_hand;
  }
}
