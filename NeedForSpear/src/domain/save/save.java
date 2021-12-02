package domain.save;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import domain.obstacle.*;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.*;

public class save {

    String username = "Player";
    String FILEPATH = "src/saves/" + username + ".json";
    Obstacle wm = new WallMaria(0,0,0,0);
    Obstacle pb = new PandorasBox(0,0,0,0);
    Obstacle gu = new GiftOfUranus(0,0,0,0);
    Obstacle sg = new SteinsGate(0,0,0,0);


    public void saveGame(String username, int score, int chancePoints,HashMap<Integer, Integer> abilities,HashMap<Integer, Integer> obslist){

    }


    public void loadGame(HashMap<Integer, Integer> abilities,HashMap<Integer, Integer> obslist) {

        username = Game.getInstance().gameState.getPlayers().get(0).getUserName();
        FILEPATH = "src/saves/" + username + ".json";
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(FILEPATH)) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject doc = (JSONObject) obj;


            double score = (Double) doc.get("score");
            int health = ((Long) doc.get("health")).intValue();


            ArrayList<DomainObject> listDO = new ArrayList<DomainObject>();
            ArrayList<ArrayList<String>> listOnScreen = (ArrayList<ArrayList<String>>) doc.get("onScreen");
            HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();

            for (ArrayList<String> l : listOnScreen) {

                String s = l.get(0);

                switch (s) {

                    case "Obstacle":
                        String type = l.get(1);
                        int x = Integer.parseInt(l.get(2));
                        int y = Integer.parseInt(l.get(3));
                        float L = Float.parseFloat(l.get(4));
                        int h = Integer.parseInt(l.get(5));

                        if (type.equals("WallMaria")){
                            Obstacle a = new WallMaria(x, y, L,h);
                            listDO.add(a);
                        }

                        if (type.equals("SteinsGate")){
                            Obstacle a = new WallMaria(x, y, L,h);
                            listDO.add(a);
                        }

                        if (type.equals("PandorasBox")){
                            Obstacle a = new WallMaria(x, y, L,h);
                            listDO.add(a);
                        }

                        if (type.equals("GiftOfUranus")){
                            Obstacle a = new WallMaria(x, y, L,h);
                            listDO.add(a);
                        }

                        break;

                    case "Paddle":
                        String type1 = l.get(1);
                        double angle = Double.parseDouble(l.get(5));

                        //listDO.add(a1);
                        break;
                    case "Ball":

                        //listDO.add(a2);
                        break;
                    case "Abilities":


                        break;

                    default:
                        break;

                }

            }

            Game.getInstance().setDomainObjectArr(listDO);



            System.out.println("Loaded from local directory.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public void saveGame(ArrayList<ArrayList<String>> list, double score, HashMap<Integer, Integer> atominv,
                         HashMap<Integer, Integer> powerupinv, HashMap<Integer, Integer> moleculeTypes) {
        // TODO Auto-generated method stub
        username = Game.getInstance().gameState.getPlayers().get(0).getUserName();
        FILEPATH = "src/saves/" + username + ".json";
        Document doc = new Document();
        ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
        ArrayList<String> temp;
        ArrayList<String> shield = new ArrayList<String>();



        doc.put("username", username);

        doc.put("score", Game.getInstance().gameState.getPlayers().get(0).getPlayerState().getScore());

        doc.put("health", Game.getInstance().gameState.getPlayers().get(0).getPlayerState().getChance_points());
        doc.put("onScreen", list);


        try {
            FileWriter file = new FileWriter(FILEPATH);
            file.write(doc.toJson());
            file.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Saved to local directory.");
    }

}
