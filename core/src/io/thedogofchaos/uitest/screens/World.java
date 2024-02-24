package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.fragments.HudFragment;

public class World extends ScreenAdapter {
    private UiTest game;
    private Stage stage;
    private HudFragment hud;

    public World(UiTest game) {
        this.game = game;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        hud = new HudFragment(); // Create a new instance of the HUD
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        hud.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta); // Update game logic
        stage.act(delta); // Update the stage
        stage.draw(); // Draw the stage
        hud.act(delta);
        hud.draw();
    }

    // Method to update game logic
    private void update(float delta) {
        // Implement game logic updates here
        hud.update(delta); // Update HUD elements
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true); // Update the viewport
        hud.resize(width, height); // Resize HUD elements
    }

    @Override
    public void dispose() {
        stage.dispose(); // Dispose of the stage
        hud.dispose(); // Dispose of HUD resources
    }
}
