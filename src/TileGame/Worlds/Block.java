package TileGame.Worlds;

import java.util.List;

public class Block {
    private List<Pair> blockingCoordinates;

    public Block(List<Pair> blockingCoordinates) {
        this.blockingCoordinates = blockingCoordinates;
    }

    public boolean isInsideBlock(Pair pair) {
        for (Pair p : blockingCoordinates) {
            if (!p.pairEquals(pair)) {
                return false;
            }
        }

        return true;
    }

}