package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.fragments.OptionsFragment;

public class MainMenu implements Screen {

    public Stage stage;
    final UiTest game;
    private OptionsFragment optionsFragment;
    private Table mainMenu;

    public MainMenu(final UiTest game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        mainMenu = new Table();
        mainMenu.setFillParent(true);
        mainMenu.pad(25).setDebug(true);

        optionsFragment = new OptionsFragment();
        optionsFragment.setVisible(false);

        // this adds the title image
        TextureRegionDrawable drawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("UNTITLED-FACTORY-GAME.png")));
        Image titleImage = new Image(drawable);
        mainMenu.add(titleImage).expand().left().top();
        mainMenu.row();

        // this adds the actual buttons of the main menu
        TextButton startButton = new TextButton("Play", Vars.gameSkin);
        mainMenu.add(startButton).space(25).width(150).left();
        mainMenu.row();
        TextButton optionsButton = new TextButton("Options", Vars.gameSkin);
        mainMenu.add(optionsButton).space(25).width(100).left();
        mainMenu.row();
        TextButton exitButton = new TextButton("Exit", Vars.gameSkin);
        mainMenu.add(exitButton).space(25).width(50).left();

        stage.addActor(optionsFragment);
        stage.addActor(mainMenu);
        // this checks when buttons are pressed
        startButton.addListener(new ChangeListener() { // works very fine
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new World(game));
            }
        });
        optionsButton.addListener(new ChangeListener() { // TODO: make this render properly
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                optionsFragment.setVisible(!optionsFragment.isVisible());
                System.out.println(optionsFragment.isVisible());
            }
        });
        exitButton.addListener(new ChangeListener() { // this works fine
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
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
