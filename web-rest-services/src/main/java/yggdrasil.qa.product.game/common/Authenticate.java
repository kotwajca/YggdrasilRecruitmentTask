package yggdrasil.qa.product.game.common;

import static yggdrasil.qa.product.game.common.EnviromentsLinks.getGameServiceUri;
import static yggdrasil.qa.product.game.utils.GameParameters.loginParameterList;

public class Authenticate {

    private String sessid;

    public String getSessid() {
        return sessid;
    }

    public ResponseObject login(ResponseObject responseObject) {

        responseObject = responseObject.executeGetRequest(responseObject, loginParameterList, getGameServiceUri());
        responseObject.isRequestCorrectlyExecuted();
        sessid = getSessidFromRespone(responseObject);

        return responseObject;
    }

    public String getSessidFromRespone(ResponseObject responseObject) {
        return responseObject.getResponseJson().get("data")
                .getAsJsonObject().get("sessid").getAsString();
    }

}
