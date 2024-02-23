package io.thedogofchaos.uitest.fragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.thedogofchaos.uitest.Vars;

public class OptionsFragment extends Table {
    // Overlay for the options menu
    public OptionsFragment (){
        Stage stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.pad(25);

        Slider masterVolume = new Slider(0,100,1,false, Vars.gameSkin);
        root.add(masterVolume).expand().top().left();
        Slider mouseSensitivity = new Slider(0,100,1,false, Vars.gameSkin);
        root.add(mouseSensitivity).expand().top().left();
    }
}
