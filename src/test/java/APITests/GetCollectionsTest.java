package APITests;

import API.ApiActions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCollectionsTest{

    ApiActions apiActions;

    @Test
    public void getCollectionsTest(){

        apiActions = new ApiActions();
        apiActions.getCollectionsByUserIdForQualifiersPopular();
    }

    @Test(dependsOnMethods = "getCollectionsTest")
    public void verificationIdTitlePricesOfProductTest(){

        apiActions = new ApiActions();
        boolean result = apiActions.verifyIDPrices();
        Assert.assertTrue(result);
    }


}
