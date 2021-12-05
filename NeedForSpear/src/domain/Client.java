package domain;

import domain.controller.PaddleController;
import domain.obstacle.*;
import domain.util.PosVector;
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


public class Client {
    String username = "Player";
    //String FILEPATH = "src/saves/" + username + ".json";
    String FILEPATH = "C:/Users/MetincanOya/Desktop/deneme.json";
    private Object WallMaria;
    //String FILEPATH = "../saves/2.json";


    public void saveGame(PaddleController pc, Ball ball, HashMap<Obstacle, PosVector> list) {
        //username = Game.getInstance().gameState.getPlayers().get(0).getName();
        //FILEPATH = "src/saves/" + "2" + ".json";
        Document doc = new Document();
        ArrayList<String> temp;
        ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
        doc.put("PaddlePositionX", pc.getPaddle().getPosVector().x);
        doc.put("PaddlePositionY", pc.getPaddle().getPosVector().y);
        doc.put("BallPositionX", ball.posVector.x);
        doc.put("BallPositionY", ball.posVector.y);
        //doc.put(" Object List: ", list);

        for (Entry<Obstacle, PosVector> o : list.entrySet()) {
            temp = new ArrayList<String>();
            temp.add(String.valueOf(o.getKey()));
            temp.add(String.valueOf(o.getValue()));
            map.add(temp);
        }
        doc.put("ObjectList", map);
        //doc.put("username", username);
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

    public void loadGame(PaddleController pc, Ball ball) {
        //username = Game.getInstance().gameState.getPlayers().get(0).getName();
        //FILEPATH = "src/saves/" + username + ".json";
        JSONParser jsonParser = new JSONParser();
        String FILEPATH = "C:/Users/MetincanOya/Desktop/deneme.json";
        try (FileReader reader = new FileReader(FILEPATH)) {
            Object obj = jsonParser.parse(reader);
            JSONObject doc = (JSONObject) obj;
            long z = (long) doc.get("PaddlePositionX");
            int x = ((int) z);
            long t = (long)  doc.get("PaddlePositionY");
            int y = ((int) t);
            PosVector paddleLoc = new PosVector(x, y);

            pc.getPaddle().setPosVector(paddleLoc);
            z = (long) doc.get("BallPositionX");
            t = (long) doc.get("BallPositionY");
            x = ((int) z);
            y = ((int) t);

            PosVector ballLoc = new PosVector(x, y);
            ball.setPosVector(ballLoc);
            System.out.println(x + "  " + y);


            JSONArray jsonlist = (JSONArray) doc.get("ObjectList");

            Map<Obstacle, PosVector> list = new HashMap<>();
            ArrayList<DomainObject> listDO = new ArrayList<DomainObject>();
            System.out.println(jsonlist.get(1));

            for (int i = 0; i < jsonlist.size(); i++) {

                String s = jsonlist.get(i).toString();

                int a = s.indexOf("x");
                int b = s.indexOf(",");
                int p = (Integer.parseInt(s.substring(a + 2, b)));
                System.out.println(p);

                int k = s.indexOf("y");
                int l = s.indexOf("}");
                int m = (Integer.parseInt(s.substring(k + 2, l)));
                System.out.println(m);

                if(s.contains("WallMaria")){
                    WallMaria obs = new WallMaria(p, m, 10, 1);
                    listDO.add(obs);
                }
                if(s.contains("GiftOfUranus")){
                    GiftOfUranus obs = new GiftOfUranus(p, m, 10, 1);
                    listDO.add(obs);
                }
                if(s.contains("PandorasBox")){
                    PandorasBox obs = new PandorasBox(p, m, 10, 1);
                    listDO.add(obs);
                }
                if(s.contains("SteinsGate")){
                    SteinsGate obs = new SteinsGate(p, m, 10, 1);
                    listDO.add(obs);
                }
                //System.out.println(jsonlist.get(i)[0]);
                //System.out.println(jsonlist.get(i).getClass());
                //Obstacle o = (Obstacle)jsonlist.get(i) ;
                //PosVector pos = ((Obstacle) (jsonlist.get(i))).posVector ;
                //list.put(o, pos);
            }

            //HashMap<Obstacle, PosVector> list = (HashMap<Obstacle, PosVector>) doc.get("ObjectList");



            for (Entry<Obstacle, PosVector> l : list.entrySet()) {
                String s = l.getKey().getType();

                switch (s) {
                    case "WallMaria":
                        int a = l.getValue().getX();
                        int b = l.getValue().getY();
                        int c = l.getKey().health;
                        WallMaria obs = new WallMaria(a, b, 10, c);
                        listDO.add(obs);
                        break;
                    case "GiftOfUranus":
                        a = l.getValue().getX();
                        b = l.getValue().getY();
                        c = l.getKey().health;
                        GiftOfUranus obs2 = new GiftOfUranus(a, b, 10, c);
                        listDO.add(obs2);
                        break;
                    case "PandorasBox":
                        a = l.getValue().getX();
                        b = l.getValue().getY();
                        c = l.getKey().health;
                        PandorasBox obs3 = new PandorasBox(a, b, 10, c);
                        listDO.add(obs3);
                        break;
                    case "SteinsGate":
                        a = l.getValue().getX();
                        b = l.getValue().getY();
                        c = l.getKey().health;
                        SteinsGate obs4 = new SteinsGate(a, b, 10, c);
                        listDO.add(obs4);
                        break;

                    default:
                        break;

                }

            }

            Game.getInstance().setDomainObjectArr(listDO);
            System.out.println("Loaded from local directory.");

        } catch (FileNotFoundException e) {


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

