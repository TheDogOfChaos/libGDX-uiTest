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
import io.thedogofchaos.uitest.Sounds;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.fragments.*;

import static io.thedogofchaos.uitest.fragments.PauseFragment.pauseStage;

public class World extends ScreenAdapter {
    private final OptionsFragment optionsMenu;
    private final PauseFragment pauseMenu;
    public static Stage worldStage;
    private final HudFragment hud;
    private final Stage hudStage;
    private final InputProcessor gameInputProcessor = new GameInputProcessor();
    public static InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public World() {
        worldStage = new Stage(new ScreenViewport());
        Vars.currentStage = "worldStage";
        Table temp = new Table();
        temp.setFillParent(true);
        hud = new HudFragment();
        hudStage = HudFragment.hudStage;
        optionsMenu = new OptionsFragment(worldStage);
        optionsMenu.setVisible(false);
        pauseMenu = new PauseFragment();
        pauseMenu.setVisible(false);

        TextureRegionDrawable placeholderImageDrawable = new TextureRegionDrawable(new Texture("badlogic.jpg"));
        Image placeholderImage = new Image(placeholderImageDrawable);
        temp.add(placeholderImage);

        HudFragment.pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Sounds.click.play(1f);
                pauseMenu.setVisible(true);
            }
        });

        worldStage.addActor(temp);
        worldStage.addActor(pauseMenu); // Fun fact: The oversight of not including this very line in my code absolutely BROKE me mentally for about 3 to 4 days. So yeah, don't forget about the stupidly simple stuff like this.
        pauseStage.addActor(optionsMenu);
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
        hud.act(delta);
        hud.draw();
        if (optionsMenu.isVisible()) {
            optionsMenu.act(delta);
            optionsMenu.draw();
        }
        if (pauseMenu.isVisible()) {
            pauseMenu.act(delta);
            pauseMenu.draw();
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
        pauseMenu.dispose();
        optionsMenu.dispose();
    }
}
