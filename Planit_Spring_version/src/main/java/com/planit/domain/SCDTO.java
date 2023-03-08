package com.planit.domain;

import lombok.Data;

@Data
public class SCDTO {
   private int scnum;
   private int catnum;
   private int contnum;
   
   public SCDTO(int scnum, int catnum, int contnum) {
      super();
      this.scnum = scnum;
      this.catnum = catnum;
      this.contnum = contnum;
   }

   public SCDTO() {
      // TODO Auto-generated constructor stub
   }

}