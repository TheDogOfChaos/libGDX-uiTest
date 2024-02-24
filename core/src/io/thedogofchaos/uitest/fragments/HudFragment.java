package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.thedogofchaos.uitest.Sounds;
import io.thedogofchaos.uitest.Vars;

public class HudFragment extends Table{
	private final Stage stage;
	private final Table stageTable;
	private final ProgressBar healthBar;
	public HudFragment() {
		stage = new Stage();
		stageTable = new Table();
		stageTable.setFillParent(true);
		stageTable.pad(25).setDebug(false);

		Table essentialStatsTable = new Table();
		stageTable.add(essentialStatsTable).expand().bottom().left();

		TextButton heal = new TextButton("Heal", Vars.gameSkin);
		essentialStatsTable.add(heal);
		TextButton hurt = new TextButton("Hurt", Vars.gameSkin);
		essentialStatsTable.add(hurt);
		essentialStatsTable.row();
        healthBar = new ProgressBar(0, 100, 1, false, Vars.gameSkin);
        essentialStatsTable.add(healthBar).colspan(2);

		heal.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Sounds.click.play(1f);
				healthBar.setValue(healthBar.getValue()+5);
			}
		});
		hurt.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Sounds.click.play(1f);
				healthBar.setValue(healthBar.getValue()-5);
			}
		});

		stage.addActor(stageTable);
	}

	public void update(float delta) {
		//
	}

	// Method to draw HUD elements
	public void draw() {
		Gdx.input.setInputProcessor(stage);
		stage.act(); // Update the stage
		stage.draw(); // Draw the stage
	}

	// Method to resize HUD elements
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	// Dispose method to release resources when no longer needed
	public void dispose() {
		stage.dispose();
	}
}
