package de.estim8.test.renderer.world;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Vector3;
import de.estim8.test.world.block.Block;

import java.awt.*;

public class BlockModel {
    public static final float BLOCK_MODEL_SIZE = .25f;

    // using MeshPartBuilder works... kinda... performance still sucks dick... and i don't know how to actually use it
    public static void addTopFace(MeshPartBuilder meshPartBuilder, Block block) {
        Vector3[] coords = new Vector3[4];
        final int x = block.getRelativePosition().getX(), y = block.getRelativePosition().getY(), z = block.getRelativePosition().getZ();

        coords[0] = new Vector3(new Vector3( // top front left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[1] = new Vector3(new Vector3( // top back left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[2] = new Vector3(new Vector3( // top back right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[3] = new Vector3(new Vector3( // top front right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        addRectNeg(meshPartBuilder, coords, new Vector3(0, 1, 0), block);
    }

    public static void addBottomFace(MeshPartBuilder meshPartBuilder, Block block) {
        Vector3[] coords = new Vector3[4];
        final int x = block.getRelativePosition().getX(), y = block.getRelativePosition().getY(), z = block.getRelativePosition().getZ();

        //  + BLOCK_MODEL_SIZE
        coords[0] = new Vector3(new Vector3( // top front left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[1] = new Vector3(new Vector3( // top back left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[2] = new Vector3(new Vector3( // top back right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[3] = new Vector3(new Vector3( // top front right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        addRect(meshPartBuilder, coords, new Vector3(0, -1, 0), block);
    }

    public static void addFrontFace(MeshPartBuilder meshPartBuilder, Block block) {
        Vector3[] coords = new Vector3[4];
        final int x = block.getRelativePosition().getX(), y = block.getRelativePosition().getY(), z = block.getRelativePosition().getZ();

        //  + BLOCK_MODEL_SIZE
        coords[0] = new Vector3(new Vector3( // top front left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[1] = new Vector3(new Vector3( // top back left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[2] = new Vector3(new Vector3( // top back right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[3] = new Vector3(new Vector3( // top front right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        addRect(meshPartBuilder, coords, new Vector3(0, 0, 1), block);
    }

    public static void addBackFace(MeshPartBuilder meshPartBuilder, Block block) {
        Vector3[] coords = new Vector3[4];
        final int x = block.getRelativePosition().getX(), y = block.getRelativePosition().getY(), z = block.getRelativePosition().getZ();

        //  + BLOCK_MODEL_SIZE
        coords[0] = new Vector3(new Vector3( // top front left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[1] = new Vector3(new Vector3( // top back left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[2] = new Vector3(new Vector3( // top back right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[3] = new Vector3(new Vector3( // top front right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        addRectNeg(meshPartBuilder, coords, new Vector3(0, 0, -1), block);
    }

    public static void addLeftFace(MeshPartBuilder meshPartBuilder, Block block) {
        Vector3[] coords = new Vector3[4];
        final int x = block.getRelativePosition().getX(), y = block.getRelativePosition().getY(), z = block.getRelativePosition().getZ();

        //  + BLOCK_MODEL_SIZE
        coords[0] = new Vector3(new Vector3( // top front left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[1] = new Vector3(new Vector3( // top back left
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[2] = new Vector3(new Vector3( // top back right
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[3] = new Vector3(new Vector3( // top front right
                x * BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        addRect(meshPartBuilder, coords, new Vector3(-1, 0, 0), block);
    }

    public static void addRightFace(MeshPartBuilder meshPartBuilder, Block block) {
        Vector3[] coords = new Vector3[4];
        final int x = block.getRelativePosition().getX(), y = block.getRelativePosition().getY(), z = block.getRelativePosition().getZ();

        //  + BLOCK_MODEL_SIZE
        coords[0] = new Vector3(new Vector3( // top front left
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[1] = new Vector3(new Vector3( // top back left
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE
        ));

        coords[2] = new Vector3(new Vector3( // top back right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        coords[3] = new Vector3(new Vector3( // top front right
                x * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE,
                y * BLOCK_MODEL_SIZE,
                z * BLOCK_MODEL_SIZE + BLOCK_MODEL_SIZE
        ));

        addRectNeg(meshPartBuilder, coords, new Vector3(1, 0, 0), block);
    }

    public static void addRectNeg(MeshPartBuilder meshPartBuilder, Vector3[] vectors, Vector3 normal, Block block) {
        meshPartBuilder.setColor(block.getColor().getNoisyColor(block.getRelativePosition()));
        meshPartBuilder.rect(vectors[0], vectors[1], vectors[2], vectors[3], normal);
    }

    public static void addRect(MeshPartBuilder meshPartBuilder, Vector3[] vectors, Vector3 normal, Block block) {
        meshPartBuilder.setColor(block.getColor().getNoisyColor(block.getRelativePosition()));
        meshPartBuilder.rect(vectors[3], vectors[2], vectors[1], vectors[0], normal);
    }
}
