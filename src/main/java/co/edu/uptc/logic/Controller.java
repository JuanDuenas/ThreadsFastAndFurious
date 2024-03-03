package co.edu.uptc.logic;

import co.edu.uptc.model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Controller {
    private List<Player> listPlayers;


    public Controller(){
        listPlayers = new ArrayList<>();
    }

    public List<Player> getListPlayers() {
        return listPlayers;
    }

    public void loadValues() {

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
}
