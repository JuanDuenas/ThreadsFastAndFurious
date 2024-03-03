package co.edu.uptc.model;

import co.edu.uptc.logic.Controller;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ThreadPlayer implements Runnable{
    private String zoneId;
    private ZonedDateTime zdt;
    private DateTimeFormatter dtf;


    public ThreadPlayer(String zoneId){
        this.zoneId = zoneId;
    }
    @Override
    public void run() {

        try{
            while(true){
                zdt = ZonedDateTime.now(ZoneId.of(zoneId));
                dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                String fdt = zdt.format(dtf);

                System.out.println(fdt+" en "+zoneId);
                Thread.sleep((int) (Math.random()*5000) +2000);
            }
        }catch (InterruptedException ex){
            ex.getMessage();
        }

    }

}

