package yggdrasil.qa.product.game;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import yggdrasil.qa.product.game.common.Authenticate;
import yggdrasil.qa.product.game.common.Play;
import yggdrasil.qa.product.game.common.ResponseObject;


public class GameTest {

    ResponseObject responseObject;

    Authenticate authenticate;

    Play play;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        responseObject = new ResponseObject();
        authenticate = new Authenticate();
        play = new Play();
    }

    @Test
    public void playTillWinTest() {
        //when
        responseObject = authenticate.login(responseObject);
        final String sessid = authenticate.getSessid();

        final Double wonAmount = play.playGameTillFirstWin(responseObject, sessid);

        //then
        assertTrue(wonAmount > 0);
    }

}
