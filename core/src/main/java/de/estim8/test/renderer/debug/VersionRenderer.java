package de.estim8.test.renderer.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VersionRenderer {
    // renderVersion() stuff
    private SpriteBatch batch;
    private BitmapFont font;

    public VersionRenderer() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    public void render() {
        batch.begin();
        font.setColor(1f, 1f, 1f, 0.25f);
        font.draw(batch, "v0.0.1-alpha", 10, Gdx.graphics.getHeight() - 10);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
