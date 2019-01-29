package nl.hanze.mvc;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JPanel {
    public abstract void paintComponent(Graphics graphics);
}
