package de.estim8.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import de.estim8.test.renderer.ChunkDebugRenderer;
import de.estim8.test.renderer.ChunkRenderer;
import de.estim8.test.renderer.base.VersionRenderer;
import de.estim8.test.renderer.block.Block;
import de.estim8.test.renderer.block.BlockColor;
import de.estim8.test.renderer.block.Chunk;
import de.estim8.test.renderer.math.IntVector3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

	private VersionRenderer versionRenderer;

	// model stuff
	private PerspectiveCamera camera;
	private CameraInputController camController;

	private final List<ChunkRenderer> chunkRenderers = new ArrayList<>();
	private final List<Chunk> chunks = new ArrayList<>();
	private final List<ChunkDebugRenderer> chunkDebugRenderers = new ArrayList<>();



	@Override
	public void show() {
		versionRenderer = new VersionRenderer();

		// setup camera
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(10f, 10f, 10f);
		camera.lookAt(0,0,0);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();

		// camera controller
		camController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(camController);

		int tmpChunks = 1;
		for (int x = -tmpChunks; x < tmpChunks; x++) {
			for (int z = -tmpChunks; z < tmpChunks; z++) {
				chunks.add(new Chunk(new IntVector3(x, 0, z)));
			}
		}

//		chunks.add(new Chunk(new IntVector3(0, 0, 0)));
//		chunks.add(new Chunk(new IntVector3(0, 0, 1)));

		for (int x = 0; x < Chunk.CHUNK_WIDTH; x++) {
			for (int y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
				for (int z = 0; z < Chunk.CHUNK_WIDTH; z++) {
					if (y <= Chunk.CHUNK_HEIGHT / 3) {
						final IntVector3 position = new IntVector3(x, y, z);
						chunks.forEach(chunk -> chunk.addBlock(position, BlockColor.GREEN));
					}
				}
			}
		}

		chunks.forEach(chunk -> chunkRenderers.add(new ChunkRenderer(chunk)));

		chunks.forEach(c -> {
			chunkDebugRenderers.add(new ChunkDebugRenderer(c));
		});

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
		chunkRenderers.forEach(ChunkRenderer::dispose);
		chunkDebugRenderers.forEach(ChunkDebugRenderer::dispose);
		versionRenderer.dispose();
	}
}
