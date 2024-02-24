package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.fragments.HudFragment;

//
public class World extends ScreenAdapter {

    Stage stage;
    UiTest game;
    HudFragment hud;
    public World(UiTest game){
        System.out.println("World loaded!");
        this.game = game;
        HudFragment hud = new HudFragment();
    }
    @Override
    public void show() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        hud.act(delta);
        hud.getStage().draw();
    }
}
