package de.estim8.test.world.block;

import de.estim8.test.math.IntVector3;
import de.estim8.test.math.SingleIntVector3;
import de.estim8.test.renderer.world.BlockState;

/*
 * This needs a shit ton of memory... the 3d-blocks-array is shit... well, I don't store them anywhere till I've figured out how to actually work with the MeshPartBuilder..
 */
public class Chunk {
    public static final short CHUNK_HEIGHT = 512;
    public static final short CHUNK_WIDTH = 64;
    private final SingleIntVector3 position;

    /*
     * how do i get rid of this little b****...?
     *
     * I know you can store a 2d field into a 1d array because you can easily get the top/bottom/left/right fields by the width of the field...
     *
     *
     * [ 0][ 1][ 2][ 3] If you want to get the field on top of field[6]
     * [ 4][ 5][ 6][ 7] you can simply subtract the current index by the width of the field, so you just do field[2] right?
     * [ 8][ 9][10][11] there must be a way to do this with a 3d field. Storing a 3d field into a 2d array would be easy.. but
     * [12][13][14][15] i am sure that there is a way to do this with a one dimensional array.
     *
     * The offets for a 2D field in a 1d array are -width top, +width bottom, -1 left, +2 right.
     *
     */
    private final Block[] blocks = new Block[CHUNK_WIDTH*CHUNK_HEIGHT*CHUNK_WIDTH];

    public Chunk(SingleIntVector3 position) {
        this.position = position;
    }

    public SingleIntVector3 getRelativePosition() {
        return new SingleIntVector3(position.getX(), position.getY(), position.getZ());
    }

    public IntVector3 getAbsolutePosition() {
        return new IntVector3(
                getRelativePosition().getX() * CHUNK_WIDTH,
                getRelativePosition().getY() * CHUNK_HEIGHT,
                getRelativePosition().getZ() * CHUNK_WIDTH
        );
    }

    public void addBlock(Block block) {
        blocks[CHUNK_WIDTH * CHUNK_WIDTH * block.getRelativePosition().getY() + (block.getRelativePosition().getX() * CHUNK_WIDTH) + block.getRelativePosition().getZ()] = block;
    }

    public Block addBlock(SingleIntVector3 relativePosition, BlockColor color) {
        Block block = new Block(relativePosition, color);
        blocks[CHUNK_WIDTH * CHUNK_WIDTH * block.getRelativePosition().getY() + (block.getRelativePosition().getX() * CHUNK_WIDTH) + block.getRelativePosition().getZ()] = block;
        return block;
    }

    public Block getBlock(int x, int y, int z) {
        if (checkInBound(new IntVector3(x, y, z))) {
            return blocks[CHUNK_WIDTH * CHUNK_WIDTH * y + x * CHUNK_WIDTH + z];
        }
        return null;
    }

    public BlockState getBlockType(int x, int y, int z) {
        if (checkInBound(new IntVector3(x, y, z))) {
            return blocks[CHUNK_WIDTH * CHUNK_WIDTH * y + (x * CHUNK_WIDTH) + z] == null ? BlockState.NOT_SOLID : BlockState.SOLID;
        }
        return BlockState.NULL;
    }

    public Block getBlock(IntVector3 position) {
        return getBlock(position.getX(), position.getY(), position.getZ());
    }

    private boolean checkInBound(IntVector3 relativePosition) {
        return (relativePosition.getX() >= 0 && relativePosition.getX() < CHUNK_WIDTH &&
                relativePosition.getZ() >= 0 && relativePosition.getZ() < CHUNK_WIDTH &&
                relativePosition.getY() >= 0 && relativePosition.getY() < CHUNK_HEIGHT);
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "position=" + position +
                "bounds=" + String.format("{from=%s, to=%s}",
                    new IntVector3(CHUNK_WIDTH * position.getX(), CHUNK_HEIGHT * position.getY(), CHUNK_WIDTH * position.getZ()),
                    new IntVector3(CHUNK_WIDTH * (position.getX()+1) - 1, CHUNK_HEIGHT * (position.getY()+1) - 1, CHUNK_WIDTH * (position.getZ()+1) - 1)) +
                '}';
    }
}
