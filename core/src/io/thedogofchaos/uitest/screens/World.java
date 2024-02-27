package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.GameInputProcessor;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.fragments.*;

public class World extends ScreenAdapter {
    private final OptionsFragment optionsMenu;
    private final PauseFragment pauseMenu;
    public static Stage worldStage;
    private final HudFragment hud;
    private final Stage hudStage;
    private final InputProcessor gameInputProcessor = new GameInputProcessor();
    public static InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public World(UiTest game) {
        worldStage = new Stage(new ScreenViewport());
        Vars.currentStage = "worldStage";
        Table temp = new Table();
        temp.setFillParent(true);
        hud = new HudFragment();
        hudStage = HudFragment.hudStage;
        optionsMenu = new OptionsFragment(worldStage);
        optionsMenu.showOptionsMenu(false);
        pauseMenu = new PauseFragment();
        pauseMenu.showPauseMenu(false);

        TextureRegionDrawable placeholderImageDrawable = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        Image placeholderImage = new Image(placeholderImageDrawable);
        temp.add(placeholderImage);

        hudStage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pauseMenu.showPauseMenu(true);
            }
        });
        worldStage.addActor(temp);
        worldStage.addActor(pauseMenu);
    }

    @Override
    public void show() {
        inputMultiplexer.addProcessor(HudFragment.hudStage);
        inputMultiplexer.addProcessor(gameInputProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);
        hud.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta); // Update logic
        worldStage.act(delta); // Update the stage
        worldStage.draw(); // Draw the stage
        if (optionsMenu.isVisible()) {
            optionsMenu.act(delta);
            optionsMenu.draw();
        }
        if (pauseMenu.isVisible()) {
            hud.act(delta);
            hud.draw();
            pauseMenu.act(delta);
            pauseMenu.draw();
        } else if (!pauseMenu.isVisible()) {
            hud.act(delta);
            hud.draw();
        }
    }

    // Method to update logic
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
