package de.estim8.test.renderer;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import de.estim8.test.renderer.block.Block;
import de.estim8.test.renderer.math.IntVector3;

public class BlockModel {
    public static final float BLOCK_MODEL_SIZE = .5f;
    private final Model model;
    private final ModelInstance instance;

    public BlockModel(Block block) {
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(BLOCK_MODEL_SIZE, BLOCK_MODEL_SIZE, BLOCK_MODEL_SIZE,  block.getColor().createMaterialNoise(block.getAbsolutePosition()), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);
        IntVector3 absolutePosition = block.getAbsolutePosition();
        Vector3 modelPosition = new Vector3(
                absolutePosition.getX() * BLOCK_MODEL_SIZE + (BLOCK_MODEL_SIZE / 2),
                absolutePosition.getY() * BLOCK_MODEL_SIZE + (BLOCK_MODEL_SIZE / 2),
                absolutePosition.getZ() * BLOCK_MODEL_SIZE + (BLOCK_MODEL_SIZE / 2)
        );
        instance.transform.set(modelPosition, new Quaternion(0f, 0f, 0f, 0f));
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(instance, environment);
    }

    public void dispose() {
        model.dispose();
    }
}
