package de.estim8.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import de.estim8.test.math.SingleIntVector3;
import de.estim8.test.renderer.debug.ChunkDebugRenderer;
import de.estim8.test.renderer.debug.VersionRenderer;
import de.estim8.test.renderer.world.ChunkRenderer;
import de.estim8.test.world.block.BlockColor;
import de.estim8.test.world.block.Chunk;

import java.util.ArrayList;
import java.util.List;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {

    private VersionRenderer versionRenderer;

    // model stuff
    private PerspectiveCamera camera;
    private FirstPersonCameraController camController;

    private final List<ChunkRenderer> chunkRenderers = new ArrayList<>();
    private final List<ChunkDebugRenderer> chunkDebugRenderers = new ArrayList<>();

    @Override
    public void show() {
        versionRenderer = new VersionRenderer();

        int cCount = 0;
        int cBlocks = 0;
        int tmpChunks = 1;
        for (int x = -tmpChunks; x < tmpChunks; x++) {
            for (int z = -tmpChunks; z < tmpChunks; z++) {
                Chunk chunk = new Chunk(new SingleIntVector3(x, 0, z));
                cCount++;
                for (int b_x = 0; b_x < Chunk.CHUNK_WIDTH; b_x++) {
                    for (int b_y = 0; b_y < 5; b_y++) {
                        for (int b_z = 0; b_z < Chunk.CHUNK_WIDTH; b_z++) {
                            cBlocks++;
//                          double noise = SimplexNoise.noise((chunkAbsPosition.getX() + x) / 50f, (chunkAbsPosition.getY() + y) / 50f, (chunkAbsPosition.getZ() + z) / 50f);
//                          if (noise > .3d) {
                                final SingleIntVector3 position = new SingleIntVector3(b_x, b_y, b_z);
                                chunk.addBlock(position, BlockColor.CYAN);
//                          }
                        }
                    }
                }
                chunkRenderers.add(new ChunkRenderer(chunk));
                chunkDebugRenderers.add(new ChunkDebugRenderer(chunk));
            }
        }

        System.out.println("Bloody hell... " + cCount + " chunks???");
        System.out.println("Bloody hell... " + cBlocks + " blocks???");


        // setup camera
        camera = new PerspectiveCamera(80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 30f, 0f);
        camera.near = 1f;
        camera.far = 500f;
        camera.update();

        camController = new FirstPersonCameraController(camera);
        camController.setVelocity(50f);
        Gdx.input.setInputProcessor(camController);
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        versionRenderer.render();

        // camera controller
        camController.update();
        chunkRenderers.forEach(c -> c.render(camera));
        chunkDebugRenderers.forEach(c -> c.render(camera));
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        chunkRenderers.forEach(r -> r.dispose());
        chunkDebugRenderers.forEach(ChunkDebugRenderer::dispose);
        versionRenderer.dispose();
    }
}
