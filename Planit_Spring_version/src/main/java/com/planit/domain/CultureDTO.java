package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CultureDTO extends SCDTO{
private String cultitle;
   private String cultype;
   private String culgenre;
   private int culscore;
   private String culcontents;
   
   public CultureDTO(){
      
   }
   public CultureDTO(int scnum, int catnum, int contnum, String cultitle, String cultype, String culgenre,
            int culscore, String culcontents) {
         super(scnum, catnum, contnum);
         this.cultitle = cultitle;
         this.cultype = cultype;
         this.culgenre = culgenre;
         this.culscore = culscore;
         this.culcontents = culcontents;
      }
}