package Bot5.playground;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Created by user on 19.03.2017.
 */


public class BoardTest  {

    @Test
    public void hasWonHorizontal1() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][1].content=Seed.CROSS;
        board.cells[0][2].content=Seed.CROSS;
        board.cells[0][3].content=Seed.CROSS;

        board.cells[0][0].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=0;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal2() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[0][1].content=Seed.CROSS;
        board.cells[0][2].content=Seed.CROSS;
        board.cells[0][3].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=3;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal3() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[0][1].content=Seed.CROSS;
        board.cells[0][2].content=Seed.CROSS;
        board.cells[0][3].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=2;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal4() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][6].content=Seed.CROSS;
        board.cells[0][7].content=Seed.CROSS;
        board.cells[0][8].content=Seed.CROSS;
        board.cells[0][9].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=6;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal5() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][6].content=Seed.CROSS;
        board.cells[0][7].content=Seed.CROSS;
        board.cells[0][8].content=Seed.CROSS;
        board.cells[0][9].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=9;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal6() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][6].content=Seed.CROSS;
        board.cells[0][7].content=Seed.CROSS;
        board.cells[0][8].content=Seed.CROSS;
        board.cells[0][9].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=7;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal7() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[5][4].content=Seed.CROSS;
        board.cells[5][5].content=Seed.CROSS;
        board.cells[5][6].content=Seed.CROSS;
        board.cells[5][7].content=Seed.CROSS;
        board.currentRow=5;
        board.currentCol=4;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal8() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[5][4].content=Seed.CROSS;
        board.cells[5][5].content=Seed.CROSS;
        board.cells[5][6].content=Seed.CROSS;
        board.cells[5][7].content=Seed.CROSS;
        board.currentRow=5;
        board.currentCol=7;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonHorizontal9() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[5][4].content=Seed.CROSS;
        board.cells[5][5].content=Seed.CROSS;
        board.cells[5][6].content=Seed.CROSS;
        board.cells[5][7].content=Seed.CROSS;
        board.currentRow=5;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal1() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[1][0].content=Seed.CROSS;
        board.cells[2][0].content=Seed.CROSS;
        board.cells[3][0].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=0;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal2() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[1][0].content=Seed.CROSS;
        board.cells[2][0].content=Seed.CROSS;
        board.cells[3][0].content=Seed.CROSS;
        board.currentRow=3;
        board.currentCol=0;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal3() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[1][0].content=Seed.CROSS;
        board.cells[2][0].content=Seed.CROSS;
        board.cells[3][0].content=Seed.CROSS;
        board.currentRow=2;
        board.currentCol=0;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal4() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[6][5].content=Seed.CROSS;
        board.cells[7][5].content=Seed.CROSS;
        board.cells[8][5].content=Seed.CROSS;
        board.cells[9][5].content=Seed.CROSS;
        board.currentRow=6;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal5() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[6][5].content=Seed.CROSS;
        board.cells[7][5].content=Seed.CROSS;
        board.cells[8][5].content=Seed.CROSS;
        board.cells[9][5].content=Seed.CROSS;
        board.currentRow=9;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal6() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[6][5].content=Seed.CROSS;
        board.cells[7][5].content=Seed.CROSS;
        board.cells[8][5].content=Seed.CROSS;
        board.cells[9][5].content=Seed.CROSS;
        board.currentRow=8;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal7() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[5][5].content=Seed.CROSS;
        board.cells[6][5].content=Seed.CROSS;
        board.cells[7][5].content=Seed.CROSS;
        board.cells[8][5].content=Seed.CROSS;
        board.currentRow=8;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal8() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[5][5].content=Seed.CROSS;
        board.cells[6][5].content=Seed.CROSS;
        board.cells[7][5].content=Seed.CROSS;
        board.cells[8][5].content=Seed.CROSS;
        board.currentRow=5;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void hasWonVertikal9() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[5][5].content=Seed.CROSS;
        board.cells[6][5].content=Seed.CROSS;
        board.cells[7][5].content=Seed.CROSS;
        board.cells[8][5].content=Seed.CROSS;
        board.currentRow=7;
        board.currentCol=5;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal1() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[1][1].content=Seed.CROSS;
        board.cells[2][2].content=Seed.CROSS;
        board.cells[3][3].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=0;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal2() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[1][1].content=Seed.CROSS;
        board.cells[2][2].content=Seed.CROSS;
        board.cells[3][3].content=Seed.CROSS;
        board.currentRow=3;
        board.currentCol=3;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal3() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[0][0].content=Seed.CROSS;
        board.cells[1][1].content=Seed.CROSS;
        board.cells[2][2].content=Seed.CROSS;
        board.cells[3][3].content=Seed.CROSS;
        board.currentRow=2;
        board.currentCol=2;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal4() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[6][6].content=Seed.CROSS;
        board.cells[7][7].content=Seed.CROSS;
        board.cells[8][8].content=Seed.CROSS;
        board.cells[9][9].content=Seed.CROSS;
        board.currentRow=6;
        board.currentCol=6;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal5() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[6][6].content=Seed.CROSS;
        board.cells[7][7].content=Seed.CROSS;
        board.cells[8][8].content=Seed.CROSS;
        board.cells[9][9].content=Seed.CROSS;
        board.currentRow=9;
        board.currentCol=9;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal6() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[6][6].content=Seed.CROSS;
        board.cells[7][7].content=Seed.CROSS;
        board.cells[8][8].content=Seed.CROSS;
        board.cells[9][9].content=Seed.CROSS;
        board.currentRow=7;
        board.currentCol=7;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal7() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[3][6].content=Seed.CROSS;
        board.cells[2][7].content=Seed.CROSS;
        board.cells[1][8].content=Seed.CROSS;
        board.cells[0][9].content=Seed.CROSS;
        board.currentRow=3;
        board.currentCol=6;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal8() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[3][6].content=Seed.CROSS;
        board.cells[2][7].content=Seed.CROSS;
        board.cells[1][8].content=Seed.CROSS;
        board.cells[0][9].content=Seed.CROSS;
        board.currentRow=0;
        board.currentCol=9;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal9() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[3][6].content=Seed.CROSS;
        board.cells[2][7].content=Seed.CROSS;
        board.cells[1][8].content=Seed.CROSS;
        board.cells[0][9].content=Seed.CROSS;
        board.currentRow=1;
        board.currentCol=8;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal10() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[9][0].content=Seed.CROSS;
        board.cells[8][1].content=Seed.CROSS;
        board.cells[7][2].content=Seed.CROSS;
        board.cells[6][3].content=Seed.CROSS;
        board.currentRow=6;
        board.currentCol=3;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal11() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[9][0].content=Seed.CROSS;
        board.cells[8][1].content=Seed.CROSS;
        board.cells[7][2].content=Seed.CROSS;
        board.cells[6][3].content=Seed.CROSS;
        board.currentRow=9;
        board.currentCol=0;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal12() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[9][0].content=Seed.CROSS;
        board.cells[8][1].content=Seed.CROSS;
        board.cells[7][2].content=Seed.CROSS;
        board.cells[6][3].content=Seed.CROSS;
        board.currentRow=7;
        board.currentCol=2;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal13() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[8][1].content=Seed.CROSS;
        board.cells[7][2].content=Seed.CROSS;
        board.cells[6][3].content=Seed.CROSS;
        board.cells[5][4].content=Seed.CROSS;
        board.currentRow=5;
        board.currentCol=4;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal14() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[8][1].content=Seed.CROSS;
        board.cells[7][2].content=Seed.CROSS;
        board.cells[6][3].content=Seed.CROSS;
        board.cells[5][4].content=Seed.CROSS;
        board.currentRow=8;
        board.currentCol=1;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }

    @Test
    public void diagonal15() throws Exception {
        Board board = new Board(10,10,4);
        board.cells[8][1].content=Seed.CROSS;
        board.cells[7][2].content=Seed.CROSS;
        board.cells[6][3].content=Seed.CROSS;
        board.cells[5][4].content=Seed.CROSS;
        board.currentRow=6;
        board.currentCol=3;
        assertEquals(true,board.hasWon(Seed.CROSS));

    }



}