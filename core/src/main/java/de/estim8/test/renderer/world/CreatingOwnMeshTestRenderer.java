package de.estim8.test.renderer.world;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.FloatArray;
import de.estim8.test.world.block.BlockColor;

public class CreatingOwnMeshTestRenderer {
    private final ModelBatch modelBatch;
    private final Environment environment;
    private final Model testModel;
    private final ModelInstance testModelInstance;

    public CreatingOwnMeshTestRenderer() {
        modelBatch = new ModelBatch();

        // lightning
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.4f, 0.4f, 0.4f, -1f, -0.8f, -0.2f));


        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        Mesh mesh = new Mesh(true, 16, 36, VertexAttribute.Position(), VertexAttribute.Normal());
        mesh.setVertices(new float[]{
                0, 0, 0, -1, -1, -1, // so.. if i want to create a cube.. i apparently need 3
                0, 0, 1, -1, -1, -1, // vertices with different normals so the shadows aren't fucked up? Damn...
                1, 0, 0, -1, -1, -1,
                1, 0, 1, -1, -1, -1,
                0, 1, 0,  0,  1,  0,
                0, 1, 1,  0,  1,  0,
                1, 1, 0,  0,  1,  0,
                1, 1, 1,  0,  1,  0,
        });
        mesh.setIndices(new short[] {
                2, 1, 0, 1, 2, 3, // that was easier as i thought but the order matters.
                6, 4, 7, 5, 7, 4, // If i fuck up the order than the bottom plane can only be seen from above and so on
                2, 6, 3, 7, 3, 6,
                4, 0, 5, 1, 5, 0,
                4, 6, 0, 2, 0, 6,
                3, 7, 1, 5, 1, 7
        });

        modelBuilder.part("block", mesh, GL20.GL_TRIANGLES, BlockColor.ORANGE.createMaterial());
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
