package co.edu.uptc.logic;

import co.edu.uptc.model.Player;
import co.edu.uptc.model.ThreadLauch;
import co.edu.uptc.model.ThreadPlayer;
import co.edu.uptc.view.PodiumScreen;
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

    private List<Player> listPodium;

    private boolean p1;
    private boolean p2;
    private boolean p3;
    private boolean p4;
    private boolean p5;

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
            ThreadLauch tl = new ThreadLauch(i,listPlayers.get(i),this);
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

    }

    public void podium(){
        if(!listThreadLauch.get(0).isStop()){
            if(listPodium != null){
                boolean validate = false;
                for(Player p:listPodium){
                    if(p.equals(listPlayers.get(0))){
                        validate = true;
                    }
                }
                if(!validate){
                    listPodium.add(listPlayers.get(0));
                    p1 = true;
                }
            }

        }
        if(!listThreadLauch.get(1).isStop()){
            if(listPodium != null){
                boolean validate = false;
                for(Player p:listPodium){
                    if(p.equals(listPlayers.get(1))){
                        validate = true;
                    }
                }
                if(!validate){
                    listPodium.add(listPlayers.get(1));
                    p2 = true;
                }
            }
        }
        if(!listThreadLauch.get(2).isStop()){
            if(listPodium != null){
                boolean validate = false;
                for(Player p:listPodium){
                    if(p.equals(listPlayers.get(2))){
                        validate = true;
                    }
                }
                if(!validate){
                    listPodium.add(listPlayers.get(2));
                    p3 = true;
                }
            }
        }
        if(!listThreadLauch.get(3).isStop()){
            if(listPodium != null){
                boolean validate = false;
                for(Player p:listPodium){
                    if(p.equals(listPlayers.get(3))){
                        validate = true;
                    }
                }
                if(!validate){
                    listPodium.add(listPlayers.get(3));
                    p4 = true;
                }
            }
        }
        if(!listThreadLauch.get(4).isStop()){
            if(listPodium != null){
                boolean validate = false;
                for(Player p:listPodium){
                    if(p.equals(listPlayers.get(4))){
                        validate = true;
                    }
                }
                if(!validate){
                    listPodium.add(listPlayers.get(4));
                    p5 = true;
                }
            }
        }
        validatePodium();



    }

    public void validatePodium() {
        if(p1 && p2 && p3 && p4 && p5 ){
            PodiumScreen p = new PodiumScreen(listPodium);
        }
    }


    public List<ThreadLauch> getListThreadLauch() {
        return listThreadLauch;
    }

    public List<ThreadPlayer> getListThreadPlayer() {
        return listThreadPlayer;
    }

    public Controller(){
        listPodium = new ArrayList<>();
        listPlayers = new ArrayList<>();
        listThreadLauch = new ArrayList<>();
        p1 = false;
        p2 = false;
        p3 = false;
        p4 = false;
        p5 = false;
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
