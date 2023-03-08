package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class MemoDTO extends SCDTO{
   String memocontents;
   
   public MemoDTO(){
   }
   public MemoDTO(int scnum, int catnum, int contnum, String memocontents) {
      super(scnum, catnum, contnum);
      this.memocontents = memocontents;
   }
   
   
}