package co.edu.uptc.model;

import co.edu.uptc.logic.Controller;

import javax.swing.*;

public class ThreadLauch implements Runnable{
    private int points;
    private JLabel JLPoints;
    private int countLauchess;
    public ThreadLauch(){
        points = 0;
        countLauchess = 0;
        JLPoints = new JLabel();
    }
    @Override
    public void run() {
        while(true){
            try {
                Controller c = new Controller();
                points += c.makeLaunch();
                countLauchess++;
                JLPoints.setText("PUNTAJE: "+points);
                Thread.sleep((int) (Math.random()*10000) +5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
}
