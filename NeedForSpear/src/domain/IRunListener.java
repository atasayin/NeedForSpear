package domain;
import java.util.HashMap;


public interface IRunListener {

        void onRunEvent(HashMap<String, Integer> runSettings, String username, String id);
    }


