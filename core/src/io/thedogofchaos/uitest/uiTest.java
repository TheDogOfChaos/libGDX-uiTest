package io.thedogofchaos.uitest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

import javax.swing.event.ChangeEvent;

public class uiTest extends Game {
	private Skin skin;
	private Stage stage;

	ProgressBar healthBar;
	TextButton button1;
	TextButton button2;

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		root.pad(25);

		Table table = new Table();
		root.add(table).expand().left().bottom();
		button1 = new TextButton("Heal", skin);
		table.add(button1).width(75);
		button2 = new TextButton("Hurt", skin);
		table.add(button2).width(75);
		table.row();
		healthBar = new ProgressBar(0, 100, 1, false, skin);
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
