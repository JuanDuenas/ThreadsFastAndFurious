package co.edu.uptc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainScreen extends JFrame {
    JPanel pnlBackground;

    JPanel pnlWhiteBG;
    JLabel lblhead;
    JLabel lblPlayer1,lblPlayer2,lblPlayer3, lblPlayer4, lblPlayer5;
    JLabel lblmatchNum;
    JButton btnAbout;
    JButton btnPlay;
    JComboBox<Integer> matchNum;

    JTextArea txtP1, txtP2, txtP3, txtP4, txtP5;

    public MainScreen() {
        setSize(400, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Rápidos y Furiosos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        pnlBackground = new JPanel();

        createHead();
        createPanel();
        createLabel();
        createButton();
        createComboBox();
        createJTextArea();


        setVisible(true);
    }

    private void createPanel() {
        pnlBackground.setBackground(Color.black);
        pnlBackground.setBounds(0,150,400,450);
        pnlBackground.setLayout(null);
        add(pnlBackground);

        pnlWhiteBG = new JPanel();
        pnlWhiteBG.setBackground(Color.white);
        pnlWhiteBG.setBounds(0,70,400,250);
        pnlWhiteBG.setLayout(null);
        pnlBackground.add(pnlWhiteBG);
    }
    private void createLabel() {
        lblmatchNum = new JLabel("Número de Partidas");
        lblmatchNum.setBounds(25,10,250,50);
        lblmatchNum.setForeground(Color.white);
        lblmatchNum.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlBackground.add(lblmatchNum);

        lblPlayer1 = new JLabel("Jugador 1");
        lblPlayer1.setBounds(25,10,250,40);
        lblPlayer1.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlWhiteBG.add(lblPlayer1);

        lblPlayer2 = new JLabel("Jugador 2");
        lblPlayer2.setBounds(25,55,250,40);
        lblPlayer2.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlWhiteBG.add(lblPlayer2);

        lblPlayer3 = new JLabel("Jugador 3");
        lblPlayer3.setBounds(25,100,250,40);
        lblPlayer3.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlWhiteBG.add(lblPlayer3);

        lblPlayer4= new JLabel("Jugador 4");
        lblPlayer4.setBounds(25,145,250,40);
        lblPlayer4.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlWhiteBG.add(lblPlayer4);

        lblPlayer5 = new JLabel("Jugador 5");
        lblPlayer5.setBounds(25,190,250,40);
        lblPlayer5.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlWhiteBG.add(lblPlayer5);
    }

    private void createButton() {
        btnAbout = new JButton("ACERCA DE...");
        btnAbout.setBounds(25,350,140,40);
        btnAbout.setBackground(Color.white);
        btnAbout.setForeground(Color.BLACK);
        btnAbout.setFont(new Font("SansSerif", Font.BOLD, 15));
        pnlBackground.add(btnAbout);

        btnPlay = new JButton("JUGAR");
        btnPlay.setBounds(225,350,140,40);
        btnPlay.setBackground(Color.white);
        btnPlay.setForeground(Color.BLACK);
        btnPlay.setFont(new Font("SansSerif", Font.BOLD, 15));
        pnlBackground.add(btnPlay);

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GameScreen gs = new GameScreen();

            }
        });
    }
    private void createComboBox() {
        Integer[] numbers = {1, 2, 3, 4, 5};
        matchNum = new JComboBox<>(numbers);
        matchNum.setSelectedItem(0);
        matchNum.setBounds(300,20,60,30);
        pnlBackground.add(matchNum);
    }
    private void createJTextArea() {
        txtP1 = new JTextArea("Jugador 1");
        txtP1.setBounds(150,20,200,20);
        txtP1.setBorder(new LineBorder(Color.black, 1));
        pnlWhiteBG.add(txtP1);

        txtP2 = new JTextArea("Jugador 2");
        txtP2.setBounds(150,65,200,20);
        txtP2.setBorder(new LineBorder(Color.black, 1));
        pnlWhiteBG.add(txtP2);

        txtP3 = new JTextArea("Jugador 3");
        txtP3.setBounds(150,110,200,20);
        txtP3.setBorder(new LineBorder(Color.black, 1));
        pnlWhiteBG.add(txtP3);

        txtP4 = new JTextArea("Jugador 4");
        txtP4.setBounds(150,155,200,20);
        txtP4.setBorder(new LineBorder(Color.black, 1));
        pnlWhiteBG.add(txtP4);

        txtP5 = new JTextArea("Jugador 5");
        txtP5.setBounds(150,200,200,20);
        txtP5.setBorder(new LineBorder(Color.black, 1));
        pnlWhiteBG.add(txtP5);
    }

    private void createHead(){
        try {
            Image raceImage = ImageIO.read(new File("Image/RaceHead.png"));
            ImageIcon raceIcon = new ImageIcon(raceImage);
            lblhead = new JLabel(raceIcon);
            lblhead.setBounds(-10, 0, 400, 150);
            add(lblhead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
