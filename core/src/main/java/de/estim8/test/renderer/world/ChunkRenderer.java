package de.estim8.test.renderer.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import de.estim8.test.world.block.Block;
import de.estim8.test.world.block.BlockColor;
import de.estim8.test.world.block.Chunk;

import static de.estim8.test.renderer.world.BlockModel.BLOCK_MODEL_SIZE;

public class ChunkRenderer {
    private final ModelBatch modelBatch;
    private final Environment environment;
    private final ModelInstance chunkModelInstance;
    private final Model chunkModel;

    private static final int attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorPacked;

    public ChunkRenderer(Chunk chunk) {
        modelBatch = new ModelBatch();

        // lightning
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.4f, 0.4f, 0.4f, -1f, -0.8f, -0.2f));

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        setChunk(modelBuilder, chunk);
        chunkModel = modelBuilder.end();
        chunkModelInstance = new ModelInstance(chunkModel);
        Vector3 modelPosition = new Vector3(
                chunk.getAbsolutePosition().getX() * BLOCK_MODEL_SIZE,
                chunk.getAbsolutePosition().getY() * BLOCK_MODEL_SIZE,
                chunk.getAbsolutePosition().getZ() * BLOCK_MODEL_SIZE
        );
        chunkModelInstance.transform.set(modelPosition, new Quaternion(0, 0, 0, 0));
    }

    public void setChunk(ModelBuilder modelBuilder, Chunk chunk) {
        int blockCount = 0;
        String blockId = "block-" + blockCount;
        BlockColor color = BlockColor.random();
        MeshPartBuilder builder = modelBuilder.part(blockId, GL20.GL_TRIANGLES, attributes, new Material());
        for (int y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
            for (int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
                for (int z = 0; z < Chunk.CHUNK_WIDTH; z++) {
                    Block currentBlock = chunk.getBlock(x, y, z);
                    if (currentBlock != null) {
                        if (blockCount++ > 1024) {
                            blockCount = 0;
                            color = BlockColor.random();
                            builder = modelBuilder.part(blockId, GL20.GL_TRIANGLES, attributes, new Material());
                        }
                        BlockState top = chunk.getBlockType(x, y + 1, z);
                        BlockState bottom = chunk.getBlockType(x, y - 1, z);
                        BlockState left = chunk.getBlockType(x - 1, y, z);
                        BlockState front = chunk.getBlockType(x, y, z + 1);
                        BlockState right = chunk.getBlockType(x + 1, y, z);
                        BlockState back = chunk.getBlockType(x, y, z - 1);

//                        currentBlock.setColor(color);

                        if (!top.equals(BlockState.SOLID)) {
//                            currentBlock.setColor(BlockColor.GREEN);
                            BlockModel.addTopFace(builder, currentBlock);
                        }

                        if (!bottom.equals(BlockState.SOLID)) {
                            BlockModel.addBottomFace(builder, currentBlock);
                        }

                        if (!left.equals(BlockState.SOLID)) {
                            BlockModel.addLeftFace(builder, currentBlock);
                        }

                        if (!front.equals(BlockState.SOLID)) {
                            BlockModel.addFrontFace(builder, currentBlock);
                        }

                        if (!right.equals(BlockState.SOLID)) {
                            BlockModel.addRightFace(builder, currentBlock);
                        }

                        if (!back.equals(BlockState.SOLID)) {
                            BlockModel.addBackFace(builder, currentBlock);
                        }
                    }
                }
            }
        }
    }

    public void render(Camera camera) {
        modelBatch.begin(camera);
        modelBatch.render(chunkModelInstance, environment);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        chunkModel.dispose();
    }

    public static void test() {

    }
}
