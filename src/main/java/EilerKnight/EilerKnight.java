package EilerKnight;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by user on 23.08.2016.
 */
public class EilerKnight {
    private int currentPosY;
    private int currentPosX;
    private Board board;
    private final static int[][] MOVES = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    int[][] resultMoves = new int[24][2];
    int count = 0; // moves count
    File file = new File("d:/eiler.txt"); // file to write result
    FileWriter fw = new FileWriter(file);


    public EilerKnight() throws Exception {
        currentPosY = 2;
        currentPosX = 2;
        board = new Board();
        board.cells[2][2].content = Seed.TAKEN; // initial position

    }

    public void doEiler() throws Exception {
        if (areAllCellsTaken()) {
            saveResultMoves(); // adds moves to result file
            fw.close();
            return;
        }

        for (int i = 0; i < MOVES.length; i++) {

            int a = MOVES[i][0];
            int b = MOVES[i][1];

            if (isMoveAllowed(a, b)) {
                currentPosY = currentPosY + a;
                currentPosX = currentPosX + b;
                board.cells[currentPosY][currentPosX].content = Seed.TAKEN;
                fw.write("move number " + ++count + "\n\r\n");
                fw.write(board.print());
                resultMoves[count - 1][0] = currentPosY;
                resultMoves[count - 1][1] = currentPosX;

                doEiler();

            }


        }


        if (!areAllCellsTaken()) { //needs to be unchanged to finish all
            count--;
            fillBoard(count);
            currentPosY = resultMoves[count - 1][0];
            currentPosX = resultMoves[count - 1][1];
        }


    }


    public boolean isMoveAllowed(int a, int b) {
        if ((currentPosY + a) > 4 || (currentPosX + b > 4)
                || (currentPosY + a) < 0 || (currentPosX + b) < 0) return false;

        return (board.cells[currentPosY + a][currentPosX + b].content == Seed.EMPTY);

    }

    public boolean areAllCellsTaken() {
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                if (board.cells[i][j].content == Seed.EMPTY) {

                    return false;
                }

            }
        }
        System.out.println("Done!");
        return true;
    }

    public void fillBoard(int count) {
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells.length; j++) {
                board.cells[i][j].content = Seed.EMPTY;
            }

        }
        board.cells[2][2].content = Seed.TAKEN;
        for (int i = 0; i < count; i++) {
            board.cells[resultMoves[i][0]][resultMoves[i][1]].content = Seed.TAKEN;

        }
    }


    public void saveResultMoves() throws Exception {
        for (int[] a : resultMoves) {
            fw.write(a[0] + "," + a[1]);
            fw.write("\n\r\n");
        }
    }

    public static void main(String[] args) throws Exception {
        EilerKnight eilerKnight = new EilerKnight();
        eilerKnight.doEiler();

    }

}
