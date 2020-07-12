package API.Utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

import static API.Utils.Constans.getPathToResouces;

public class JsonReader {

    public JSONObject getDataJSON(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        try {
            FileReader file = new FileReader(getPathToResouces(fileName));

            object = (JSONObject) parser.parse(file);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        return object;
    }
}
