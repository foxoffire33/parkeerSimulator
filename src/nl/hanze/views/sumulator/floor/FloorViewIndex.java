package nl.hanze.views.sumulator.floor;

import nl.hanze.models.FloorModel;

import javax.swing.*;
import java.awt.*;

public class FloorViewIndex extends JPanel {

    public FloorViewIndex() {
        this.setBackground(Color.BLUE);
        this.setMinimumSize(new Dimension(1000, 100));
    }

    public FloorViewIndex(FloorModel model){
        System.out.println(model.toString());
    }

}
