package nl.hanze;

public enum FloorEnum {
     FLOOR_TYPE_NONE(1),
    FLOOR_TYPE_MENBER(2),
    FLOOR_TYPE_RESAVERED(2);

     private int returnValue;

     FloorEnum(int number){
         this.returnValue = number;
     }
}
