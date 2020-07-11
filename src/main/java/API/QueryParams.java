package API;

import java.util.HashMap;
import java.util.Map;

public class QueryParams {

    Map<String,String> queryParams;

    public Map<String,String> collectionsByUserIdForQualifiersPopular(){
        queryParams = new HashMap();

        queryParams.put("operationName", "web_getCollectionsByUserIdForQualifiersPopular");
        queryParams.put("variables","{\"limit\":10}");

        return queryParams;
    }

    public Map<String,String> productByIds(String id){
        queryParams = new HashMap();

        queryParams.put("operationName", "web_getProductByIds");
        queryParams.put("variables","{\"productIds\":[\""+id+"\"],\"meta\":{\"shouldGetDealInsights\":true}}");

        return queryParams;
    }


}
