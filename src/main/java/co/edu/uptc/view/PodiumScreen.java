package co.edu.uptc.view;

import co.edu.uptc.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class PodiumScreen extends JFrame {


    JPanel pnlBackground;
    private JLabel JLTitle;
    public PodiumScreen(List<Player> playerList) {

        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Rápidos y Furiosos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLTitle = new JLabel("CLASIFICACIÓN");

        JLTitle.setBounds(250,30,100,50);


        pnlBackground = new JPanel();
        pnlBackground.setBounds(0,0,600,400);
        pnlBackground.setBackground(Color.white);
        pnlBackground.setLayout(null);
        add(pnlBackground);
        pnlBackground.add(JLTitle);

        int i = 0;
        for(Player p : playerList){
            generateInformation(p, i);
            i++;
        }

        setVisible(true);
    }

    private void generateInformation(Player p, int i) {
        JLabel position = new JLabel();
        position.setText((i+1)+". "+p.getName());

        if(i == 0){
            position.setBounds(10,100,200,20);
        }
        if(i == 1){
            position.setBounds(10,130,200,20);
        }
        if(i == 2){
            position.setBounds(10,160,200,20);
        }
        if(i == 3){
            position.setBounds(10,190,200,20);
        }
        if(i == 4){
            position.setBounds(10,220,200,20);
        }


        position.setForeground(Color.decode("#119000"));
        position.setFont(new Font("SansSerif", Font.BOLD, 20));

        pnlBackground.add(position);
    }

}
