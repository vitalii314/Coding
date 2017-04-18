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
        playGround.getBoard().cells[0][0].content = Seed.CROSS;
        playGround.getBoard().cells[1][3].content = Seed.CROSS;
        playGround.getBoard().cells[2][5].content = Seed.CROSS;
        playGround.getBoard().cells[3][2].content = Seed.NOUGHT;
        playGround.getBoard().cells[3][3].content = Seed.CROSS;
        playGround.getBoard().cells[4][0].content = Seed.CROSS;
        playGround.getBoard().cells[4][1].content = Seed.NOUGHT;
        playGround.getBoard().cells[4][6].content = Seed.NOUGHT;
        playGround.getBoard().cells[5][2].content = Seed.CROSS;
        playGround.getBoard().cells[5][2].content = Seed.CROSS;
        playGround.getBoard().cells[5][4].content = Seed.NOUGHT;
        playGround.getBoard().cells[6][1].content = Seed.NOUGHT;
        playGround.getBoard().cells[6][3].content = Seed.CROSS;
        playGround.getBoard().cells[6][5].content = Seed.NOUGHT;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();

        assertEquals(4,bot.evaluateAdvanced(playGround,Seed.CROSS,4,3));
    }

    @Test
    public void testThreeInRowPlayer() throws Exception {
        SimplePlayGround playGround = new SimplePlayGround(7, 7, 4);
        playGround.start();
        playGround.getBoard().cells[2][3].content = Seed.CROSS;
        playGround.getBoard().cells[2][4].content = Seed.CROSS;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();

        assertEquals(800,bot.evaluateAdvanced(playGround,Seed.CROSS,2,5));
    }

    @Test // if opp winning next move horizontal dir
    public void testThreeInRowOpp() throws Exception {
        SimplePlayGround playGround = new SimplePlayGround(7, 7, 4);
        playGround.start();
        playGround.getBoard().cells[2][3].content = Seed.NOUGHT;
        playGround.getBoard().cells[2][4].content = Seed.NOUGHT;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();
        bot.count=2;
        bot.tempMoves[bot.count-2][0]=2;
        bot.tempMoves[bot.count-2][1]=5;


        assertEquals(-800,bot.evaluateAdvanced(playGround,Seed.CROSS,6,6));
    }

    @Test // if opp winning next move horizontal dir
    public void testThreeInColOpp() throws Exception {
        SimplePlayGround playGround = new SimplePlayGround(7, 7, 4);
        playGround.start();
        playGround.getBoard().cells[2][3].content = Seed.NOUGHT;
        playGround.getBoard().cells[4][3].content = Seed.NOUGHT;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();
        bot.count=2;
        bot.tempMoves[bot.count-2][0]=3;
        bot.tempMoves[bot.count-2][1]=3;


        assertEquals(-800,bot.evaluateAdvanced(playGround,Seed.CROSS,6,6));
    }

    @Test // if opp winning next move diagonal 1 dir
    public void testThreeInDiagonal1Opp() throws Exception {
        SimplePlayGround playGround = new SimplePlayGround(7, 7, 4);
        playGround.start();
        playGround.getBoard().cells[2][3].content = Seed.NOUGHT;
        playGround.getBoard().cells[3][4].content = Seed.NOUGHT;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();
        bot.count=2;
        bot.tempMoves[bot.count-2][0]=4;
        bot.tempMoves[bot.count-2][1]=5;


        assertEquals(-800,bot.evaluateAdvanced(playGround,Seed.CROSS,6,6));
    }

    @Test // if opp winning next move diagonal 1 dir
    public void testThreeInDiagonal2Opp() throws Exception {
        SimplePlayGround playGround = new SimplePlayGround(7, 7, 4);
        playGround.start();
        playGround.getBoard().cells[4][4].content = Seed.NOUGHT;
        playGround.getBoard().cells[5][3].content = Seed.NOUGHT;

        playGround.getBoard().paint();

        Bot5 bot = new Bot5();
        bot.count=2;
        bot.tempMoves[bot.count-2][0]=3;
        bot.tempMoves[bot.count-2][1]=5;


        assertEquals(-800,bot.evaluateAdvanced(playGround,Seed.CROSS,1,1));
    }

}