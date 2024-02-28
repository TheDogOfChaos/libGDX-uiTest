package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sun.tools.javac.Main;
import io.thedogofchaos.uitest.screens.MainMenu;
import io.thedogofchaos.uitest.Sounds;
import io.thedogofchaos.uitest.UiTest;
import io.thedogofchaos.uitest.Vars;
import io.thedogofchaos.uitest.screens.World;
import com.badlogic.gdx.Screen;
import org.w3c.dom.Text;

import static io.thedogofchaos.uitest.screens.MainMenu.mainMenuStage;

public class PauseFragment extends Table {
    private OptionsFragment optionsMenu;
    public static Stage pauseStage;
    private Actor background;
    public PauseFragment(){
        background = background();
        pauseStage = new Stage();
        optionsMenu = new OptionsFragment(pauseStage);
        Table stageTable = new Table();
        stageTable.setFillParent(true);
        stageTable.pad(25).setDebug(false);

        pauseStage.addActor(background);
        TextButton optionsButton = new TextButton("Options", Vars.gameSkin);
        stageTable.add(optionsButton);
        stageTable.row();
        TextButton closeButton = new TextButton("Back", Vars.gameSkin);
        stageTable.add(closeButton);
        stageTable.row();
        TextButton exitButton = new TextButton("Exit to Main Menu", Vars.gameSkin);
        stageTable.add(exitButton);

        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                optionsMenu.setVisible(true);
                Gdx.input.setInputProcessor(OptionsFragment.optionsStage);
            }
        });
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                setVisible(false);
                Gdx.input.setInputProcessor(World.inputMultiplexer);
            }
        });
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Sounds.click.play(1f);
                setVisible(false);
                Gdx.input.setInputProcessor(mainMenuStage);
                MainMenu.game.setScreen(new MainMenu(MainMenu.game));
            }
        });

        pauseStage.addActor(stageTable);
    }


    // TODO: maybe make this not boilerplate-y in future
    private Actor background() {
        Actor background = new Actor() {
            final Texture whitePixel = new Texture(Gdx.files.internal("whitepixel.png"));
            @Override
            public void draw(Batch batch, float parentAlpha) {
                // this draws a semi-transparent black rectangle covering the entire screen
                batch.setColor(0, 0, 0, 0.75f); // Semi-transparent black color
                batch.draw(whitePixel, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.setColor(Color.WHITE); // Reset color
            }
        };
        background.setTouchable(Touchable.disabled);
        return background;
    }

    public void update(float delta) {

    }

    public void draw() {
        Gdx.input.setInputProcessor(pauseStage);
        pauseStage.act();
        pauseStage.draw();
    }

    public void resize(int width, int height) {
        pauseStage.getViewport().update(width, height, true);
    }

    public void dispose() {
        pauseStage.dispose();
    }
}
