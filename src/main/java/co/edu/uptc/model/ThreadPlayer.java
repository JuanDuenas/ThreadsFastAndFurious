package co.edu.uptc.model;

import co.edu.uptc.logic.Controller;

import javax.swing.*;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ThreadPlayer implements Runnable{
    private String zoneId;
    private ZonedDateTime zdt;
    private DateTimeFormatter dtf;
    private String fdt;
    private JLabel JLHour;



    public ThreadPlayer(String zoneId){
        this.zoneId = zoneId;
        this.fdt = "";
        JLHour = new JLabel();
    }
    @Override
    public void run() {

        try{
            while(true){
                zdt = ZonedDateTime.now(ZoneId.of(zoneId));
                dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                fdt = zdt.format(dtf);
                JLHour.setText(fdt);

                Thread.sleep(1000);

            }
        }catch (InterruptedException ex){
            ex.getMessage();
        }

    }



    public JLabel getJLHour(){
        return JLHour;
    }

    public String getFdt() {
        return fdt;
    }
}

