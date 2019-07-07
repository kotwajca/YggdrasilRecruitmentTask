package yggdrasil.qa.product.game.common;

public class EnviromentsLinks {

    private static final String PROTOCOL = "https";

    private static final String HOST = "pff.yggdrasilgaming.com";

    private static final String SERVICE = "/game.web/service";

    public static String getGameServiceUri() {
        return PROTOCOL + "://" + HOST + SERVICE;
    }

}
