package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;

public class HudFragment extends Table {
    private final ProgressBar healthBar;

    public HudFragment() {
        Stage stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		root.pad(25);

		Table table = new Table();
		root.add(table).expand().left().bottom();
        TextButton button1 = new TextButton("Heal", Vars.gameSkin);
		table.add(button1).width(75);
        TextButton button2 = new TextButton("Hurt", Vars.gameSkin);
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
}
