package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.thedogofchaos.uitest.Vars;

public class HudFragment extends Table{
	private Stage stage;
	private Table table;

	public HudFragment() {
		stage = new Stage();
		table = new Table();

		ProgressBar healthBar = new ProgressBar(0, 100, 1, false, Vars.gameSkin);
		table.add(healthBar);

		table.setFillParent(true);

		stage.addActor(table);
	}

	public void update(float delta) {

	}

	// Method to draw HUD elements
	public void draw() {
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
