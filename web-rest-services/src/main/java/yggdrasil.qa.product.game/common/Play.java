package yggdrasil.qa.product.game.common;

import static yggdrasil.qa.product.game.common.EnviromentsLinks.getGameServiceUri;
import static yggdrasil.qa.product.game.utils.GameStatus.PENDING;
import static yggdrasil.qa.product.game.utils.GameParameters.getPlayParametersList;
import static yggdrasil.qa.product.game.utils.GameParameters.getPlayPendingParametersList;

public class Play {

    Double wonAmount;

    String wagerid;

    String status;

    public Double getWonAmount() {
        return wonAmount;
    }

    public String getWagerid() {
        return wagerid;
    }

    public String getStatus() {
        return status;
    }

    public Double playGameTillFirstWin(ResponseObject responseObject, String sessid) {
        do {
            responseObject = responseObject.executeGetRequest(responseObject, getPlayParametersList(sessid), getGameServiceUri());
            responseObject.isRequestCorrectlyExecuted();
            status = getStatusFromResponse(responseObject);
            if (status.equals(PENDING.getStatus())) {
                wagerid = getWageridFromResponse(responseObject);
                responseObject = responseObject.executeGetRequest(responseObject, getPlayPendingParametersList(sessid, wagerid), getGameServiceUri());
                responseObject.isRequestCorrectlyExecuted();
            }
            wonAmount = Double.parseDouble(getWonamountFromResponse(responseObject));
        } while (wonAmount == 0);

        return wonAmount;
    }

    public String getStatusFromResponse(ResponseObject responseObject) {
        return responseObject.getResponseJson().get("data")
                .getAsJsonObject().get("wager")
                .getAsJsonObject().get("status").getAsString();
    }

    public String getWageridFromResponse(ResponseObject responseObject) {
        return responseObject.getResponseJson().get("data")
                .getAsJsonObject().get("wager")
                .getAsJsonObject().get("wagerid").getAsString();
    }

    public String getWonamountFromResponse(ResponseObject responseObject) {
        return responseObject.getResponseJson().get("data")
                .getAsJsonObject().get("wager")
                .getAsJsonObject().get("bets")
                .getAsJsonArray().get(0)
                .getAsJsonObject().get("wonamount")
                .getAsString();
    }
}
