package io.thedogofchaos.uitest.screens;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Sounds;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.fragments.OptionsFragment;

public class MainMenu implements Screen {

    public static Stage mainMenuStage;
    final UiTest game;
    private OptionsFragment optionsMenu;
    private final Table mainMenu;

    public MainMenu(final UiTest game) {
        this.game = game;
        mainMenuStage = new Stage(new ScreenViewport());
        mainMenu = new Table();
        mainMenu.setFillParent(true);
        mainMenu.pad(25).setDebug(false);

        optionsMenu = new OptionsFragment(mainMenuStage);
        optionsMenu.setVisible(false);

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

        // this checks when buttons are pressed
        startButton.addListener(new ChangeListener() { // works very fine
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                game.setScreen(new World(game));
            }
        });
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                optionsMenu.setVisible(true);
            }
        });
        exitButton.addListener(new ChangeListener() { // this works fine
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                Gdx.app.exit();
            }
        });

        mainMenuStage.addActor(optionsMenu);
        mainMenuStage.addActor(mainMenu);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainMenuStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenuStage.act();
        mainMenuStage.draw();
        if (optionsMenu.isVisible()) {
            optionsMenu.act(delta);
            optionsMenu.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        mainMenuStage.getViewport().update(width, height, true);
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
        mainMenuStage.dispose();
    }
}
