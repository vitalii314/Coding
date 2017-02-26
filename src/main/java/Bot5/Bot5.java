package Bot5;

/**
 * Created by user on 10.02.2017.
 */

import Bot5.playground.Seed;
import Bot5.playground.SimplePlayGround;

import java.util.Scanner;

public class Bot5 {

    public static final int ROWS = 5;
    public static final int COLS = 5;
    public int depth = 3;
    private SimplePlayGround playGround = new SimplePlayGround(3, 3);
    private int[] move = new int[2];
    private int[][] tempMoves = new int[depth][2];
    private boolean firstMove;
    Seed tempSeed;
    Seed tempCurPlayer;
    Seed firstSeed;

    int[][] field = new int[ROWS][COLS];
    int count = 0;
    int varCount = 0;


    public void makeMove2(Seed seed) {
        maximin(seed);
        playGround.doStep(move[0], move[1]);

    }

    public int maximin(Seed seed) {
        if (count >= depth || playGround.isFinished()) {
            return heruistic(playGround, seed);
        }
        int score = (count + 1) % 2 == 0 ? 1000 : -1000;
        for (int i = 0; i < playGround.getBoard().cells.length; i++) {
            for (int j = 0; j < playGround.getBoard().cells[i].length; j++) {
                if (playGround.getBoard().cells[i][j].content == Seed.EMPTY &&
                        !playGround.isFinished()) {
                    count++;

//
//                    if (count==1) {
//                        tempSeed = seed;
//                    } else {
//                        tempSeed = (seed==Seed.CROSS?Seed.NOUGHT:Seed.CROSS);
//                    }
                    if (count != 0)
                        playGround.setCurrentPlayer(count % 2 != 0 ? seed : (seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS));

                    //playGround.setCurrentPlayer(tempSeed);
                    playGround.doStep(i, j);
                    firstMove = false;
                    tempMoves[count - 1][0] = i;
                    tempMoves[count - 1][1] = j;

                    System.out.println("Count = " + count + " TempSeed= " + tempSeed + " CurrentPlayer = " + playGround.getCurrentPlayer());
                    System.out.println(playGround.print());
                    int s = maximin(seed);
                    //if (s>score) score = s;
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
                    //playGround.setCurrentPlayer(playGround.getCurrentPlayer() == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS);
                    playGround.setCurrentPlayer(seed);
                    playGround.getBoard().cells[tempMoves[count][0]][tempMoves[count][1]].content = Seed.EMPTY;
                }

            }
        }
        return score;
    }

    public int heruistic(SimplePlayGround playGround, Seed seed) {
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

    public void makeMove() {
        int a = -1000;
//        for (int i = 0; i < playGround.getBoard().cells.length; i++) {
//            for (int j = 0; j < playGround.getBoard().cells[i].length; j++) {
//                if (playGround.getBoard().cells[i][j].content==Seed.EMPTY) {
//                    int b=maximin(Seed.CROSS);
//                    if (b>a) {
//                        a=b;
//                        move[0]=i;
//                        move[1]=j;
//                    }
//
//                }
//            }
//
//        }
//        System.out.println("value="+a);
//        System.out.println("State of playground after maximin");
        System.out.println("Printing after minimax...");
        System.out.println(playGround.print());
//        playGround.doStep(move[0],move[1]);
    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        firstMove = true;
        firstSeed = Seed.EMPTY;
        Seed tempSeed = Seed.EMPTY;
        this.playGround.start();
//        this.playGround.getBoard().cells[0][0].content = Seed.CROSS;
//        this.playGround.getBoard().cells[0][1].content = Seed.CROSS;
//        this.playGround.getBoard().cells[0][2].content = Seed.NOUGHT;
//        this.playGround.getBoard().cells[1][1].content = Seed.NOUGHT;
//        this.playGround.getBoard().cells[2][1].content = Seed.NOUGHT;
//        this.playGround.getBoard().cells[2][2].content = Seed.CROSS;
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
            System.out.println("CRU PLAYER= " + playGround.getCurrentPlayer());
            makeMove2(Seed.CROSS);
            System.out.println(this.playGround.print());
            System.out.println("*********************************");

            if (!playGround.isFinished()) {
                System.out.println("Your move...");
                int a;
                int b;
                do {
                    a = scan.nextInt();
                    b = scan.nextInt();
                    if (playGround.getBoard().cells[a][b].content != Seed.EMPTY) {
                        System.out.println("Сдейлай нормальный ход");
                    }
                } while (playGround.getBoard().cells[a][b].content != Seed.EMPTY);
                playGround.doStep(a, b);
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


        //bot5.makeMove();
        //bot5.maximin(Seed.CROSS);
        // System.out.println(bot5.playGround.print());
        // long startTime = System.nanoTime();
        // bot5.doRec();
        // long finishTime = System.nanoTime();
        //System.out.println("time = "+(finishTime-startTime)/1000000000);


    }
}
