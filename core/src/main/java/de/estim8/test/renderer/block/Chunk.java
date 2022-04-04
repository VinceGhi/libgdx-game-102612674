package de.estim8.test.renderer.block;

import de.estim8.test.renderer.math.IntVector3;

public class Chunk {
    public static final short CHUNK_HEIGHT = 16;
    public static final short CHUNK_WIDTH = 16;
    private final IntVector3 position;
    private final Block[][][] blocks = new Block[CHUNK_WIDTH][CHUNK_HEIGHT][CHUNK_WIDTH];

    public Chunk(IntVector3 position) {
        this.position = position;
    }

    public IntVector3 getPositionCopy() {
        return new IntVector3(position.getX(), position.getY(), position.getZ());
    }

    public void addBlock(Block block) {
        block.setChunk(this);
        blocks[block.getRelativePosition().getX()][block.getRelativePosition().getY()][block.getRelativePosition().getZ()] = block;
    }

    public Block addBlock(IntVector3 relativePosition, BlockColor color) {
        Block block = new Block(relativePosition, color, this);
        blocks[block.getRelativePosition().getX()][block.getRelativePosition().getY()][block.getRelativePosition().getZ()] = block;
        return block;
    }

    public Block getBlock(int x, int y, int z) {
        return blocks[x][y][z];
    }

    public Block getBlock(IntVector3 position) {
        return getBlock(position.getX(), position.getY(), position.getZ());
    }

    private void checkInBound(IntVector3 relativePosition) {
        if (relativePosition.getX() >= 0 && relativePosition.getX() <= CHUNK_WIDTH &&
                relativePosition.getZ() >= 0 && relativePosition.getZ() <= CHUNK_WIDTH &&
                relativePosition.getY() >= 0 && relativePosition.getY() <= CHUNK_HEIGHT) {
            throw new RuntimeException("Blocks relative position is out of bounds");
        }
    }

    private void getAbsolutePosition() {

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
