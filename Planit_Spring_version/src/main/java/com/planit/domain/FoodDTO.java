package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class FoodDTO extends SCDTO{
   private String foodTime;
   private int foodScore;
   private String foodContents;
   public FoodDTO() {}
   public FoodDTO(int scnum, int catnum, int contnum, String foodTime, int foodScore, String foodContents) {
      super(scnum, catnum, contnum);
      this.foodTime = foodTime;
      this.foodScore = foodScore;
      this.foodContents = foodContents;
   }

}