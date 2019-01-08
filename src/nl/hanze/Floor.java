package nl.hanze;

public class Floor {

    private FloorEnum type;
    private int id;

    public Floor(FloorEnum type, int id) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
