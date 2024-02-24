package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;

public class OptionsFragment extends Table {
    private Stage stage;
    private Table table;
    public OptionsFragment (){
        stage = new Stage();
        table = new Table();
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.pad(25).setDebug(true);

        Slider masterVolume = new Slider(0,100,1,false, Vars.gameSkin);
        table.add(masterVolume).expand().top().left();
        Slider mouseSensitivity = new Slider(0,100,1,false, Vars.gameSkin);
        table.add(mouseSensitivity).expand().top().left();

        stage.addActor(table);
    }

    public void update(float delta) {

    }

    // Method to draw HUD elements
    public void draw() {
        stage.act(); // Update the stage
        stage.draw(); // Draw the stage
    }

    // Method to resize HUD elements
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    // Dispose method to release resources when no longer needed
    public void dispose() {
        stage.dispose();
    }
}
