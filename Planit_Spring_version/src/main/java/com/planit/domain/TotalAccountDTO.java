package com.planit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


@Data
@EqualsAndHashCode(callSuper=true)
public class TotalAccountDTO extends SCDTO{
   public TotalAccountDTO() {
      super();
   }
      
   public TotalAccountDTO(int scnum, int catnum, int contnum, ArrayList<AccountRowDTO> a, AccountDTO b) {
      super(scnum, catnum, contnum);
      this.a = a;
      this.b = b;
   }
   ArrayList<AccountRowDTO> a = new ArrayList<AccountRowDTO>();
   private AccountDTO b;
   public ArrayList<AccountRowDTO> getA() {
      return a;
   }
   public void setA(ArrayList<AccountRowDTO> a) {
      this.a = a;
   }
   public AccountDTO getB() {
      return b;
   }
   public void setB(AccountDTO b) {
      this.b = b;
   }
   
//   @Override
//   public String toString() {
//      return "TotalAccountDTO [AccountRowDTO=" + a + ", AccountDTO=" + b + "]";
//   }
//   
}
