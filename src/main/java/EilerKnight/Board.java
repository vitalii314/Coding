package EilerKnight;

/**
 * Created by user on 23.08.2016.
 */
public class Board {
    private static final int ROW_NUMBER=5;
    private static final int COLUMN_NUMBER=5;

    public Cell[][] cells = new Cell[COLUMN_NUMBER][ROW_NUMBER];

    public Board () {
        for ( int i=0 ; i<cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
               cells[i][j]  = new Cell();

            }
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                sb.append(cells[i][j].paint());
                sb.append("|");
            }
            sb.append("\n\r\n");
            sb.append("----------\n\r\n");

        }
        return sb.toString();
    }

}
