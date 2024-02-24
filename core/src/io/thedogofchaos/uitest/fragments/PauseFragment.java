package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.thedogofchaos.uitest.Sounds;
import io.thedogofchaos.uitest.Vars;

public class PauseFragment extends Table {
    private OptionsFragment optionsMenu;
    private Stage pauseStage;
    private Slider masterVolume;
    private Slider mouseSensitivity;
    private Actor background;
    public PauseFragment(){
        Vars.currentStage = pauseStage;
        background = background();
        pauseStage = new Stage();
        Table stageTable = new Table();
        stageTable.setFillParent(true);
        stageTable.pad(25).setDebug(false);

        TextButton optionsButton = new TextButton("Options", Vars.gameSkin);
        stageTable.add(optionsButton);
        TextButton closeButton = new TextButton("Back", Vars.gameSkin);
        stageTable.add(closeButton);

        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                Vars.prevStage = pauseStage;
                optionsMenu.showOptionsMenu(true);
                Gdx.input.setInputProcessor(OptionsFragment.optionsStage);
            }
        });
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                showPauseMenu(false);
                Gdx.input.setInputProcessor(Vars.prevStage);
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

    public void showPauseMenu(boolean show) {
        if (show) {
            getStage().addActor(background);
        } else {
            background.remove();
        }
        setVisible(show);
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
