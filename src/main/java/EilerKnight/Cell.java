package EilerKnight;

/**
 * Created by user on 23.08.2016.
 */
public class Cell {
    public Seed content;

    public Cell() {
        clear();
    }

    public void clear(){
        content=Seed.EMPTY;
    }

    public String paint(){
        StringBuilder sb = new StringBuilder();
        switch (content) {
            case EMPTY: sb.append(" ");break;
            case TAKEN: sb.append("X");break;
        }
        return sb.toString();
    }

}
