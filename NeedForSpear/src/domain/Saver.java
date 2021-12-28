package domain;

import domain.obstacle.*;
import util.PosVector;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Saver {

    String FILEPATH = "src/saves/Demo1.json";


    public void saveGame(HashMap<Obstacle, PosVector> list) {
        Document doc = new Document();
        ArrayList<String> temp;
        ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
        doc.put("PaddlePositionX", Game.getInstance().getPaddle().getPosVector().x);
        doc.put("PaddlePositionY", Game.getInstance().getPaddle().getPosVector().y);
        doc.put("BallPositionX", Game.getInstance().getBall().posVector.x);
        doc.put("BallPositionY", Game.getInstance().getBall().posVector.y);
        doc.put("Username", Game.getInstance().gameState.getPlayer().getUserName());
        doc.put("ChancePoints", Game.getInstance().gameState.getPlayer().getChance_points());
        doc.put("Score", Game.getInstance().getOldScore());
        doc.put("ChanceGivingAbility",Game.getInstance().gameState.getPlayer().getAbilities().get(1));
        doc.put("PaddleExpansionAbility",Game.getInstance().gameState.getPlayer().getAbilities().get(2));
        doc.put("UnstoppableAbility",Game.getInstance().gameState.getPlayer().getAbilities().get(3));
        doc.put("RocketAbility",Game.getInstance().gameState.getPlayer().getAbilities().get(4));


        for (Entry<Obstacle, PosVector> o : list.entrySet()) {
            temp = new ArrayList<String>();
            temp.add(String.valueOf(o.getKey()));
            temp.add(String.valueOf(o.getValue()));
            map.add(temp);
        }
        doc.put("ObjectList", map);

        try {
            FileWriter file = new FileWriter(FILEPATH);
            file.write(doc.toJson());
            file.close();
            System.out.println("Saved to local directory successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(Paddle paddle, Ball ball) {

        HashMap<Obstacle, PosVector> loadObsPos = new HashMap<>();
        Layout.setObstacle_positions(loadObsPos);

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(FILEPATH)) {
            Object obj = jsonParser.parse(reader);
            JSONObject doc = (JSONObject) obj;
            long z = (long) doc.get("PaddlePositionX");
            int x = ((int) z);
            long t = (long)  doc.get("PaddlePositionY");
            int y = ((int) t);
            PosVector paddleLoc = new PosVector(x, y);
            long c = (long) doc.get("ChancePoints");
            String user = (String) doc.get("Username");
            Game.getInstance().gameState.setChance((int)c);
            long f = (long) doc.get("Score");
            Game.getInstance().gameState.getPlayer().setUserName(user);
            Game.getInstance().gameState.getPlayer().setScore((int)f);
            Game.getInstance().setScore((int)f);
            Game.getInstance().getPaddle().setPosVector(paddleLoc);
            z = (long) doc.get("BallPositionX");
            t = (long) doc.get("BallPositionY");
            x = ((int) z);
            y = ((int) t);

            z = (long) doc.get("ChanceGivingAbility");
            Game.getInstance().gameState.getPlayer().getAbilities().put(1,(int)z);

            z = (long) doc.get("PaddleExpansionAbility");
            Game.getInstance().gameState.getPlayer().getAbilities().put(2,(int)z);

            z = (long) doc.get("UnstoppableAbility");
            Game.getInstance().gameState.getPlayer().getAbilities().put(3,(int)z);

            z = (long) doc.get("RocketAbility");
            Game.getInstance().gameState.getPlayer().getAbilities().put(4,(int)z);


            PosVector ballLoc = new PosVector(x, y);
            ball.setPosVector(ballLoc);

            JSONArray jsonlist = (JSONArray) doc.get("ObjectList");

            Map<Obstacle, PosVector> list = new HashMap<>();
            ArrayList<DomainObject> listDO = new ArrayList<DomainObject>();
            int obsLen = Game.getInstance().getPaddle().length/5;
            PosVector vec;

            for (int i = 0; i < jsonlist.size(); i++) {

                String s = jsonlist.get(i).toString();

                int a = s.indexOf("x");
                int b = s.indexOf(",");
                int p = (Integer.parseInt(s.substring(a + 2, b)));

                int k = s.indexOf("y");
                int l = s.indexOf("}");
                int m = (Integer.parseInt(s.substring(k + 2, l)));

                if(s.contains("WallMaria")){
                    WallMaria obs = new WallMaria(p, m);
                    listDO.add(obs);
                    vec = new PosVector(p,m);
                    Layout.getObstacle_positions().put(obs,vec);
                }
                if(s.contains("GiftOfUranus")){
                    GiftOfUranus obs = new GiftOfUranus(p, m);
                    listDO.add(obs);
                    vec = new PosVector(p,m);
                    Layout.getObstacle_positions().put(obs,vec);
                }
                if(s.contains("PandorasBox")){
                    PandorasBox obs = new PandorasBox(p, m);
                    listDO.add(obs);
                    vec = new PosVector(p,m);
                    Layout.getObstacle_positions().put(obs,vec);
                }
                if(s.contains("SteinsGate")){
                    SteinsGate obs = new SteinsGate(p, m);
                    listDO.add(obs);
                    vec = new PosVector(p,m);
                    Layout.getObstacle_positions().put(obs,vec);
                }
            }

            Game.getInstance().setDomainObjectArr(listDO);
            System.out.println("Loaded from local directory successfully.");

        } catch (FileNotFoundException e) {


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

