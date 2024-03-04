package co.edu.uptc.model;

import co.edu.uptc.logic.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadLauch implements Runnable{
    private int points;
    private JLabel JLPoints;
    private JLabel JLFaltPoints;
    private JLabel JLLaunchess;
    private int countLauchess;

    private JLabel JLPosition;

    private JLabel JLCar;

    private JLabel JLLastLaunch;
    private int carIndex;

    private Player playerObjct;

    ArrayList<Player> podiumList;
    private int lastLaunch;
    private boolean stop;
    private Controller c;

    public ThreadLauch(int carIndex, Player objct,Controller c){
        stop = true;
        podiumList = new ArrayList<>();

        playerObjct = objct;
        this.c = c;
        this.carIndex = carIndex;
        points = 0;
        countLauchess = 0;
        lastLaunch = 0;
        JLPoints = new JLabel();
        JLFaltPoints = new JLabel();
        JLLastLaunch = new JLabel();
        JLLaunchess = new JLabel();
        JLCar = new JLabel();
        JLPosition = new JLabel();

    }

    public boolean isStop() {
        return stop;
    }

    @Override
    public void run() {
        boolean p1 = false;

        while(stop){
            try {


                lastLaunch = c.makeLaunch();
                points = points+lastLaunch;
                countLauchess +=1;
                JLPoints.setText("PUNTAJE: "+points);
                JLFaltPoints.setText("PUNTAJE FALTANTE: "+(33-points));
                JLLastLaunch.setText("ULTIMO LANZAMIENTO: "+lastLaunch);
                JLLaunchess.setText("LANZAMIENTOS: "+countLauchess);
                JLPosition.setText("POSICIÓN: "+points);

                Image car1Image = null;



                if(carIndex == 0) {
                    car1Image = ImageIO.read(new File("Image/Car1.png"));
                } if(carIndex == 1) {
                    car1Image = ImageIO.read(new File("Image/Car2.png"));
                } if(carIndex == 2) {
                    car1Image = ImageIO.read(new File("Image/Car3.png"));
                } if(carIndex == 3) {
                    car1Image = ImageIO.read(new File("Image/Car4.png"));
                } if(carIndex == 4) {
                    car1Image = ImageIO.read(new File("Image/Car5.png"));
                }

                if(this.getPoints() > 32){
                    stopThread();
                    c.podium();
                }
                ImageIcon car1Icon = new ImageIcon(car1Image);

                double rotationDegrees = 0;
                int xPosition = 0;
                int yPosition = 0;

                if(points >= 0 && points< 6){
                    xPosition = 220 + (points*40);
                    yPosition = 220;
                } else if(points >= 6 && points <= 10) {
                    if(points == 6){
                        rotationDegrees = 30;
                        xPosition = 450;
                        yPosition = 230;
                    } else if(points == 7){
                        rotationDegrees = 60;
                        xPosition = 465;
                        yPosition = 260;
                    }
                    else if(points == 8){
                        rotationDegrees = 90;
                        xPosition = 475;
                        yPosition = 285;
                    }
                    else if(points == 9){
                        rotationDegrees = 120;
                        xPosition = 465;
                        yPosition = 310;
                    }
                    else if(points == 10){
                        rotationDegrees = 150;
                        xPosition = 450;
                        yPosition = 325;
                    }
                } else if(points >= 11 && points <= 20) {
                    rotationDegrees = 180;
                    xPosition = 700 - (points*30);
                    yPosition = 340;
                } else if(points >= 21 && points <= 25) {
                    if(points == 21){
                        rotationDegrees = 210;
                        xPosition = 50;
                        yPosition = 330;
                    } else if(points == 22){
                        rotationDegrees = 240;
                        xPosition = 30;
                        yPosition = 300;
                    }
                    else if(points == 23){
                        rotationDegrees = 270;
                        xPosition = 25;
                        yPosition = 270;
                    }
                    else if(points == 24){
                        rotationDegrees = 300;
                        xPosition = 30;
                        yPosition = 240;
                    }
                    else if(points == 25) {
                        rotationDegrees = 335;
                        xPosition = 560;
                        yPosition = 235;
                    }
                } else  if(points >= 26 && points <= 32){
                    xPosition = -420 + (points*20);
                    yPosition = 220;
                } else  if(points > 32){
                    xPosition = 2000;
                    yPosition = 2000;
                    for(Player p: podiumList) {
                        if(p.equals(playerObjct)) {
                            p1 = true;
                        }
                    }
                    if(!p1) {
                        podiumList.add(playerObjct);
                        JOptionPane.showMessageDialog(null,playerObjct.getName() + " Ha completado el circuíto!");
                    }
                    p1=false;
                }

                JLCar.setBounds(xPosition, yPosition, 90, 90);


                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.toRadians(rotationDegrees), JLCar.getWidth() / 2.0, JLCar.getHeight() / 2.0);

                // Crear una imagen transformada
                BufferedImage rotatedImage = new BufferedImage(JLCar.getWidth(), JLCar.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = rotatedImage.createGraphics();
                g2d.setTransform(transform);
                g2d.drawImage(car1Image, 0, 0, null);
                g2d.dispose();

                // Crear un nuevo ícono a partir de la imagen rotada
                ImageIcon rotatedIcon = new ImageIcon(rotatedImage);
                JLCar.setIcon(rotatedIcon);
                JLCar.setVisible(true);
                Thread.sleep((int) (Math.random()*2000) +1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopThread(){
        stop = false;
    }
    public JLabel getJLPosition() {
        return JLPosition;
    }

    public JLabel getJLCar() {
        return JLCar;
    }

    public JLabel getJLLaunchess() {
        return JLLaunchess;
    }

    public JLabel getJLFaltPoints() {
        return JLFaltPoints;
    }

    public int getCountLauchess() {
        return countLauchess;
    }

    public JLabel getJLPoints() {
        return JLPoints;
    }

    public int getPoints() {
        return points;
    }

    public JLabel getLastLaunch() {
        return JLLastLaunch;
    }
}
