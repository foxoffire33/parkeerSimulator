package nl.hanze.enums;

public enum FloorType {
     FLOOR_TYPE_NONE(1),
    FLOOR_TYPE_MENBER(0),
    FLOOR_TYPE_RESAVERED(2);
     //FLOOR_TYPE_TEST(3);

     private int value;

    FloorType(int number){
         this.value = number;
     }

    public int getValue() {
        return value;
    }
}
