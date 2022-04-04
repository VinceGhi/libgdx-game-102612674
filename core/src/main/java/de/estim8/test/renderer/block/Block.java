package de.estim8.test.renderer.block;

import de.estim8.test.renderer.math.IntVector3;

import static de.estim8.test.renderer.block.Chunk.CHUNK_HEIGHT;
import static de.estim8.test.renderer.block.Chunk.CHUNK_WIDTH;

public class Block {
    private IntVector3 relativePosition;
    private BlockColor color;
    private Chunk chunk;

    public Block(IntVector3 relativePosition, BlockColor color, Chunk chunk) {
        this.relativePosition = relativePosition;
        this.color = color;
        this.chunk = chunk;
        this.chunk.addBlock(this);
    }

    protected Block(IntVector3 relativePosition, BlockColor color) {
        this.relativePosition = relativePosition;
        this.color = color;
    }

    public IntVector3 getAbsolutePosition() {
        return new IntVector3(CHUNK_WIDTH * chunk.getPositionCopy().getX() + getRelativePosition().getX(), CHUNK_HEIGHT * chunk.getPositionCopy().getY() + getRelativePosition().getY(), CHUNK_WIDTH * chunk.getPositionCopy().getZ() + getRelativePosition().getZ());
    }

    public IntVector3 getRelativePosition() {
        return relativePosition;
    }

    public void setRelativePosition(IntVector3 position) {
        this.relativePosition = new IntVector3(position.getX() % CHUNK_WIDTH, position.getY() % CHUNK_HEIGHT, position.getZ() % CHUNK_WIDTH);
    }

    public BlockColor getColor() {
        return color;
    }

    public void setColor(BlockColor color) {
        this.color = color;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public String toString() {
        return "Block{" +
                "position=" + relativePosition +
                ", color=" + color +
                ", chunk=" + chunk +
                '}';
    }
}
