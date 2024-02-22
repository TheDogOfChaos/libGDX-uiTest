package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;

public class gameHud implements Screen, ApplicationListener {
	private Stage stage;
	private ProgressBar healthBar;
	private TextButton button1;
	private TextButton button2;

	public void create () {
		Vars.gameSkin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		root.pad(25);

		Table table = new Table();
		root.add(table).expand().left().bottom();
		button1 = new TextButton("Heal", Vars.gameSkin);
		table.add(button1).width(75);
		button2 = new TextButton("Hurt", Vars.gameSkin);
		table.add(button2).width(75);
		table.row();
		healthBar = new ProgressBar(0, 100, 1, false, Vars.gameSkin);
        table.add(healthBar).colspan(2);

		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				updateHP(5);
			}
		});
		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				updateHP(-5);
			}
		});
	}

	private void updateHP(Integer newVal){
		healthBar.setValue(healthBar.getValue()+newVal);
	}

	@Override
	public void render (float v) {
		Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		Vars.gameSkin.dispose();
		stage.dispose();
	}
}
