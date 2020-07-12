package API.Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static API.Utils.Constans.getPathToResouces;

public class YmlWriter {

    private Map<String, Map<String, Map<String, String>>> category = new HashMap<>();
    private Map<String, Map<String, String>> productId = new HashMap<>();
    private Map<String, String> productData;

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

        Yaml yaml = new Yaml();
        FileWriter writer = null;
        try {
            writer = new FileWriter(getPathToResouces(fileName));
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


}
