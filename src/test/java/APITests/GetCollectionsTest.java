package APITests;

import API.ApiActions;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetCollectionsTest{

    ApiActions apiActions;

    @Test
    public void getCollectionsTest(){

        apiActions = new ApiActions();
        apiActions.getCollectionsByUserIdForQualifiersPopular();
    }

    @Description("Verification Id, Prices of product")
    @Story("Smoke")
    @Test(dependsOnMethods = "getCollectionsTest")
    public void verificationIdPricesOfProductTest(){

        apiActions = new ApiActions();
        boolean result = apiActions.verifyIDPrices();
        Assert.assertTrue(result);
    }


}
