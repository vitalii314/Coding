package Bot5;

/**
 * Created by user on 10.02.2017.
 */

import Bot5.playground.Seed;
import Bot5.playground.SimplePlayGround;
import Bot5.playground.State;

import java.util.Scanner;

public class Bot5 {

    public int depth = 3;
    private SimplePlayGround playGround = new SimplePlayGround(10, 10, 4);
    private int[] move = new int[2];
    private int[][] tempMoves = new int[depth][2];
    int count = 0;


    public int maximin(Seed seed) {
        if (count >= depth || playGround.isFinished()) {
            return evaluate(playGround, seed);
        }
        int score = (count + 1) % 2 == 0 ? 1000 : -1000;
        for (int i = 0; i < playGround.getBoard().cells.length; i++) {
            for (int j = 0; j < playGround.getBoard().cells[i].length; j++) {
                if (playGround.getBoard().cells[i][j].content == Seed.EMPTY &&
                        !playGround.isFinished()) {
                    count++;
                    playGround.setCurrentPlayer(count % 2 != 0 ? seed : (seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS));
                    playGround.doStep(i, j);
                    tempMoves[count - 1][0] = i;
                    tempMoves[count - 1][1] = j;
                    System.out.println(playGround.print());

                    int s = maximin(seed);

                    if (count % 2 == 0) {
                        if (s < score) {
                            score = s;
                        }
                    } else if (s > score) {
                        score = s;
                        if (count == 1) {
                            move[0] = tempMoves[0][0];
                            move[1] = tempMoves[0][1];
                        }

                    }
                    System.out.println("count = " + count + " Value = " + s);
                    System.out.println("Score = " + score);
                    count--;
                    playGround.setCurrentPlayer(seed);
                    playGround.getBoard().cells[tempMoves[count][0]][tempMoves[count][1]].content = Seed.EMPTY;
                }

            }
        }
        return score;
    }

    public int maximinWithAlphaBeta(int alpha, int beta, Seed seed) {
        if (count >= depth || playGround.isFinished()) {
            return evaluate(playGround, seed);
        }
        int score = (count + 1) % 2 == 0 ? beta : alpha;
        for (int i = 0; i < playGround.getBoard().cells.length; i++) {
            for (int j = 0; j < playGround.getBoard().cells[i].length; j++) {
                if (playGround.getBoard().cells[i][j].content == Seed.EMPTY &&
                        !playGround.isFinished()) {
                    count++;

                    playGround.setCurrentPlayer(count % 2 != 0 ? seed : (seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS));

                    playGround.doStep(i, j);
                    tempMoves[count - 1][0] = i;
                    tempMoves[count - 1][1] = j;
                    //System.out.println(playGround.print());

                    int s = maximinWithAlphaBeta((count % 2 != 0 ? score : alpha), (count % 2 != 0 ? beta : score), seed);

                    count--;
                    playGround.setCurrentPlayer(seed);
                    playGround.getBoard().cells[tempMoves[count][0]][tempMoves[count][1]].content = Seed.EMPTY;

                    if ((count + 1) % 2 == 0) {
                        if (s < score) {
                            score = s;
                            if (score < alpha) return score;
                        }
                    } else if (s > score) {
                        score = s;
                        if (score > beta) return score;
                        if (count == 0) {
                            move[0] = tempMoves[0][0];
                            move[1] = tempMoves[0][1];
                        }

                    }


                    //System.out.println("count = " + count + " Value = " + s);
                    //System.out.println("Score = " + score);

                }

            }
        }
        return score;
    }

    /**
     * A simple evaluation function,
     * evaluates: win, loose, draw, none
     */
    public int evaluate(SimplePlayGround playGround, Seed seed) {
        if (!playGround.isFinished()) return 0;
        if (playGround.getBoard().hasWon(seed)) {
            return 5;
        } else {
            if (playGround.getBoard().hasWon(seed == Seed.NOUGHT ? Seed.CROSS : Seed.NOUGHT)) {
                return -5;
            } else {
                return 2;
            }

        }
    }

