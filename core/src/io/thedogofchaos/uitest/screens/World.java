package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.fragments.*;

public class World extends ScreenAdapter {
    private final OptionsFragment optionsMenu;
    private final PauseFragment pauseMenu;
    private final UiTest game;
    private final Stage worldStage;
    private final HudFragment hud;

    public World(UiTest game) {
        this.game = game;
        worldStage = new Stage(new ScreenViewport());
        Table temp = new Table();
        temp.setFillParent(true);
        hud = new HudFragment();
        optionsMenu = new OptionsFragment();
        optionsMenu.showOptionsMenu(false);
        pauseMenu = new PauseFragment();
        pauseMenu.showPauseMenu(false);

        TextureRegionDrawable placeholderImageDrawable = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        Image placeholderImage = new Image(placeholderImageDrawable);
        temp.add(placeholderImage);

        worldStage.addActor(temp);
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputAdapter() {
            public boolean keyDown(int keycode) {
                if (keycode == 111) {
                    System.out.println("Esc pressed!");
                    // TODO: FIX THIS, DOESN'T DETECT ESCAPE PRESS
                    if (Vars.currentStage == worldStage) {
                        pauseMenu.showPauseMenu(true);
                        return true;
                    } else if (Vars.currentStage == PauseFragment.pauseStage) {
                        pauseMenu.showPauseMenu(false);
                        return true;
                    }

                }
                return false;
            }
        });
        inputMultiplexer.addProcessor(worldStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        hud.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta); // Update game logic
        worldStage.act(delta); // Update the stage
        worldStage.draw(); // Draw the stage
        hud.act(delta);
        hud.draw();
        if (optionsMenu.isVisible()) {
            optionsMenu.act(delta);
            optionsMenu.draw();
        }
        if (pauseMenu.isVisible()){
            pauseMenu.act(delta);
            pauseMenu.draw();
        }
    }

    // Method to update game logic
    private void update(float delta) {
        hud.update(delta);
        optionsMenu.update(delta);
        pauseMenu.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        worldStage.getViewport().update(width, height, true); // Update the viewport
        hud.resize(width, height); // Resize HUD elements
    }

    @Override
    public void dispose() {
        worldStage.dispose(); // Dispose of the stage
        hud.dispose(); // Dispose of HUD resources
    }
}
