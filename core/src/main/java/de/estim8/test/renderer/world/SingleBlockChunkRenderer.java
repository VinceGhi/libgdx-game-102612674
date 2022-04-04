package de.estim8.test.renderer.world;

import com.badlogic.gdx.graphics.Camera;
import de.estim8.test.math.SingleIntVector3;
import de.estim8.test.world.block.Block;
import de.estim8.test.world.block.Chunk;

import java.util.ArrayList;
import java.util.List;

public class SingleBlockChunkRenderer {

    List<BlockRenderer> blockRendererList = new ArrayList<>();

    /*
    This was a dumb test... rendering every voxel as single mesh is a big no no if you want more than 2fps, BUT
    the calculations were right, and they were places at the correct spot... that's... a win i guess?
     */
    public SingleBlockChunkRenderer(Chunk chunk) {
        for (int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
            for (int y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < Chunk.CHUNK_WIDTH; z++) {
                    Block currentBlock = chunk.getBlock(x, y, z);
                    if (currentBlock != null) {
                        blockRendererList.add(new BlockRenderer(chunk, currentBlock, checkBlock(chunk, currentBlock)));
                    }
                }
            }
        }
    }

    public List<BlockFace> checkBlock(Chunk chunk, Block block) {
        List<BlockFace> blockFaces = new ArrayList<>();
        if (block != null) {
            SingleIntVector3 relativePosition = block.getRelativePosition();
            Block top = chunk.getBlock(relativePosition.getX(), relativePosition.getY() + 1, relativePosition.getZ());
            Block bottom = chunk.getBlock(relativePosition.getX(), relativePosition.getY() - 1, relativePosition.getZ());
            Block left = chunk.getBlock(relativePosition.getX() - 1, relativePosition.getY(), relativePosition.getZ());
            Block front = chunk.getBlock(relativePosition.getX(), relativePosition.getY(), relativePosition.getZ() + 1);
            Block right = chunk.getBlock(relativePosition.getX() + 1, relativePosition.getY(), relativePosition.getZ());
            Block back = chunk.getBlock(relativePosition.getX(), relativePosition.getY(), relativePosition.getZ() - 1);

            if (top == null) {
                blockFaces.add(BlockFace.TOP);
            }

            if (bottom == null) {
                blockFaces.add(BlockFace.BOTTOM);
            }

            if (left == null) {
                blockFaces.add(BlockFace.LEFT);
            }

            if (front == null) {
                blockFaces.add(BlockFace.FRONT);
            }

            if (right == null) {
                blockFaces.add(BlockFace.RIGHT);
            }

            if (back == null) {
                blockFaces.add(BlockFace.BACK);
            }
        }

        return blockFaces;
    }

    public void render(Camera camera) {
        blockRendererList.forEach(br -> br.render(camera));
    }

    public void dispose() {
        blockRendererList.forEach(BlockRenderer::dispose);
    }

    public static void test() {

    }
}
