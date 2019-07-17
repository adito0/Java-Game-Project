package TileGame.Worlds;


public class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean pairEquals(Pair pair) {
        return (pair.getX() == this.x) && (pair.getY() == this.y);
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}