package Bot5;

import Bot5.playground.Seed;
import Bot5.playground.SimplePlayGround;
import Bot5.playground.State;

import java.util.Scanner;

/**
 * Created by user on 11.06.2017.
 */
public class Bot5Manager {

    private SimplePlayGround playGround = new SimplePlayGround(3, 3, 3);
    private int[] move = new int[2];
    private int depth = 8;
    public void play() {
        int count = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Game starting... Press Enter");
        System.out.println(playGround.print());

        do {
            System.out.println("******************************");
            scan.nextLine();


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
                } while (playGround.getCurrentPlayer() == Seed.CROSS);
                System.out.println("**********************************");
                System.out.println(playGround.print());
                System.out.println("***********************************");
            }
            if (!playGround.isFinished()) {
                long start = System.nanoTime();
                move=Bot5.makeBotMove(Seed.NOUGHT, playGround, depth);
                long finish = System.nanoTime();
                System.out.println("Time = " + (finish-start));
                System.out.println("Number of rec = " + Bot5.totalRecCount);
                System.out.println("Number of returnings = " + Bot5.totalReturnCount);
                System.out.println("size of randomMoveList =" + Bot5.randomMoveList.size());
                for (int j = 0; j < Bot5.randomMoveList.size(); j++) {
                    System.out.print(Bot5.randomMoveList.get(j)[0][0] + "," + Bot5.randomMoveList.get(j)[0][1]+ ";");

                }
                playGround.doStep(move[0],move[1]);
                System.out.println("CPU MOVE");
                System.out.println("Current player- " + playGround.getCurrentPlayer());
                System.out.println(this.playGround.print());
                System.out.println("*********************************");
            }


        } while (!playGround.isFinished());
        scan.close();

    }

    public void start() {
        this.playGround.start();
        //playGround.getBoard().cells[0][2].content=Seed.CROSS;
        //playGround.getBoard().cells[1][2].content=Seed.CROSS;
        //playGround.getBoard().cells[2][2].content=Seed.CROSS;
        //playGround.getBoard().cells[5][2].content=Seed.NOUGHT;
        System.out.println("Initital pos...");
        System.out.println(playGround.print());

    }

    public static void main(String[] args) {
        Bot5Manager bot5Manager = new Bot5Manager();
        bot5Manager.start();
        bot5Manager.play();


    }
}
