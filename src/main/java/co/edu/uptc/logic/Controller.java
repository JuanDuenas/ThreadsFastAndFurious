package co.edu.uptc.logic;

import co.edu.uptc.model.Player;
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

    public void initgame(){
        loadValues();

        List<ThreadPlayer> listThreadPlayer = new ArrayList<>();

        for (Player p: listPlayers){
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
