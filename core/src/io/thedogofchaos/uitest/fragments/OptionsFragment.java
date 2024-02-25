package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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

public class OptionsFragment extends Table {
    public static Stage optionsStage;
    private Slider masterVolume;
    private Slider mouseSensitivity;
    private Actor background;
    public OptionsFragment (final InputProcessor prevStage){
        background = background();
        optionsStage = new Stage();
        Table stageTable = new Table();
        stageTable.setFillParent(true);
        stageTable.pad(25).setDebug(true);

        Table optionsTable = new Table();
        stageTable.add(optionsTable).expand().top().left();

        Label labelVolume = new Label("Master Volume:", Vars.gameSkin);
        optionsTable.add(labelVolume);
        masterVolume = new Slider(0,100,1,false, Vars.gameSkin);
        optionsTable.add(masterVolume);
        optionsTable.row();
        Label labelMouseSensitivity = new Label("Mouse Sensitivity:", Vars.gameSkin);
        optionsTable.add(labelMouseSensitivity);
        mouseSensitivity = new Slider(0,100,1,false, Vars.gameSkin);
        optionsTable.add(mouseSensitivity);
        TextButton backButton = new TextButton("Back", Vars.gameSkin);
        stageTable.add(backButton).expand().bottom().left();

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Sounds.click.play(1f);
                showOptionsMenu(false);
                Gdx.input.setInputProcessor(prevStage);
            }
        });

        optionsStage.addActor(stageTable);
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

    public void showOptionsMenu(boolean show) {
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
        Gdx.input.setInputProcessor(optionsStage);
        optionsStage.act();
        optionsStage.draw();
    }

    public void resize(int width, int height) {
        optionsStage.getViewport().update(width, height, true);
    }

    public void dispose() {
        optionsStage.dispose();
    }
}
