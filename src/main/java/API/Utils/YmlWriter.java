package API.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class YmlWriter {

    private Map<String, Map<String, Map<String, String>>> category = new HashMap<>();
    private Map<String, Map<String, String>> productId = new HashMap<>();
    private Map<String, String> productData;
    private String absolutePath = System.getProperty("user.dir");
    private String pathToBookingId = "";

    public void addCategory(String name, Map<String, Map<String, String>> productID) {
        category.put(name, productID);
        productId = new HashMap<>();
    }

    public void addProductId(String id, Map<String, String> data) {
        productId.put(id, data);
    }

    public void addProductData(String title, String oldPrice, String newPrice) {
        productData = new HashMap<>();
        productData.put("Title", title);
        productData.put("Old Price", oldPrice);
        productData.put("New Price", newPrice);
    }

    public Map<String, Map<String, Map<String, String>>> getCategory() {
        return category;
    }

    public Map<String, Map<String, String>> getProductId() {
        return productId;
    }

    public Map<String, String> getProductData() {
        return productData;
    }

    public void setData(Map<?, ?> data, String fileName) {

        pathToBookingId = (absolutePath + "/src/main/resources/" + fileName).replaceAll("/", Matcher.quoteReplacement(File.separator));
        Yaml yaml = new Yaml();
        FileWriter writer = null;
        try {
            writer = new FileWriter(pathToBookingId);
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void setDataJSON(Map<?, ?> data, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        pathToBookingId = (absolutePath + "/src/main/resources/" + fileName).replaceAll("/", Matcher.quoteReplacement(File.separator));
        try {
            objectMapper.writeValue(
                    new FileOutputStream(pathToBookingId), data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
