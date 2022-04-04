package de.estim8.test.world.block;

import com.badlogic.gdx.math.Vector3;
import de.estim8.test.math.IntVector3;
import de.estim8.test.math.SingleIntVector3;

import static de.estim8.test.renderer.world.BlockModel.BLOCK_MODEL_SIZE;

public class Block {
    private final SingleIntVector3 relativePosition;
    private final byte data; // first 4 bits are for color rn

    public Block(SingleIntVector3 relativePosition, BlockColor color) {
        this.relativePosition = relativePosition;
        this.data = (byte) color.getId();
    }

    public IntVector3 getAbsolutePosition(Chunk chunk) {
        return new IntVector3(
                chunk.getAbsolutePosition().getX() + getRelativePosition().getX(),
                chunk.getAbsolutePosition().getY() + getRelativePosition().getY(),
                chunk.getAbsolutePosition().getZ() + getRelativePosition().getZ()
        );
    }

    public Vector3 getAbsoluteRenderPosition(Chunk chunk) {
        return new Vector3(
                (chunk.getAbsolutePosition().getX() + getRelativePosition().getX()) * BLOCK_MODEL_SIZE,
                (chunk.getAbsolutePosition().getY() + getRelativePosition().getY()) * BLOCK_MODEL_SIZE,
                (chunk.getAbsolutePosition().getZ() + getRelativePosition().getZ()) * BLOCK_MODEL_SIZE
        );
    }

    public SingleIntVector3 getRelativePosition() {
        return relativePosition;
    }

    public BlockColor getColor() {
        return BlockColor.getById(data & 0xF);
    }

    @Override
    public String toString() {
        return "Block{" +
                "position=" + getRelativePosition() +
                ", color=" + getColor() +
                '}';
    }
}
