package nl.hanze.Windows;

import nl.hanze.Main;
import nl.hanze.controllers.FloorController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InformationPanel extends JPanel {

    public static JLabel spots1, spots2, spots3, spots4, space1, queue1, queue2, queue3, space2, profitg, profitm, profitr, profitT, space3,
            dubble1, dubble2, dubble3, dubble4, dubblet, space4, loss1, loss2, loss3, loss4, loss5, loss6, space5, space6, title1, title2, title3, title4, title5, title6,
            title7;

    public InformationPanel(FloorController controller) {
        super();


        JPanel panel = new JPanel();
        panel.setLayout(null);

        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(boxLayout);
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        InformationPanel.title4 = new JLabel();
        panel.add(InformationPanel.title4);
        InformationPanel.spots1 = new JLabel();
        panel.add(InformationPanel.spots1);
        InformationPanel.spots1 = new JLabel();
        panel.add(InformationPanel.spots1);
        InformationPanel.spots2 = new JLabel();
        panel.add(InformationPanel.spots2);
        InformationPanel.spots3 = new JLabel();
        panel.add(InformationPanel.spots3);
        InformationPanel.spots4 = new JLabel();
        panel.add(InformationPanel.spots4);



        InformationPanel.space1 = new JLabel();
        panel.add(InformationPanel.space1);
        InformationPanel.title5 = new JLabel();
        panel.add(InformationPanel.title5);
        InformationPanel.queue1 = new JLabel();
        panel.add(InformationPanel.queue1);
        InformationPanel.queue2 = new JLabel();
        panel.add(InformationPanel.queue2);
        InformationPanel.queue3 = new JLabel();
        panel.add(InformationPanel.queue3);


        InformationPanel.space3 = new JLabel(" ");
        panel.add(InformationPanel.space3);

        InformationPanel.title7 = new JLabel();
        panel.add(InformationPanel.title7);
        InformationPanel.dubble1 = new JLabel();
        panel.add(InformationPanel.dubble1);
        InformationPanel.dubble2 = new JLabel();
        panel.add(InformationPanel.dubble2);
        InformationPanel.dubble3 = new JLabel();
        panel.add(InformationPanel.dubble3);
        InformationPanel.dubble4 = new JLabel();
        panel.add(InformationPanel.dubble4);
        InformationPanel.dubblet = new JLabel();
        panel.add(InformationPanel.dubblet);

        InformationPanel.space4 = new JLabel(" ");
        panel.add(InformationPanel.space4);
        InformationPanel.title2 = new JLabel();
        panel.add(InformationPanel.title2);
        InformationPanel.loss5 = new JLabel();
        panel.add(InformationPanel.loss5);

        InformationPanel.space5 = new JLabel();
        panel.add(InformationPanel.space5);
        InformationPanel.title1 = new JLabel();
        panel.add(InformationPanel.title1);


        InformationPanel.loss1 = new JLabel();
        panel.add(InformationPanel.loss1);
        InformationPanel.loss2 = new JLabel();
        panel.add(InformationPanel.loss2);
        InformationPanel.loss3 = new JLabel();
        panel.add(InformationPanel.loss3);
        InformationPanel.loss4 = new JLabel();
        panel.add(InformationPanel.loss4);


        InformationPanel.space6 = new JLabel("");
        panel.add(InformationPanel.space6);
        InformationPanel.title3 = new JLabel();
        panel.add(InformationPanel.title3);
        InformationPanel.loss6 = new JLabel();
        panel.add(InformationPanel.loss6);
        InformationPanel.space2 = new JLabel(" ");
        panel.add(InformationPanel.space2);

        InformationPanel.title6 = new JLabel();
        panel.add(InformationPanel.title6);

        InformationPanel.profitm = new JLabel();
        panel.add(InformationPanel.profitm);


        InformationPanel.profitg = new JLabel();
        panel.add(InformationPanel.profitg);
        InformationPanel.profitr = new JLabel();
        panel.add(InformationPanel.profitr);
        InformationPanel.profitT = new JLabel();
        panel.add(InformationPanel.profitT);

        panel.setMinimumSize(new Dimension(250, 600));
        panel.setMaximumSize(new Dimension(250, 600));

        this.setSize(new Dimension(200,500));


        this.add(scrollPane);
        setVisible(true);

    }
}
