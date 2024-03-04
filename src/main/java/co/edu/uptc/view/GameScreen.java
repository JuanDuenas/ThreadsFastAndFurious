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

    Controller c;


    public GameScreen() {
        setSize(900, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Rápidos y Furiosos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        pnlInfo = new JPanel();
        pnlGame = new JPanel();



        createPanel();

        carMovement(32/*Pasar parámetro de imágen dependiendo del botón*/);

        createLabel();
        //createImages();
        createButton();

        updateInfo();

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

    private void carMovement(int score) {
        //Puntuación, nombre
        //UBICACIÓN INCIAL 20,20


        try {
            if (car != null) {
                pnlGame.remove(car);
            }
            if (lblTrack != null) {
                pnlGame.remove(lblTrack);
            }

            Image car1Image = ImageIO.read(new File("Image/Car1.png"));
            ImageIcon car1Icon = new ImageIcon(car1Image);
            car = new JLabel(car1Icon);


            double rotationDegrees = 0;
            int xPosition = 0;
            int yPosition = 0;

            if(score >= 0 && score< 6){
                xPosition = 220 + (score*40);
                yPosition = 220;
            } else if(score >= 6 && score <= 10) {
                if(score == 6){
                    rotationDegrees = 30;
                    xPosition = 450;
                    yPosition = 230;
                } else if(score == 7){
                    rotationDegrees = 60;
                    xPosition = 465;
                    yPosition = 260;
                }
                else if(score == 8){
                    rotationDegrees = 90;
                    xPosition = 475;
                    yPosition = 285;
                }
                else if(score == 9){
                    rotationDegrees = 120;
                    xPosition = 465;
                    yPosition = 310;
                }
                else if(score == 10){
                    rotationDegrees = 150;
                    xPosition = 450;
                    yPosition = 325;
                }
            } else if(score >= 11 && score <= 20) {
                rotationDegrees = 180;
                xPosition = 700 - (score*30);
                yPosition = 340;
            } else if(score >= 21 && score <= 25) {
                if(score == 21){
                    rotationDegrees = 210;
                    xPosition = 50;
                    yPosition = 330;
                } else if(score == 22){
                    rotationDegrees = 240;
                    xPosition = 30;
                    yPosition = 300;
                }
                else if(score == 23){
                    rotationDegrees = 270;
                    xPosition = 25;
                    yPosition = 270;
                }
                else if(score == 24){
                    rotationDegrees = 300;
                    xPosition = 30;
                    yPosition = 240;
                }
                else if(score == 25) {
                    rotationDegrees = 335;
                    xPosition = 560;
                    yPosition = 235;
                }
            } else  if(score >= 26 && score <= 32){
                xPosition = -420 + (score*20);
                yPosition = 220;
            }

            car.setBounds(xPosition, yPosition, 90, 90);


            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(rotationDegrees), car.getWidth() / 2.0, car.getHeight() / 2.0);

            // Crear una imagen transformada
            BufferedImage rotatedImage = new BufferedImage(car.getWidth(), car.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rotatedImage.createGraphics();
            g2d.setTransform(transform);
            g2d.drawImage(car1Image, 0, 0, null);
            g2d.dispose();

            // Crear un nuevo ícono a partir de la imagen rotada
            ImageIcon rotatedIcon = new ImageIcon(rotatedImage);
            car.setIcon(rotatedIcon);
            car.setVisible(true);
            pnlGame.add(car);

            pnlGame.revalidate();
            pnlGame.repaint();

            createImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void updateInfo() {
         /*
        AGREGAR PARÁMETROS NECESARIOS PARA OPTENER LA INFORMACIÓN DE LOS RESPECTIVOS JUGADORES
        */
        c = new Controller();
        c.initgame();


            lblName = new JLabel(c.getListPlayers().get(0).getName());
            lblName.setBounds(0,45,300,80);
            lblName.setForeground(Color.white);
            lblName.setHorizontalAlignment(SwingConstants.CENTER);
            lblName.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblName);

            lblHour = c.getListThreadPlayer().get(0).getJLHour();
            lblHour.setBounds(0,80,300,80);
            lblHour.setForeground(Color.decode("#FFC000"));
            lblHour.setHorizontalAlignment(SwingConstants.CENTER);
            lblHour.setFont(new Font("SansSerif", Font.PLAIN, 15));
            pnlInfo.add(lblHour);

            lblMatch = new JLabel("PARTIDA: "+c.getListPlayers().get(0).getNumParties());
            lblMatch.setBounds(20,115,260,80);
            lblMatch.setForeground(Color.white);
            lblMatch.setHorizontalAlignment(SwingConstants.LEFT);
            lblMatch.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblMatch);

            lblPosition = new JLabel("POSICIÓN:"+c.getListThreadLauch().get(0).getPoints());
            lblPosition.setBounds(20,150,260,80);
            lblPosition.setForeground(Color.white);
            lblPosition.setHorizontalAlignment(SwingConstants.LEFT);
            lblPosition.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblPosition);

            lblDiceRolls = new JLabel("LANZAMIENTOS: "+c.getListThreadLauch().get(0).getCountLauchess());
            lblDiceRolls.setBounds(20,185,260,80);
            lblDiceRolls.setForeground(Color.white);
            lblDiceRolls.setHorizontalAlignment(SwingConstants.LEFT);
            lblDiceRolls.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblDiceRolls);

            lblLastScore = new JLabel("ULTIMO PUNTAJE: ");
            lblLastScore.setBounds(20,220,260,80);
            lblLastScore.setForeground(Color.white);
            lblLastScore.setHorizontalAlignment(SwingConstants.LEFT);
            lblLastScore.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblLastScore);

            lblTotalScore = c.getListThreadLauch().get(0).getJLPoints();
            lblTotalScore.setBounds(20,255,260,80);
            lblTotalScore.setForeground(Color.white);
            lblTotalScore.setHorizontalAlignment(SwingConstants.LEFT);
            lblTotalScore.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblTotalScore);

            lblMissingScore = new JLabel("PUNTAJE FALTANTE: "+(200 - c.getListPlayers().get(0).getPoints()));
            lblMissingScore.setBounds(20,290,260,80);
            lblMissingScore.setForeground(Color.white);
            lblMissingScore.setHorizontalAlignment(SwingConstants.LEFT);
            lblMissingScore.setFont(new Font("SansSerif", Font.BOLD, 15));
            pnlInfo.add(lblMissingScore);


    }
    private void createLabel() {

        lblPlayer1 = new JLabel("Jugador 1");
        lblPlayer1.setBounds(10, 20, 90, 30);
        lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer1);

        lblPlayer2 = new JLabel("Jugador 2");
        lblPlayer2.setBounds(120, 20, 90, 30);
        lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer2);

        lblPlayer3 = new JLabel("Jugador 3");
        lblPlayer3.setBounds(240, 20, 90, 30);
        lblPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer3);

        lblPlayer4 = new JLabel("Jugador 4");
        lblPlayer4.setBounds(360, 20, 90, 30);
        lblPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
        pnlGame.add(lblPlayer4);

        lblPlayer5 = new JLabel("Jugador 5");
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
        btnPlayer1 = new JButton("Mostrar");
        btnPlayer1.setBounds(10, 110, 90, 40);
        btnPlayer1.setBackground(Color.white);
        btnPlayer1.setForeground(Color.black);
        btnPlayer1.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer1);

        btnPlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carMovement((int)(Math.random()*10));
            }
        });

        btnPlayer2 = new JButton("Mostrar");
        btnPlayer2.setBounds(120, 110, 90, 40);
        btnPlayer2.setBackground(Color.white);
        btnPlayer2.setForeground(Color.black);
        btnPlayer2.setBorder(new LineBorder(Color.black, 1));;
        pnlGame.add(btnPlayer2);

        btnPlayer3 = new JButton("Mostrar");
        btnPlayer3.setBounds(240, 110, 90, 40);
        btnPlayer3.setBackground(Color.white);
        btnPlayer3.setForeground(Color.black);
        btnPlayer3.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer3);

        btnPlayer4 = new JButton("Mostrar");
        btnPlayer4.setBounds(360, 110, 90, 40);
        btnPlayer4.setBackground(Color.white);
        btnPlayer4.setForeground(Color.black);
        btnPlayer4.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer4);

        btnPlayer5 = new JButton("Mostrar");
        btnPlayer5.setBounds(480, 110, 90, 40);
        btnPlayer5.setBackground(Color.white);
        btnPlayer5.setForeground(Color.black);
        btnPlayer5.setBorder(new LineBorder(Color.black, 1));
        pnlGame.add(btnPlayer5);
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
