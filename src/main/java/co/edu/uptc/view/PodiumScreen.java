package co.edu.uptc.view;

import co.edu.uptc.logic.Controller;
import co.edu.uptc.model.Player;
import co.edu.uptc.model.ThreadLauch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class PodiumScreen extends JFrame {


    JPanel pnlBackground;
    JPanel pnlHead;

    JButton JBNextPartie;
    JButton JBFinish;
    private JLabel JLTitle;
    public PodiumScreen(List<Player> playerList, List<ThreadLauch> listThreadLaunch, Controller c) {

        setSize(1000, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Rápidos y Furiosos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JBNextPartie = new JButton("SIGUIENTE PARTIDA");
        JBNextPartie.setBounds(600, 300,150,40);
        JBNextPartie.setBackground(Color.white);
        JBNextPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameScreen(c.getParties());
            }
        });

        JBFinish = new JButton("FINALIZAR");
        JBFinish.setBounds(250, 300,150,40);
        JBFinish.setBackground(Color.white);
        JBFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLTitle = new JLabel("CLASIFICACIÓN");
        JLTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        JLTitle.setBounds(0,0,1000,60);
        JLTitle.setHorizontalAlignment(JLabel.CENTER);
        JLTitle.setVerticalAlignment(JLabel.CENTER);

        pnlBackground = new JPanel();
        pnlBackground.setBounds(0,0,1000,400);
        pnlBackground.setBackground(Color.black);
        pnlBackground.setLayout(null);

        pnlHead = new JPanel();
        pnlHead.setBounds(0,0,1000,60);
        pnlHead.setBackground(Color.white);
        pnlHead.setLayout(null);

        add(pnlBackground);
        pnlBackground.add(pnlHead);
        pnlBackground.add(JBNextPartie);
        pnlBackground.add(JBFinish);
        pnlHead.add(JLTitle);


        int i = 0;
        for(Player p : playerList){
            p.setPoints(listThreadLaunch.get(i).getPoints());
            p.setLauchess(listThreadLaunch.get(i).getCountLauchess());
            p.setNumParties(c.getParties());
            generateInformation(p, i);
            i++;
        }

        setVisible(true);
    }

    private void generateInformation(Player p, int i) {
        JLabel position = new JLabel();
        position.setText("    << "+(i+1)+" >>");
        position.setBounds((20+(i*185)),80,200,20);
        position.setForeground(Color.BLACK);
        position.setFont(new Font("SansSerif", Font.BOLD, 16));

        if(i == 0){
            position.setForeground(Color.decode("#FFC900"));
        }
        if(i == 1){
            position.setForeground(Color.decode("#00FFA6"));
        }
        if(i == 2){
            position.setForeground(Color.decode("#FF5100"));
        }
        if(i == 3){
            position.setForeground(Color.decode("#CB0000"));
        }
        if(i == 4){
            position.setForeground(Color.decode("#CB0000"));
        }



        String string = "Nombre: " + p.getName() + "<br>" +
                "<br>Puntuación: "+p.getPoints() + "<br>" +
                "<br>Lanzamientos: "+p.getLauchess() + "<br>" +
                "<br>Número de partidas: "+p.getNumParties();

        JLabel info = new JLabel("<html>" + string + "</html>");
        info.setBounds((30+(i*185)),80,180,200);
        info.setForeground(Color.white);
        info.setFont(new Font("SansSerif", Font.BOLD, 14));

        pnlBackground.add(info);
        pnlBackground.add(position);
    }

}
