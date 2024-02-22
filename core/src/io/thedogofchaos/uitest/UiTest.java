package io.thedogofchaos.uitest;

import com.badlogic.gdx.Game;
import io.thedogofchaos.uitest.screens.MainMenu;

public class UiTest extends Game {

    @Override
    public void create() {
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){

    }
}
