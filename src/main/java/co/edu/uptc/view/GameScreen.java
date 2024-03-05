package co.edu.uptc.view;

import co.edu.uptc.logic.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameScreen extends JFrame {
    JPanel pnlInfo;
    JPanel pnlGame;
    JLabel lblTrack;
    JLabel lblcar1,lblcar2,lblcar3,lblcar4,lblcar5;
    JLabel car;
    JLabel lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer5;
    JLabel lblTitle, lblName, lblHour, lblMatch, lblPosition, lblDiceRolls,lblLastScore,lblTotalScore, lblMissingScore;

    JButton btnPlayer1, btnPlayer2, btnPlayer3, btnPlayer4, btnPlayer5;

    JButton btnCredits;

    boolean i1 = false;
    boolean i2 = false;
    boolean i3 = false;
    boolean i4 = false;
    boolean i5 = false;

    int match = 0;
    Controller c;


    public GameScreen(int party) {
        match = party + 1;
        c = new Controller(this,party);
        c.initgame();

        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Rápidos y Furiosos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        pnlInfo = new JPanel();
        pnlGame = new JPanel();



        createPanel();

        lblName = new JLabel();
        lblMatch = new JLabel();
        lblHour = new JLabel();
        lblPosition = new JLabel();
        lblLastScore = new JLabel();
        lblMissingScore = new JLabel();
        lblTotalScore = new JLabel();
        lblDiceRolls = new JLabel();


        createButton();

        updateInfo(4);
        updateInfo(3);
        updateInfo(2);
        updateInfo(1);
        updateInfo(0);



        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void createPanel() {
        pnlInfo.setBackground(Color.black);
        pnlInfo.setBounds(0,0,300,500);
        pnlInfo.setLayout(null);
        add(pnlInfo);

        pnlGame.setBackground(Color.white);
        pnlGame.setBounds(300,0,600,500);
        pnlGame.setLayout(null);
        add(pnlGame);
    }

    private void carMovement(int score, int carIndex) {
        //Puntuación, nombre
        //UBICACIÓN INCIAL 20,20
        if (car != null) {
                pnlGame.remove(car);
            }
            if (lblTrack != null) {
                pnlGame.remove(lblTrack);
            }
            pnlGame.add(c.getListThreadLauch().get(carIndex).getJLCar());
            pnlGame.revalidate();
            pnlGame.repaint();

            createImages();

    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void updateInfo(int index) {
         /*
        AGREGAR PARÁMETROS NECESARIOS PARA OPTENER LA INFORMACIÓN DE LOS RESPECTIVOS JUGADORES
        */

        createLabel();
        lblHour.setText("");
        lblName.setText("");

        pnlInfo.remove(lblHour);
        pnlInfo.remove(lblMatch);
        pnlInfo.remove(lblPosition);
        pnlInfo.remove(lblMissingScore);
        pnlInfo.remove(lblLastScore);
        pnlInfo.remove(lblTotalScore);
        pnlInfo.remove(lblDiceRolls);

        //int points = c.getListThreadLauch().get(index).getPoints();

            lblName = new JLabel(c.getListPlayers().get(index).getName());
            lblName.setBounds(0,45,300,80);
            lblName.setForeground(Color.white);
            lblName.setHorizontalAlignment(SwingConstants.CENTER);
            lblName.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblName);

            lblHour = c.getListThreadPlayer().get(index).getJLHour();
            lblHour.setBounds(0,80,300,80);
            lblHour.setForeground(Color.decode("#FFC000"));
            lblHour.setHorizontalAlignment(SwingConstants.CENTER);
            lblHour.setFont(new Font("SansSerif", Font.PLAIN, 14));
            pnlInfo.add(lblHour);

            //lblMatch = new JLabel("PARTIDA: "+(c.getListPlayers().get(index).getNumParties()+1));
            lblMatch = new JLabel("PARTIDA: "+(match));
            lblMatch.setBounds(20,115,260,80);
            lblMatch.setForeground(Color.white);
            lblMatch.setHorizontalAlignment(SwingConstants.LEFT);
            lblMatch.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblMatch);

            lblPosition = c.getListThreadLauch().get(index).getJLPosition();
            lblPosition.setBounds(20,150,260,80);
            lblPosition.setForeground(Color.white);
            lblPosition.setHorizontalAlignment(SwingConstants.LEFT);
            lblPosition.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblPosition);

            lblDiceRolls = c.getListThreadLauch().get(index).getJLLaunchess();
            lblDiceRolls.setBounds(20,185,260,80);
            lblDiceRolls.setForeground(Color.white);
            lblDiceRolls.setHorizontalAlignment(SwingConstants.LEFT);
            lblDiceRolls.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblDiceRolls);

            lblLastScore = c.getListThreadLauch().get(index).getLastLaunch();
            lblLastScore.setBounds(20,220,260,80);
            lblLastScore.setForeground(Color.white);
            lblLastScore.setHorizontalAlignment(SwingConstants.LEFT);
            lblLastScore.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblLastScore);

            lblTotalScore = c.getListThreadLauch().get(index).getJLPoints();
            lblTotalScore.setBounds(20,255,260,80);
            lblTotalScore.setForeground(Color.white);
            lblTotalScore.setHorizontalAlignment(SwingConstants.LEFT);
            lblTotalScore.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblTotalScore);

            lblMissingScore = c.getListThreadLauch().get(index).getJLFaltPoints();
            lblMissingScore.setBounds(20,290,260,80);
            lblMissingScore.setForeground(Color.white);
            lblMissingScore.setHorizontalAlignment(SwingConstants.LEFT);
            lblMissingScore.setFont(new Font("SansSerif", Font.BOLD, 14));
            pnlInfo.add(lblMissingScore);

            pnlInfo.revalidate();
            pnlInfo.repaint();
            validateCar(index);


    }

    private void validateCar(int index){
        switch (index) {
            case 0:
                if(!i1){
                    carMovement(c.getListThreadLauch().get(index).getPoints(),index);
                }
                i1 = true;
            case 1:
                if(!i2){
                    carMovement(c.getListThreadLauch().get(index).getPoints(),index);
                    i2 = true;
                }

            case 2:
                if(!i3){
                    i3 = true;
                    carMovement(c.getListThreadLauch().get(index).getPoints(),index);

                }

            case 3:
                if(!i4){
                    carMovement(c.getListThreadLauch().get(index).getPoints(),index);
                    i4 = true;
                }

            case 4:
                if(!i5){
                    carMovement(c.getListThreadLauch().get(index).getPoints(),index);
                    i5 = true;
                }
        }

    }

    private void createLabel() {

        lblPlayer1 = new JLabel(c.getListPlayers().get(0).getName());
        lblPlayer1.setBounds(10, 20, 90, 30);
        lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer1);

        lblPlayer2 = new JLabel(c.getListPlayers().get(1).getName());
        lblPlayer2.setBounds(120, 20, 90, 30);
        lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer2);

        lblPlayer3 = new JLabel(c.getListPlayers().get(2).getName());
        lblPlayer3.setBounds(240, 20, 90, 30);
        lblPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer3);

        lblPlayer4 = new JLabel(c.getListPlayers().get(3).getName());
        lblPlayer4.setBounds(360, 20, 90, 30);
        lblPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer4);

        lblPlayer5 = new JLabel(c.getListPlayers().get(4).getName());
        lblPlayer5.setBounds(480, 20, 90, 30);
        lblPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer5);

        //ETIQUETAS DE INFORMACIÓN

        lblTitle = new JLabel("IFORMACIÓN DEL JUGADOR");
        lblTitle.setBounds(0,10,300,80);
        lblTitle.setForeground(Color.white);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        pnlInfo.add(lblTitle);
    }
    private void createButton() {

        btnCredits = new JButton("Acerca de...");
        btnCredits.setBounds(95, 400, 100, 40);
        btnCredits.setBackground(Color.white);
        btnCredits.setForeground(Color.black);
        btnCredits.setBorder(new LineBorder(Color.black, 1));
        pnlInfo.add(btnCredits);

        btnCredits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen mn = new MainScreen();
            }
        });

        btnPlayer1 = new JButton("Mostrar");
        btnPlayer1.setBounds(10, 110, 90, 40);
        btnPlayer1.setBackground(Color.white);
        btnPlayer1.setForeground(Color.black);
        btnPlayer1.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer1);


        btnPlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if(o.equals(btnPlayer1)){


                    updateInfo(0);
                }
            }
        });

        btnPlayer2 = new JButton("Mostrar");
        btnPlayer2.setBounds(120, 110, 90, 40);
        btnPlayer2.setBackground(Color.white);
        btnPlayer2.setForeground(Color.black);
        btnPlayer2.setBorder(new LineBorder(Color.black, 1));

        pnlGame.add(btnPlayer2);
        btnPlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if (o.equals(btnPlayer2)) {

                    updateInfo(1);


                }
            }
        });

        btnPlayer3 = new JButton("Mostrar");
        btnPlayer3.setBounds(240, 110, 90, 40);
        btnPlayer3.setBackground(Color.white);
        btnPlayer3.setForeground(Color.black);
        btnPlayer3.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer3);

        btnPlayer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if (o.equals(btnPlayer3)) {

                    updateInfo(2);
                }
            }
        });

        btnPlayer4 = new JButton("Mostrar");
        btnPlayer4.setBounds(360, 110, 90, 40);
        btnPlayer4.setBackground(Color.white);
        btnPlayer4.setForeground(Color.black);
        btnPlayer4.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer4);

        btnPlayer4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if (o.equals(btnPlayer4)) {

                    updateInfo(3);
                }
            }
        });

        btnPlayer5 = new JButton("Mostrar");
        btnPlayer5.setBounds(480, 110, 90, 40);
        btnPlayer5.setBackground(Color.white);
        btnPlayer5.setForeground(Color.black);
        btnPlayer5.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer5);

        btnPlayer5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if (o.equals(btnPlayer5)) {
                    updateInfo(4);
                }
            }
        });
    }
    private void createImages(){

        //CarrosIcono
        try {
            Image car1Image = ImageIO.read(new File("Image/Car1.png"));
            ImageIcon car1Icon = new ImageIcon(car1Image);
            lblcar1 = new JLabel(car1Icon);
            lblcar1.setBounds(10, 50, 90, 50);

            Image car2Image = ImageIO.read(new File("Image/Car2.png"));
            ImageIcon car2Icon = new ImageIcon(car2Image);
            lblcar2 = new JLabel(car2Icon);
            lblcar2.setBounds(120, 50, 90, 50);

            Image car3Image = ImageIO.read(new File("Image/Car3.png"));
            ImageIcon car3Icon = new ImageIcon(car3Image);
            lblcar3 = new JLabel(car3Icon);
            lblcar3.setBounds(240, 50, 90, 50);

            Image car4Image = ImageIO.read(new File("Image/Car4.png"));
            ImageIcon car4Icon = new ImageIcon(car4Image);
            lblcar4 = new JLabel(car4Icon);
            lblcar4.setBounds(360, 50, 90, 50);

            Image car5Image = ImageIO.read(new File("Image/Car5.png"));
            ImageIcon car5Icon = new ImageIcon(car5Image);
            lblcar5 = new JLabel(car5Icon);
            lblcar5.setBounds(480, 50, 90, 50);

            pnlGame.add(lblcar1);
            pnlGame.add(lblcar2);
            pnlGame.add(lblcar3);
            pnlGame.add(lblcar4);
            pnlGame.add(lblcar5);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Pista
        try {
            Image trackImage = ImageIO.read(new File("Image/TrackBG.png"));
            ImageIcon trackIcon = new ImageIcon(trackImage);
            lblTrack = new JLabel(trackIcon);
            lblTrack.setBounds(-5, 165, 600, 300);
            pnlGame.add(lblTrack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
