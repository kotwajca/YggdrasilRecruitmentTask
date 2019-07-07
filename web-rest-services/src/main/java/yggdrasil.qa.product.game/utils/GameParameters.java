package yggdrasil.qa.product.game.utils;

import com.mifmif.common.regex.Generex;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.Arrays;
import java.util.List;

public final class GameParameters {

    public static List<NameValuePair> loginParameterList = Arrays.asList(
            new BasicNameValuePair("fn", "authenticate"),
            new BasicNameValuePair("org", "Demo"),
            new BasicNameValuePair("lang", "en"),
            new BasicNameValuePair("gameid", "7316"),
            new BasicNameValuePair("channel", "pc"),
            new BasicNameValuePair("currency", "EUR"),
            new BasicNameValuePair("crid", generateCrid()),
            new BasicNameValuePair("csid", "38e8b5d2-da3b-4aac-a2a2-81d8302ee00e")
    );

    public static List<NameValuePair> getPlayParametersList(String sessid) {
        return Arrays.asList(
                new BasicNameValuePair("fn", "play"),
                new BasicNameValuePair("currency", "EUR"),
                new BasicNameValuePair("gameid", "7316"),
                new BasicNameValuePair("sessid", sessid),
                new BasicNameValuePair("channel", "pc"),
                new BasicNameValuePair("lang", "en"),
                new BasicNameValuePair("gameHistorySessionId", "session"),
                new BasicNameValuePair("gameHistoryTicketId", "ticket"),
                new BasicNameValuePair("amount", "1.25"),
                new BasicNameValuePair("lines", "1111111111111111111111111"),
                new BasicNameValuePair("coin", "0.05"),
                new BasicNameValuePair("clientinfo", "1904171344380100000"),
                new BasicNameValuePair("crid", generateCrid()),
                new BasicNameValuePair("csid", "38e8b5d2-da3b-4aac-a2a2-81d8302ee00e")
        );
    }

    public static List<NameValuePair> getPlayPendingParametersList(String sessid, String wagergid) {
        return Arrays.asList(
                new BasicNameValuePair("fn", "play"),
                new BasicNameValuePair("currency", "EUR"),
                new BasicNameValuePair("gameid", "7316"),
                new BasicNameValuePair("sessid", sessid),
                new BasicNameValuePair("channel", "pc"),
                new BasicNameValuePair("lang", "en"),
                new BasicNameValuePair("gameHistorySessionId", "session"),
                new BasicNameValuePair("gameHistoryTicketId", "ticket"),
                new BasicNameValuePair("amount", "0"),
                new BasicNameValuePair("wagerid", wagergid),
                new BasicNameValuePair("betid", "1"),
                new BasicNameValuePair("step", "2"),
                new BasicNameValuePair("cmd", "C"),
                new BasicNameValuePair("crid", generateCrid()),
                new BasicNameValuePair("csid", "38e8b5d2-da3b-4aac-a2a2-81d8302ee00e")
        );
    }

    private static String generateCrid() {
        Generex generex = new Generex("[a-z0-9]{8}-([a-z0-9]{4}-){4}[a-z0-9]{12}");
        return generex.random();
    }
}
