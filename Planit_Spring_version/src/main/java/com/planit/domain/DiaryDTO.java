package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DiaryDTO extends SCDTO {
   private String diarydate;
      private String weather;
      private String diarytitle;
      private String diarycontents;
   
      public DiaryDTO(){}
      public DiaryDTO(int scnum, int catnum, int contnum, String weather, 
            String diarytitle, String diarycontents, String diarydate) {
         super(scnum, catnum, contnum);
         this.diarydate = diarydate;
         this.weather = weather;
         this.diarytitle = diarytitle;
         this.diarycontents = diarycontents;
      }

}