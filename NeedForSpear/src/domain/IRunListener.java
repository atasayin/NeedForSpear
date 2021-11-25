package domain;
import java.util.HashMap;


public interface IRunListener {

        void onClickEvent(HashMap<String, Double> runSettings, String username);
    }


