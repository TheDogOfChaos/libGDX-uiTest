package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.fragments.HudFragment;

public class World extends ScreenAdapter {
    private Stage stage;
    private Game game;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private HudFragment hud;

    public World(UiTest game){
        System.out.println("World loaded!");
        this.game = game;
        hud = new HudFragment();
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(hud);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        hud.draw(spriteBatch, delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        hud.getViewport().update(width, height, true);
    }

    @Override
    public void dispose(){
        stage.dispose();
        spriteBatch.dispose();
    }
}
