package API.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static API.Utils.Constans.getPathToResouces;

public class JsonWriter {

    public void setDataJSON(Map<?, ?> data, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(
                    new FileOutputStream(getPathToResouces(fileName)), data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

