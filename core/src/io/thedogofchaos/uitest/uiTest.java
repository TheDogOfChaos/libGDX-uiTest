package io.thedogofchaos.uitest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class uiTest extends ApplicationAdapter {
	private Skin skin;
	private Stage stage;

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);

		root.pad(25);
		TextButton textButton = new TextButton("Fire!", skin);
		root.add(textButton).expand().bottom().left();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose () {
		skin.dispose();
		stage.dispose();
	}
}
