package Bot5;

/**
 * Created by user on 10.02.2017.
 */

import Bot5.playground.Seed;
import Bot5.playground.SimplePlayGround;
import Bot5.playground.State;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bot5 {
    List<int[][]> randomMoveList = new ArrayList<int[][]>();
    public int depth = 3;
    private SimplePlayGround playGround = new SimplePlayGround(3, 3, 3);
    private int[] move = new int[2];
    int[][] tempMoves = new int[depth][2];
    int count = 0;
    FileWriter fw;


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
            return evaluateAdvanced(playGround,
                    seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS, tempMoves[count - 1][0], tempMoves[count - 1][1]);
        }

        int score = (count + 1) % 2 == 0 ? 1000 : -1000;  //without alpha beta
        //int score = (count + 1) % 2 == 0 ? beta : alpha;


        for (int i = 0; i < playGround.getBoard().cells.length; i++) {
            for (int j = 0; j < playGround.getBoard().cells[i].length; j++) {
                if (playGround.getBoard().cells[i][j].content == Seed.EMPTY &&
                        !playGround.isFinished()) {
                    count++;

                    playGround.setCurrentPlayer(seed);

                    playGround.doStep(i, j);
                    tempMoves[count - 1][0] = i;
                    tempMoves[count - 1][1] = j;

                    int s = maximinWithAlphaBeta((count % 2 != 0 ? score : alpha), (count % 2 != 0 ? beta : score),
                            seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS);

                    count--;


                    playGround.setCurrentPlayer(seed);
                    playGround.getBoard().cells[tempMoves[count][0]][tempMoves[count][1]].content = Seed.EMPTY;

                    if ((count + 1) % 2 == 0) {

                        if (s < score) {
                            try {
                                fw.write("s<score " + (count + 1) + " s = " + s + ",score was " + score + " moves:" + tempMoves[0][0] + "," + tempMoves[0][1] + ";" + tempMoves[1][0] + "," + tempMoves[1][1] + ";" + tempMoves[2][0] + "," + tempMoves[2][1]);
                                fw.write("\n\r\n");
                            } catch (Exception e) {
                                System.out.println("shit happens");
                                e.printStackTrace();
                            }
                            score = s;
                            if (score < alpha) {
                                try {
                                    fw.write("Returning score to next level - depth=" + (count + 1) + " with alpha beta(alpha=" + alpha + ") evaluation = " + score + " moves:" + tempMoves[0][0] + "," + tempMoves[0][1] + ";" + tempMoves[1][0] + "," + tempMoves[1][1] + ";" + tempMoves[2][0] + "," + tempMoves[2][1]);
                                    fw.write("\n\r\n");
                                } catch (Exception e) {
                                }
                                return score;
                            }
                        }
                    } else {
                        if (s == score) {                           //not working with
                            if (count == 0) {
                                System.out.println("s=score= "+s);  // alpha beta
                                move[0] = tempMoves[0][0];
                                move[1] = tempMoves[0][1];
                                int[][] tempArr = {{move[0], move[1]}};
                                randomMoveList.add(tempArr);
                            }
                        }
                        if (s > score) {
                            try {
                                fw.write("s>score, depth " + (count + 1) + " s = " + s + ",score was " + score + " moves:" + tempMoves[0][0] + "," + tempMoves[0][1] + ";" + tempMoves[1][0] + "," + tempMoves[1][1] + ";" + tempMoves[2][0] + "," + tempMoves[2][1]);
                                fw.write("\n\r\n");
                            } catch (Exception e) {
                            }
                            score = s;

                            if (score > beta) {
                                try {
                                    fw.write("Returning score to next level - depth=" + (count + 1) + " with alpha beta(beta=" + beta + ") evaluation = " + score + " moves:" + tempMoves[0][0] + "," + tempMoves[0][1] + ";" + tempMoves[1][0] + "," + tempMoves[1][1] + ";" + tempMoves[2][0] + "," + tempMoves[2][1]);
                                    fw.write("\n\r\n");
                                } catch (Exception e) {
                                }
                                return score;
                            }
                            if (count == 0) {
                                System.out.println("s<score "+score);
                                move[0] = tempMoves[0][0];
                                move[1] = tempMoves[0][1];
                                randomMoveList.clear();
                                int[][] tempArr = {{move[0], move[1]}};
                                randomMoveList.add(tempArr);

                            }

                        }

                    }


                }

            }
        }
        if (count == 0) {
            try {
                fw.write("Choosing move " + move[0] + " " + move[1] + "; with evaluation score " + score);
                fw.write("\n\r\n");
            } catch (IOException e) {
            }
        }
        try {
            fw.write("Returning to next level- Deapth " + (count + 1) + " evaluation = " + score + " moves:" + tempMoves[0][0] + "," + tempMoves[0][1] + ";" + tempMoves[1][0] + "," + tempMoves[1][1] + ";" + tempMoves[2][0] + "," + tempMoves[2][1]);
            fw.write("\n\r\n");
        } catch (Exception e) {
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
     * how many player's fields the move completes
     * how many opponent's fields the move destroys
     * Also detects next move win both for player and opponent
     */

    public int evaluateAdvanced(SimplePlayGround playGround, Seed seed, int i, int j) {
        int countTotalPlayer;
        boolean isWinningNextMovePlayer = false;
        boolean isWinningNextMoveOpp = false;
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
        countTotalPlayer = count1 + count2;

        // checking if we have numberToWin-1 in row and
        // 2 empty on both sides (winning next move)
        if (countTotalPlayer == playGround.getBoard().numberToWin - 2 &&
                j + count1 + 1 < playGround.getBoard().COLS &&
                playGround.getBoard().cells[i][j + count1 + 1].content == Seed.EMPTY &&
                j - count2 - 1 >= 0 &&
                playGround.getBoard().cells[i][j - count2 - 1].content == Seed.EMPTY) {
            isWinningNextMovePlayer = true;
        }

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
        if ((count1 + count2) > countTotalPlayer) countTotalPlayer = count1 + count2;

        // checking if we have numberToWin-1 in col and
        // 2 empty on both sides (winning next move)
        if (countTotalPlayer == playGround.getBoard().numberToWin - 2 &&
                i + count1 + 1 < playGround.getBoard().ROWS &&
                playGround.getBoard().cells[i + count1 + 1][j].content == Seed.EMPTY &&
                i - count2 - 1 >= 0 &&
                playGround.getBoard().cells[i - count2 - 1][j].content == Seed.EMPTY) {
            isWinningNextMovePlayer = true;
        }

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
        if ((count1 + count2) > countTotalPlayer) countTotalPlayer = count1 + count2;

        // checking if we have numberToWin-1 in diagonal 1 and
        // 2 empty on both sides (winning next move)
        if (countTotalPlayer == playGround.getBoard().numberToWin - 2 &&
                j + count1 + 1 < playGround.getBoard().COLS &&
                i + count1 + 1 < playGround.getBoard().ROWS &&
                playGround.getBoard().cells[i + count1 + 1][j + count1 + 1].content == Seed.EMPTY &&
                (i - count2 - 1) >= 0 && (j - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i - count2 - 1][j - count2 - 1].content == Seed.EMPTY) {
            isWinningNextMovePlayer = true;
        }


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
        if ((count1 + count2) > countTotalPlayer) countTotalPlayer = count1 + count2;

        // checking if player has numberToWin-1 in diagonal 2 and
        // 2 empty on both sides (winning next move)
        if (countTotalPlayer == playGround.getBoard().numberToWin - 2 &&
                j + count1 + 1 < playGround.getBoard().COLS &&
                i - count1 - 1 >= 0 &&
                playGround.getBoard().cells[i - count1 - 1][j + count1 + 1].content == Seed.EMPTY &&
                (i + count2 + 1) < playGround.getBoard().ROWS &&
                (j - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i + count2 + 1][j - count2 - 1].content == Seed.EMPTY) {
            isWinningNextMovePlayer = true;
        }

        Seed opponentSeed = seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS;

        //horizontal opponent's
        int countTotalOpp;
        count1 = 0;
        while (((j + count1 + 1) < playGround.getBoard().COLS) &&
                playGround.getBoard().cells[i][j + count1 + 1].content == opponentSeed) {
            count1++;
        }
        count2 = 0;
        while ((j - count2 - 1 >= 0) &&
                playGround.getBoard().cells[i][j - count2 - 1].content == opponentSeed) {
            count2++;
        }
        countTotalOpp = count1 + count2;

        // vertikal opponent's
        count1 = 0;
        while ((i + count1 + 1) < playGround.getBoard().ROWS &&
                playGround.getBoard().cells[i + count1 + 1][j].content == opponentSeed) {
            count1++;
        }
        count2 = 0;
        while ((i - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i - count2 - 1][j].content == opponentSeed) {
            count2++;
        }
        if ((count1 + count2) > countTotalOpp) countTotalOpp = count1 + count2;

        // opponent's diagonal 1
        count1 = 0;
        while ((i + count1 + 1) < playGround.getBoard().ROWS &&
                (j + count1 + 1) < playGround.getBoard().COLS &&
                playGround.getBoard().cells[i + count1 + 1][j + count1 + 1].content == opponentSeed) {
            count1++;
        }
        count2 = 0;
        while ((i - count2 - 1) >= 0 && (j - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i - count2 - 1][j - count2 - 1].content == opponentSeed) {
            count2++;
        }
        if ((count1 + count2) > countTotalOpp) countTotalOpp = count1 + count2;

        // opponents's diagonal 2
        count1 = 0;
        while ((i - count1 - 1) >= 0 && (j + count1 + 1) < playGround.getBoard().COLS &&
                playGround.getBoard().cells[i - count1 - 1][j + count1 + 1].content == opponentSeed) {
            count1++;
        }
        count2 = 0;
        while ((i + count2 + 1) < playGround.getBoard().ROWS && (j - count2 - 1) >= 0 &&
                playGround.getBoard().cells[i + count2 + 1][j - count2 - 1].content == opponentSeed) {
            count2++;
        }
        if ((count1 + count2) > countTotalOpp) countTotalOpp = count1 + count2;

        //checking if opponent is winning next move
        // (opp has numberToWin and 2 empty cells on both sides
        if (count - 2 >= 0) {
            int i1 = tempMoves[count - 2][0]; // prev opponent's
            int j1 = tempMoves[count - 2][1];   //move

            //removing last player's move as we are evaluating previos move board state
            playGround.getBoard().cells[tempMoves[count - 1][0]][tempMoves[count - 1][1]].content = Seed.EMPTY;

            //horizontal opponent's
            int countTotalOppPrevMove;
            count1 = 0;
            while (((j1 + count1 + 1) < playGround.getBoard().COLS) &&
                    playGround.getBoard().cells[i1][j1 + count1 + 1].content == opponentSeed) {
                count1++;
            }
            count2 = 0;
            while ((j1 - count2 - 1 >= 0) &&
                    playGround.getBoard().cells[i1][j1 - count2 - 1].content == opponentSeed) {
                count2++;
            }
            countTotalOppPrevMove = count1 + count2;
            // checking if opp has numberToWin-2 in row and
            // 2 empty on both sides (winning next move)
            if (countTotalOppPrevMove == playGround.getBoard().numberToWin - 2 &&
                    j1 + count1 + 1 < playGround.getBoard().COLS &&
                    playGround.getBoard().cells[i1][j1 + count1 + 1].content == Seed.EMPTY &&
                    j1 - count2 - 1 >= 0 &&
                    playGround.getBoard().cells[i1][j1 - count2 - 1].content == Seed.EMPTY) {

                isWinningNextMoveOpp = true;
            }

            //vertikal opponent
            count1 = 0;
            while (((i1 + count1 + 1) < playGround.getBoard().ROWS) &&
                    playGround.getBoard().cells[i1 + count1 + 1][j1].content == opponentSeed) {
                count1++;
            }
            count2 = 0;
            while ((i1 - count2 - 1 >= 0) &&
                    playGround.getBoard().cells[i1 - count2 - 1][j1].content == opponentSeed) {
                count2++;
            }
            countTotalOppPrevMove = count1 + count2;

            // checking if opp has numberToWin-2 in col and
            // 2 empty on both sides (winning next move)
            if (countTotalOppPrevMove == playGround.getBoard().numberToWin - 2 &&
                    i1 + count1 + 1 < playGround.getBoard().ROWS &&
                    playGround.getBoard().cells[i1 + count1 + 1][j1].content == Seed.EMPTY &&
                    i1 - count2 - 1 >= 0 &&
                    playGround.getBoard().cells[i1 - count2 - 1][j1].content == Seed.EMPTY) {

                isWinningNextMoveOpp = true;
            }

            //diagonal 1 opp
            count1 = 0;
            while ((i1 + count1 + 1) < playGround.getBoard().ROWS &&
                    (j1 + count1 + 1) < playGround.getBoard().COLS &&
                    playGround.getBoard().cells[i1 + count1 + 1][j1 + count1 + 1].content == opponentSeed) {
                count1++;
            }
            count2 = 0;
            while ((i1 - count2 - 1) >= 0 && (j1 - count2 - 1) >= 0 &&
                    playGround.getBoard().cells[i1 - count2 - 1][j1 - count2 - 1].content == opponentSeed) {
                count2++;
            }
            countTotalOppPrevMove = count1 + count2;

            // checking if opp has numberToWin-2 in diagonal 1 and
            // 2 empty on both sides (winning next move)
            if (countTotalOppPrevMove == playGround.getBoard().numberToWin - 2 &&
                    i1 + count1 + 1 < playGround.getBoard().ROWS &&
                    j1 + count1 + 1 < playGround.getBoard().COLS &&
                    playGround.getBoard().cells[i1 + count1 + 1][j1 + count1 + 1].content == Seed.EMPTY &&
                    j1 - count2 - 1 >= 0 &&
                    i1 - count2 - 1 >= 0 &&
                    playGround.getBoard().cells[i1 - count2 - 1][j1 - count2 - 1].content == Seed.EMPTY) {
                isWinningNextMoveOpp = true;
            }

            //diagonal 2 opp
            count1 = 0;
            while ((i1 + count1 + 1) < playGround.getBoard().ROWS &&
                    (j1 - count1 - 1) >= 0 &&
                    playGround.getBoard().cells[i1 + count1 + 1][j1 - count1 - 1].content == opponentSeed) {
                count1++;
            }
            count2 = 0;
            while ((i1 - count2 - 1) >= 0 && (j1 + count2 + 1) < playGround.getBoard().COLS &&
                    playGround.getBoard().cells[i1 - count2 - 1][j1 + count2 + 1].content == opponentSeed) {
                count2++;
            }
            countTotalOppPrevMove = count1 + count2;

            // checking if opp has numberToWin-2 in diagonal 2 and
            // 2 empty on both sides (winning next move)
            if (countTotalOppPrevMove == playGround.getBoard().numberToWin - 2 &&
                    i1 + count1 + 1 < playGround.getBoard().ROWS &&
                    j1 - count1 - 1 >= 0 &&
                    playGround.getBoard().cells[i1 + count1 + 1][j1 - count1 - 1].content == Seed.EMPTY &&
                    j1 + count2 + 1 < playGround.getBoard().COLS &&
                    i1 - count2 - 1 >= 0 &&
                    playGround.getBoard().cells[i1 - count2 - 1][j1 + count2 + 1].content == Seed.EMPTY) {
                isWinningNextMoveOpp = true;
            }


        }

        Seed tempSeed = (count % 2 != 0 ? seed :
                (seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS));
        if (seed == tempSeed) {

            if (countTotalPlayer >= playGround.getBoard().numberToWin - 1) return 900;
            if (isWinningNextMoveOpp) {
                return -800;
            }
            if (isWinningNextMovePlayer) return 800;
            return countTotalPlayer + countTotalOpp;
        } else {
            if (countTotalPlayer >= playGround.getBoard().numberToWin - 1) return -900;
            if (isWinningNextMoveOpp) return 800;
            if (isWinningNextMovePlayer) return -800;
            return -(countTotalPlayer + countTotalOpp);
        }

    }


    public void makeBotMove(Seed seed) {
        maximinWithAlphaBeta(-1000, 1000, seed);

//        int score = 0;
//       for (int i = 0; i < playGround.getBoard().cells.length; i++) {
//            for (int j = 0; j < playGround.getBoard().cells[i].length; j++) {
//                if (playGround.getBoard().cells[i][j].content == Seed.EMPTY) {
//                    int temp = evaluateAdvanced(playGround, seed, i, j);
//                    if (temp > score) {
//                        score = temp;
//                        move[0] = i;
//                        move[1] = j;
//                    }
//
//                }
//            }
//        }
        int i = (int)(Math.random()*randomMoveList.size());
        System.out.println("i=" +randomMoveList.size());
        move[0]=randomMoveList.get(i)[0][0];
        move[1]= randomMoveList.get(i)[0][1];
        System.out.println(playGround.doStep(move[0], move[1]));


    }

    public void start() {
        this.playGround.start();
        //playGround.getBoard().cells[0][2].content=Seed.CROSS;
        //playGround.getBoard().cells[1][2].content=Seed.CROSS;
        //playGround.getBoard().cells[2][2].content=Seed.CROSS;
        //playGround.getBoard().cells[5][2].content=Seed.NOUGHT;
        System.out.println("Initital pos...");
        System.out.println(playGround.print());
        try {
            fw = new FileWriter("d:/JavaTemp/bot5.txt");
        } catch (IOException e) {
        }


    }

    public void play() {
        int count = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Game starting... Press Enter");
        System.out.println(playGround.print());

        do {
            System.out.println("******************************");
            scan.nextLine();
            try {
                fw.write("*****************************************");
                fw.write("\n\r\n");
                fw.write(" M O V E :" + ++count);
                fw.write("\n\r\n");


            } catch (Exception e) {
            }

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
            if (!playGround.isFinished()) {
                makeBotMove(Seed.NOUGHT);
                System.out.println("CPU MOVE");
                System.out.println("Current player- " + playGround.getCurrentPlayer());
                System.out.println(this.playGround.print());
                System.out.println("*********************************");
            }


        } while (!playGround.isFinished());
        scan.close();
        try {
            fw.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {

        Bot5 bot5 = new Bot5();
        bot5.start();
        System.out.println(bot5.tempMoves[0][0] + "," + bot5.tempMoves[0][1]);
        bot5.play();


    }
}


