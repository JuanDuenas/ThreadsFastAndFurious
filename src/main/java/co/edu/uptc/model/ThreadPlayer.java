package co.edu.uptc.model;

import java.time.LocalDateTime;
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
        zdt = ZonedDateTime.now(ZoneId.of(zoneId));
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String fdt = zdt.format(dtf);

        System.out.println(fdt+" en "+zoneId);
    }
}
