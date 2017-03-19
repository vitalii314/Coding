package Bot5;

import Bot5.playground.Seed;
import Bot5.playground.SimplePlayGround;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 19.03.2017.
 */
public class Bot5Test {

    @Test
    // player's fields
    public void testEvaluateAdvanced() throws Exception {
        SimplePlayGround playGround = new SimplePlayGround(7, 7, 4);
        playGround.start();
        playGround.getBoard().cells[1][3].content = Seed.CROSS;
        playGround.getBoard().cells[2][5].content = Seed.CROSS;
        playGround.getBoard().cells[3][2].content = Seed.NOUGHT;
        playGround.getBoard().cells[3][3].content = Seed.CROSS;
        playGround.getBoard().cells[4][0].content = Seed.CROSS;
        playGround.getBoard().cells[4][1].content = Seed.NOUGHT;
        playGround.getBoard().cells[4][6].content = Seed.NOUGHT;
        playGround.getBoard().cells[5][2].content = Seed.CROSS;
        playGround.getBoard().cells[5][2].content = Seed.CROSS;
        playGround.getBoard().cells[5][3].content = Seed.CROSS;
        playGround.getBoard().cells[5][4].content = Seed.NOUGHT;
        playGround.getBoard().cells[6][1].content = Seed.NOUGHT;
        playGround.getBoard().cells[6][3].content = Seed.CROSS;
        playGround.getBoard().cells[6][5].content = Seed.NOUGHT;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();

        assertEquals(4,bot.evaluateAdvanced(playGround,Seed.CROSS,4,3));
    }
}