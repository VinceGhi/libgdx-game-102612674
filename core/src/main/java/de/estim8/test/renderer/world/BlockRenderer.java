package de.estim8.test.renderer.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ShortArray;
import de.estim8.test.math.IntVector3;
import de.estim8.test.world.block.Block;
import de.estim8.test.world.block.BlockColor;
import de.estim8.test.world.block.Chunk;

import java.util.List;

import static de.estim8.test.renderer.world.BlockModel.BLOCK_MODEL_SIZE;

public class BlockRenderer {
    private final ModelBatch modelBatch;
    private final Environment environment;
    private final Model testModel;
    private final ModelInstance testModelInstance;

    /*
    This was a dumb test... rendering every voxel as single mesh is a big no no if you want more than 2fps, BUT
    the calculations were right, and they were places at the correct spot... that's... a win i guess?
     */
    public BlockRenderer(Chunk chunk, Block block, List<BlockFace> blockFaces) {
        modelBatch = new ModelBatch();

        // lightning
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.4f, 0.4f, 0.4f, -1f, -0.8f, -0.2f));


        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        Mesh mesh = new Mesh(true, 16, blockFaces.size() * 6, VertexAttribute.Position(), VertexAttribute.Normal());
        Vector3 absolutePosition = block.getAbsoluteRenderPosition(chunk);
//        mesh.setVertices(new float[]{
//                absolutePosition.getX(), absolutePosition.getY(), absolutePosition.getZ(), -1, -1, -1,
//                absolutePosition.getX(), absolutePosition.getY(), absolutePosition.getZ() * BLOCK_MODEL_SIZE, -1, -1, -1,
//                absolutePosition.getX() * BLOCK_MODEL_SIZE, absolutePosition.getY(), absolutePosition.getZ(), -1, -1, -1,
//                absolutePosition.getX() * BLOCK_MODEL_SIZE, absolutePosition.getY(), absolutePosition.getZ() * BLOCK_MODEL_SIZE, -1, -1, -1,
//                absolutePosition.getX(), absolutePosition.getY() * BLOCK_MODEL_SIZE, absolutePosition.getZ(),  0,  1,  0,
//                absolutePosition.getX(), absolutePosition.getY() * BLOCK_MODEL_SIZE, absolutePosition.getZ() * BLOCK_MODEL_SIZE,  0,  1,  0,
//                absolutePosition.getX() * BLOCK_MODEL_SIZE, absolutePosition.getY() * BLOCK_MODEL_SIZE, absolutePosition.getZ(),  0,  1,  0,
//                absolutePosition.getX() * BLOCK_MODEL_SIZE, absolutePosition.getY() * BLOCK_MODEL_SIZE, absolutePosition.getZ() * BLOCK_MODEL_SIZE,  0,  1,  0,
//        });

        float x = absolutePosition.x;
        float y = absolutePosition.y;
        float z = absolutePosition.z;

        mesh.setVertices(new float[]{
                x                   , y                     , z                     , 0, -1, 0,
                x                   , y                     , z + BLOCK_MODEL_SIZE  , 0, -1, 0,
                x + BLOCK_MODEL_SIZE, y                     , z                      , 0, -1, 0,
                x + BLOCK_MODEL_SIZE, y                     , z + BLOCK_MODEL_SIZE  , 0, -1, 0,
                x                   , y + BLOCK_MODEL_SIZE  , z                     , 0,  1,  0,
                x                   , y + BLOCK_MODEL_SIZE  , z + BLOCK_MODEL_SIZE  , 0,  1,  0,
                x + BLOCK_MODEL_SIZE, y + BLOCK_MODEL_SIZE  , z                     , 0,  1,  0,
                x + BLOCK_MODEL_SIZE, y + BLOCK_MODEL_SIZE  , z + BLOCK_MODEL_SIZE  , 0,  1,  0,
        });

        ShortArray indices = new ShortArray(blockFaces.size() * 6);

        if (blockFaces.contains(BlockFace.BOTTOM)) {
            indices.addAll((short) 2, (short) 1, (short) 0, (short) 1, (short) 2, (short) 3); // Bottom
        }

        if (blockFaces.contains(BlockFace.TOP)) {
            indices.addAll((short) 6, (short) 4, (short) 7, (short) 5, (short) 7, (short) 4); // Top
        }

        if (blockFaces.contains(BlockFace.RIGHT)) {
            indices.addAll((short) 2, (short) 6, (short) 3, (short) 7, (short) 3, (short) 6); // Right
        }

        if (blockFaces.contains(BlockFace.LEFT)) {
            indices.addAll((short) 4, (short) 0, (short) 5, (short) 1, (short) 5, (short) 0); // Left
        }

        if (blockFaces.contains(BlockFace.BACK)) {
            indices.addAll((short) 4, (short) 6, (short) 0, (short) 2, (short) 0, (short) 6); // Back
        }

        if (blockFaces.contains(BlockFace.FRONT)) {
            indices.addAll((short) 3, (short) 7, (short) 1, (short) 5, (short) 1, (short) 7); // Front
        }

        mesh.setIndices(indices.items);

        modelBuilder.part("block", mesh, GL20.GL_TRIANGLES, block.getColor().createMaterial());
        testModel = modelBuilder.end();
        testModelInstance = new ModelInstance(testModel);
    }


    public void render(Camera camera) {
        modelBatch.begin(camera);
        modelBatch.render(testModelInstance, environment);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        testModel.dispose();
    }
}
