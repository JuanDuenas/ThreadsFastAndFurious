package co.edu.uptc.logic;

import co.edu.uptc.model.Player;
import co.edu.uptc.model.ThreadLauch;
import co.edu.uptc.model.ThreadPlayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Controller {
    private List<Player> listPlayers;
    private List<ThreadPlayer> listThreadPlayer;
    private List<ThreadLauch> listThreadLauch;

    public void initgame() {
        loadValues();

        listThreadPlayer = new ArrayList<>();

        for (Player p : listPlayers) {
            ThreadPlayer tp = new ThreadPlayer(p.getZoneId());
            listThreadPlayer.add(tp);
        }

        Thread t1 = new Thread(listThreadPlayer.get(0));
        Thread t2 = new Thread(listThreadPlayer.get(1));
        Thread t3 = new Thread(listThreadPlayer.get(2));
        Thread t4 = new Thread(listThreadPlayer.get(3));
        Thread t5 = new Thread(listThreadPlayer.get(4));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        listThreadLauch = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ThreadLauch tl = new ThreadLauch(i);
            listThreadLauch.add(tl);
        }

        Thread tp1 = new Thread(listThreadLauch.get(0));
        Thread tp2 = new Thread(listThreadLauch.get(1));
        Thread tp3 = new Thread(listThreadLauch.get(2));
        Thread tp4 = new Thread(listThreadLauch.get(3));
        Thread tp5 = new Thread(listThreadLauch.get(4));

        tp1.start();
        tp2.start();
        tp3.start();
        tp4.start();
        tp5.start();

        if(listThreadLauch.get(0).getPoints() >= 32){
            tp1.interrupt();
        }
        if(listThreadLauch.get(1).getPoints() >= 32){
            tp2.interrupt();
        }
        if(listThreadLauch.get(2).getPoints() >= 32){
            tp3.interrupt();
        }if(listThreadLauch.get(3).getPoints() >= 32){
            tp4.interrupt();
        }
        if(listThreadLauch.get(4).getPoints() >= 32){
            tp5.interrupt();
        }
    }

    public List<ThreadLauch> getListThreadLauch() {
        return listThreadLauch;
    }

    public List<ThreadPlayer> getListThreadPlayer() {
        return listThreadPlayer;
    }

    public Controller(){
        listPlayers = new ArrayList<>();
    }

    public List<Player> getListPlayers() {
        return listPlayers;
    }

    private void loadValues() {

        Gson gson = new Gson();

        String content = readFile();

        Type type = new TypeToken<ArrayList<Player>>(){}.getType();
        listPlayers = gson.fromJson(content, type);


    }

    private String readFile(){
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/data.json"), Charset.defaultCharset()));{
                String line = null;
                StringBuilder output = new StringBuilder();
                while((line = input.readLine()) != null){
                    output.append(line);
                }
                return output.toString();
            }
        }catch (IOException e){
            e.getMessage();
        }
        return null;
    }

    public int makeLaunch(){
        int value = ((int) (Math.random()*6)+1) + ((int) (Math.random()*6)+1);
        return value;
    }
}
