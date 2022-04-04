package de.estim8.test.renderer.debug;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import de.estim8.test.world.block.Chunk;
import de.estim8.test.math.IntVector3;

import java.util.List;

import static de.estim8.test.renderer.world.BlockModel.BLOCK_MODEL_SIZE;
import static de.estim8.test.world.block.Chunk.CHUNK_HEIGHT;
import static de.estim8.test.world.block.Chunk.CHUNK_WIDTH;

public class ChunkDebugRenderer {
    private final ModelBatch modelBatch;
    private final MeshPartBuilder builder;
    private final Model lineModel;
    private final ModelInstance lineModelInstance;

    public ChunkDebugRenderer(Chunk chunk) {
        this.modelBatch = new ModelBatch();

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        builder = modelBuilder.part("line", GL20.GL_LINES, VertexAttributes.Usage.Position | VertexAttributes.Usage.ColorUnpacked, new Material());
        builder.setColor(new Color(1f, 0, 0, 0.2f));
        stuff(chunk);
        lineModel = modelBuilder.end();
        lineModelInstance = new ModelInstance(lineModel);
        Vector3 modelPosition = new Vector3(
                chunk.getAbsolutePosition().getX() * BLOCK_MODEL_SIZE,
                chunk.getAbsolutePosition().getY() * BLOCK_MODEL_SIZE,
                chunk.getAbsolutePosition().getZ() * BLOCK_MODEL_SIZE
        );
        lineModelInstance.transform.set(modelPosition, new Quaternion(0, 0, 0, 0));
    }

    public void render(Camera camera) {
        modelBatch.begin(camera);
        modelBatch.render(lineModelInstance);
        modelBatch.end();
    }

    private void renderChunkLine(IntVector3 vec1, IntVector3 vec2) {
        builder.line(
                new Vector3(vec1.getX() * BLOCK_MODEL_SIZE, vec1.getY() * BLOCK_MODEL_SIZE, vec1.getZ() * BLOCK_MODEL_SIZE),
                new Vector3(vec2.getX() * BLOCK_MODEL_SIZE, vec2.getY() * BLOCK_MODEL_SIZE, vec2.getZ() * BLOCK_MODEL_SIZE)
        );
    }

    private void stuff(Chunk chunk) {
        IntVector3 bottomLeftFront = new IntVector3(0, 0, 0);
        IntVector3 bottomRightFront = new IntVector3(CHUNK_WIDTH, 0, 0);
        IntVector3 bottomLeftBack = new IntVector3(0, 0, CHUNK_WIDTH);
        IntVector3 bottomRightBack = new IntVector3(CHUNK_WIDTH, 0, CHUNK_WIDTH);
        IntVector3 topLeftFront = new IntVector3(0, CHUNK_HEIGHT, 0);
        IntVector3 topRightFront = new IntVector3(CHUNK_WIDTH, CHUNK_HEIGHT, 0);
        IntVector3 topLeftBack = new IntVector3(0, CHUNK_HEIGHT, CHUNK_WIDTH);
        IntVector3 topRightBack = new IntVector3(CHUNK_WIDTH, CHUNK_HEIGHT, CHUNK_WIDTH);
        renderChunkLine(bottomLeftFront, bottomRightFront);
        renderChunkLine(bottomRightFront, bottomRightBack);
        renderChunkLine(bottomRightBack, bottomLeftBack);
        renderChunkLine(bottomLeftBack, bottomLeftFront);

        renderChunkLine(topLeftFront, topRightFront);
        renderChunkLine(topRightFront, topRightBack);
        renderChunkLine(topRightBack, topLeftBack);
        renderChunkLine(topLeftBack, topLeftFront);
        
        renderChunkLine(topRightBack, bottomRightBack);
        renderChunkLine(topLeftBack, bottomLeftBack);
        renderChunkLine(topRightFront, bottomRightFront);
        renderChunkLine(topLeftFront, bottomLeftFront);
    }

    public void dispose() {
        modelBatch.dispose();
        lineModel.dispose();
    }
}
