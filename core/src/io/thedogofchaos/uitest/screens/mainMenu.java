package io.thedogofchaos.uitest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.uiTest;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

public class mainMenu implements Screen {

    private final Stage stage;
    private Game game;

    private final TextButton startButton;
    private final TextButton optionsButton;
    private final TextButton exitButton;

    public mainMenu(uiTest uiTest) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        root.pad(25);
        // TODO: Add title image for main menu
        //Image titleImage = new Image();
        //root.add(titleImage).expand().left().top();

        Table menuButtons = new Table();

        startButton = new TextButton("Play", Vars.gameSkin);
        menuButtons.add(startButton).expand().bottom().left().colspan(3);
        menuButtons.row();
        optionsButton = new TextButton("Options", Vars.gameSkin);
        menuButtons.add(optionsButton).expand().bottom().left().colspan(2);
        menuButtons.row();
        exitButton = new TextButton("Options", Vars.gameSkin);
        menuButtons.add(exitButton).expand().bottom().left();





    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

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

    }
}
