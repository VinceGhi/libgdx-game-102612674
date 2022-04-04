package de.estim8.test;

import com.badlogic.gdx.Game;
import de.estim8.test.renderer.base.VersionRenderer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMain extends Game {

	@Override
	public void create() {
		setScreen(new FirstScreen());
	}
}
