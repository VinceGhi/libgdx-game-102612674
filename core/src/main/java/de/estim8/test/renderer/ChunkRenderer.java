package de.estim8.test.renderer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import de.estim8.test.renderer.block.Block;
import de.estim8.test.renderer.block.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChunkRenderer {
    private final ModelBatch modelBatch;
    private final List<BlockModel> blockModels;
    private final Environment environment;

    public ChunkRenderer(Chunk chunk) {
        modelBatch = new ModelBatch();

        // lightning
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.4f, 0.4f, 0.4f, -1f, -0.8f, -0.2f));

        blockModels = new ArrayList<>();

        setChunk(chunk);
    }

    public void setChunk(Chunk chunk) {
        for (int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
            for (int y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < Chunk.CHUNK_WIDTH; z++) {
                    addBlocks(chunk.getBlock(x, y, z));
                }
            }
        }
    }

    private void addBlocks(Block... blocks) {
        this.blockModels.addAll(Stream.of(blocks).filter(Objects::nonNull).map(BlockModel::new).collect(Collectors.toList()));
    }

    public void render(Camera camera) {
        modelBatch.begin(camera);
        blockModels.forEach(model -> model.render(modelBatch, environment));
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        blockModels.forEach(BlockModel::dispose);
    }
}
