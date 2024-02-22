package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.uiTest;

public class mainMenu implements Screen {

    private final Stage stage;

    private final TextButton startButton;
    private final TextButton optionsButton;
    private final TextButton exitButton;

    public mainMenu(uiTest uiTest) {
        stage = new Stage(new ScreenViewport());

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        root.pad(25).setDebug(false);

        TextureRegionDrawable drawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("UNTITLED-FACTORY-GAME.png")));
        Image titleImage = new Image(drawable);
        root.add(titleImage).expand().left().top();
        root.row();

        startButton = new TextButton("Play", Vars.gameSkin);
        root.add(startButton).space(25).width(150).left();
        root.row();
        optionsButton = new TextButton("Options", Vars.gameSkin);
        root.add(optionsButton).space(25).width(100).left();
        root.row();
        exitButton = new TextButton("Exit", Vars.gameSkin);
        root.add(exitButton).space(25).width(50).left();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
