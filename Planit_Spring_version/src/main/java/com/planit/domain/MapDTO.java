package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper=true)
public class MapDTO extends SCDTO{
   private String latitude;
   private String longitude;
   
   public MapDTO(int scnum, int catnum, int contnum, String latitude, String longitude) {      
      super(scnum, catnum, contnum);
      this.latitude = latitude;
      this.longitude = longitude;
   }

   public MapDTO() {
   }
   
}