package API;

import API.Utils.YmlReader;
import API.Utils.YmlWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApiActions extends HTTPMethods {

    String uri = "https://d.joinhoney.com/v3";

    public void getCollectionsByUserIdForQualifiersPopular() {
        YmlWriter ymlWriter = new YmlWriter();
        Map<String, String> queryParams = new QueryParams().collectionsByUserIdForQualifiersPopular();
        ValidatableResponse response = get(uri, queryParams);
        String category;
        String title;
        String priceList;
        String priceCurrent;

        int sizeOfCateg = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular.size()");
        for (int i = 0; i < sizeOfCateg; i++) {
            int coutProduct = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular[" + i + "].products.size()");
            category = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular[" + i + "].title").toString();
            String id;
            for (int j = 0; j < coutProduct; j++) {

                id = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular[" + i + "].products[" + j + "].productId").toString();
                title = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular[" + i + "].products[" + j + "].title").toString();
                priceList = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular[" + i + "].products[" + j + "].priceList").toString();
                priceCurrent = response.extract().body().path("data.getCollectionsByUserIdForQualifiersPopular[" + i + "].products[" + j + "].priceCurrent").toString();

                ymlWriter.addProductData(title, priceList, priceCurrent);
                ymlWriter.addProductId(id, ymlWriter.getProductData());
            }
            ymlWriter.addCategory(category, ymlWriter.getProductId());
        }
//        ymlWriter.setData(ymlWriter.getCategory(),"getCollectionsByUserIdForQualifiersPopular.yml");
        ymlWriter.setDataJSON(ymlWriter.getCategory(), "getCollectionsByUserIdForQualifiersPopular.json");
    }

    public Map<String, String> getProductById(String product) {
        Map<String, String> queryParams = new QueryParams().productByIds(product);
        ValidatableResponse response = get(uri, queryParams);
        Map<String, String> productData = new HashMap<>();
        String id = response.extract().body().path("data.getProductByIds[0].productId");
        String oldPrice = response.extract().body().path("data.getProductByIds[0].priceList").toString();
        String newPrice = response.extract().body().path("data.getProductByIds[0].priceCurrent").toString();
        productData.put("id", id);
        productData.put("oldPrice", oldPrice);
        productData.put("newPrice", newPrice);
        return productData;
    }

    public boolean verifyIDPrices() {
        boolean flag = true;
        HashMap<String, HashMap<String, String>> products = getMapAllProducts();
        HashMap<String, String> errors = new HashMap<>();
        for (String product : products.keySet()) {
            Map<String, String> productData = getProductById(product);
            String id = productData.get("id");
            String oldPrice = productData.get("oldPrice");
            String newPrice = productData.get("newPrice");
            if (!id.equals(product)) {
                errors.put(product, "ID :: expected " + id + " but found " + product);
                flag = false;
            }
            if (!oldPrice.equals(products.get(product).get("Old Price"))) {
                errors.put(product, "Old Price :: expected " + oldPrice + " but found " + products.get(product).get("Old Price"));
                flag = false;
            }
            if (!newPrice.equals(products.get(product).get("New Price"))) {
                errors.put(product, "New Price :: expected " + newPrice + " but found " + products.get(product).get("New Price"));
                flag = false;
            }
        }

        if (!errors.isEmpty()) {
            for (String error : errors.keySet()) {
                System.out.print(error + " ");
                System.out.println(errors.get(error));
            }
        }

        return flag;
    }

    // "getCollectionsByUserIdForQualifiersPopular
    private HashMap<String, HashMap<String, String>> getMapAllProducts() {
        HashMap<String, HashMap<String, String>> mapIdsAll = new HashMap<>();
        HashMap<String, HashMap<String, String>> mapIds;
        YmlReader ymlReader = new YmlReader();
        JSONObject collectionOfProducts = ymlReader.getDataJSON("getCollectionsByUserIdForQualifiersPopular.json");
        Set<String> categories = collectionOfProducts.keySet();

        for (String category : categories) {
            mapIds = (HashMap<String, HashMap<String, String>>) collectionOfProducts.get(category);
            Set<String> ids = mapIds.keySet();
            for (String id : ids) {
                mapIdsAll.put(id, mapIds.get(id));

            }
        }
        return mapIdsAll;
    }

    // "getCollectionsByUserIdForQualifiersPopular
    private HashMap<String, HashMap<String, String>> getMapAllProductsByCategory(String category) {
        HashMap<String, HashMap<String, String>> mapIdsAll = new HashMap<>();
        HashMap<String, HashMap<String, String>> mapIds;
        YmlReader ymlReader = new YmlReader();
        JSONObject collectionOfProducts = ymlReader.getDataJSON("getCollectionsByUserIdForQualifiersPopular.json");
        Set<String> categories = collectionOfProducts.keySet();

        if (categories.contains(category)) {
            mapIds = (HashMap<String, HashMap<String, String>>) collectionOfProducts.get(category);
            Set<String> ids = mapIds.keySet();
            for (String id : ids) {
                mapIdsAll.put(id, mapIds.get(id));

            }
        } else {
            System.out.println("Category didn't find");
        }

        return mapIdsAll;
    }


}
