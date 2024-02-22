package io.thedogofchaos.uitest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.thedogofchaos.uitest.screens.mainMenu;

public class uiTest extends Game {

    @Override
    public void create() {
        this.setScreen(new mainMenu(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){

    }
}
