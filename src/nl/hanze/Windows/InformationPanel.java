package nl.hanze.Windows;

import nl.hanze.Main;
import nl.hanze.controllers.FloorController;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    public static JLabel spots1, spots2, spots3, spots4, space1, queue1, queue2, queue3, space2, profitg, profitm, profitr, profitT, space3,
            dubble1, dubble2, dubble3, dubble4, dubblet, space4, loss1, loss2, loss3, loss4, loss5, loss6, space5, space6, title1, title2, title3, title4, title5, title6,
    title7;

    public InformationPanel(FloorController controller) {
        super();

        JPanel informationPanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        InformationPanel.title4 = new JLabel();
        this.add(InformationPanel.title4);
        InformationPanel.spots1 = new JLabel();
        this.add(InformationPanel.spots1);
        InformationPanel.spots1 = new JLabel();
        this.add(InformationPanel.spots1);
        InformationPanel.spots2 = new JLabel();
        this.add(InformationPanel.spots2);
        InformationPanel.spots3 = new JLabel();
        this.add(InformationPanel.spots3);
        InformationPanel.spots4 = new JLabel();
        this.add(InformationPanel.spots4);

        InformationPanel.space2 = new JLabel(" ");
        this.add(InformationPanel.space2);

        InformationPanel.title6 = new JLabel();
        this.add(InformationPanel.title6);
        InformationPanel.profitm = new JLabel();
        this.add(InformationPanel.profitm);
        InformationPanel.profitg = new JLabel();
        this.add(InformationPanel.profitg);
        InformationPanel.profitr = new JLabel();
        this.add(InformationPanel.profitr);
        InformationPanel.profitT = new JLabel();
        this.add(InformationPanel.profitT);

        InformationPanel.space1 = new JLabel();
        this.add(InformationPanel.space1);
        InformationPanel.title5 = new JLabel();
        this.add(InformationPanel.title5);
        InformationPanel.queue1 = new JLabel();
        this.add(InformationPanel.queue1);
        InformationPanel.queue2 = new JLabel();
        this.add(InformationPanel.queue2);
        InformationPanel.queue3 = new JLabel();
        this.add(InformationPanel.queue3);




        InformationPanel.space3 = new JLabel(" ");
        this.add(InformationPanel.space3);

        InformationPanel.title7 = new JLabel();
        this.add(InformationPanel.title7);
        InformationPanel.dubble1 = new JLabel();
        this.add(InformationPanel.dubble1);
        InformationPanel.dubble2 = new JLabel();
        this.add(InformationPanel.dubble2);
        InformationPanel.dubble3 = new JLabel();
        this.add(InformationPanel.dubble3);
        InformationPanel.dubble4 = new JLabel();
        this.add(InformationPanel.dubble4);
        InformationPanel.dubblet = new JLabel();
        this.add(InformationPanel.dubblet);

        InformationPanel.space4 = new JLabel(" ");
        this.add(InformationPanel.space4);
        InformationPanel.title2 = new JLabel();
        this.add(InformationPanel.title2);
        InformationPanel.loss5 = new JLabel();
        this.add(InformationPanel.loss5);

        InformationPanel.space5 = new JLabel();
        this.add(InformationPanel.space5);
        InformationPanel.title1 = new JLabel();
        this.add(InformationPanel.title1);


        InformationPanel.loss1 = new JLabel();
        this.add(InformationPanel.loss1);
        InformationPanel.loss2 = new JLabel();
        this.add(InformationPanel.loss2);
        InformationPanel.loss3 = new JLabel();
        this.add(InformationPanel.loss3);
        InformationPanel.loss4 = new JLabel();
        this.add(InformationPanel.loss4);



        InformationPanel.space6 = new JLabel("");
        this.add(InformationPanel.space6);
        InformationPanel.title3 = new JLabel();
        this.add(InformationPanel.title3);
        InformationPanel.loss6 = new JLabel();
        this.add(InformationPanel.loss6);

        setVisible(true);

        informationPanel.setMinimumSize(new Dimension(250,500));
        informationPanel.setPreferredSize(new Dimension(100, 100));
    }
}
