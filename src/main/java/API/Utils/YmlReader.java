package API.Utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class YmlReader {

    String absolutePath = System.getProperty("user.dir");
    String pathToBookingId = "";

    public HashMap<?, ?> getData(String fileName) {
        FileInputStream stream = null;
        pathToBookingId = (absolutePath + "/src/main/resources/" + fileName).replaceAll("/", Matcher.quoteReplacement(File.separator));
        HashMap<?, ?> object = null;
        Yaml yaml;
        try {
            File file = new File(pathToBookingId);
            stream = new FileInputStream(file);
            yaml = new Yaml();
            object = yaml.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stream != null)
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return object;
    }

    public JSONObject getDataJSON(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        JSONObject object=null;

        pathToBookingId = (absolutePath+"/src/main/resources/" + fileName).replaceAll("/", Matcher.quoteReplacement(File.separator));
        try {
            FileReader file = new FileReader(pathToBookingId);

            object= (JSONObject) parser.parse(file);
        } catch (IOException|ParseException e){
            e.printStackTrace();
        }


        return object;
    }

}