    /**
     * Advanced evaluation function
     * evaluates
     * how many player's fields a certain move complete
     * how many opponent's fields a certain move destroy
     */

    public int evaluateAdvanced(SimplePlayGround playGround, Seed seed, int i, int j) {
        int countTotal;
        //horizontal player's
        int count1 = 0;
        while (((j + count1 + 1) < playGround.getBoard().COLS) &&
                playGround.getBoard().cells[i][j + count1 + 1].content == seed) {
            count1++;
        }
        int count2 = 0;
        while ((j - count2 - 1 >= 0) &&
                playGround.getBoard().cells[i][j - count2 - 1].content == seed) {
            count2++;
        }
        countTotal = count1 + count2;
        // vertikal player's
        count1 = 0;
        while ((i + count1 + 1) < playGround.getBoard().ROWS &&
                playGround.getBoard().cells[i + count1 + 1][j].content == seed) {
            count1++;
        }
        count2 = 0;
        while ((i - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i - count2 - 1][j].content == seed) {
            count2++;
        }
        countTotal = countTotal + count1 + count2;
        // player's diagonal 1
        count1 = 0;
        while ((i + count1 + 1) < playGround.getBoard().ROWS &&
                (j + count1 + 1) < playGround.getBoard().COLS &&
                playGround.getBoard().cells[i + count1 + 1][j + count1 + 1].content == seed) {
            count1++;
        }
        count2 = 0;
        while ((i - count2 - 1) >= 0 && (j - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i - count2 - 1][j - count2 - 1].content == seed) {
            count2++;
        }
        countTotal = countTotal + count1 + count2;

        // player's diagonal 2
        count1 = 0;
        while ((i - count1 - 1) >= 0 && (j + count1 + 1) < playGround.getBoard().COLS &&
                playGround.getBoard().cells[i - count1 - 1][j + count1 + 1].content == seed) {
            count1++;
        }
        count2 = 0;
        while ((i + count2 + 1) < playGround.getBoard().ROWS && (j - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i + count2 + 1][j - count2 - 1].content == seed) {
            count2++;
        }
        countTotal = countTotal + count1 + count2;


        return countTotal;

    }


    public void makeMove(Seed seed) {
        maximinWithAlphaBeta(-1000, 1000, seed);
        System.out.println(playGround.doStep(move[0], move[1]));

    }

    public void start() {
        this.playGround.start();
        System.out.println("Initital pos...");
        System.out.println(playGround.print());


    }

    public void play() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Game starting... Press Enter");
        System.out.println(playGround.print());

        do {
            System.out.println("******************************");
            scan.nextLine();
            makeMove(Seed.CROSS);
            System.out.println("CPU MOVE");
            System.out.println("Current player- " + playGround.getCurrentPlayer());
            System.out.println(this.playGround.print());
            System.out.println("*********************************");

            if (!playGround.isFinished()) {
                System.out.println("Your move...");
                int a;
                int b;
                do {
                    a = scan.nextInt();
                    b = scan.nextInt();
                    State state = playGround.doStep(a, b);
                    if (state == State.INVALID_INPUT_DATA) {
                        System.out.println("Сдейлай нормальный ход");
                    } else {
                        System.out.println(state);
                        break;
                    }
                } while (playGround.getCurrentPlayer() == Seed.NOUGHT);
                System.out.println("**********************************");
                System.out.println(playGround.print());
                System.out.println("***********************************");
            }

        } while (!playGround.isFinished());
        scan.close();
    }

    public static void main(String[] args) {

        Bot5 bot5 = new Bot5();
        bot5.start();
        System.out.println(bot5.tempMoves[0][0] + "," + bot5.tempMoves[0][1]);
        bot5.play();

    }
}


