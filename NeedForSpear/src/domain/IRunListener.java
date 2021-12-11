package domain;
import java.util.HashMap;


public interface IRunListener {

        void onClickEvent(HashMap<String, Integer> runSettings, String username, String id);
    }


