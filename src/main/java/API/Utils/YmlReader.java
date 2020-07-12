package API.Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static API.Utils.Constans.getPathToResouces;

public class YmlReader {

    public HashMap<?, ?> getData(String fileName) {
        FileInputStream stream = null;
        HashMap<?, ?> object = null;
        Yaml yaml;
        try {
            File file = new File(getPathToResouces(fileName));
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

}
