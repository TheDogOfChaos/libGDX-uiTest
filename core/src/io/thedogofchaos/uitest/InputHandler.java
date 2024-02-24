package io.thedogofchaos.uitest;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.thedogofchaos.uitest.fragments.PauseFragment;
import io.thedogofchaos.uitest.screens.World;
import io.thedogofchaos.uitest.Vars;

public class InputHandler implements InputProcessor {

    InputProcessor worldStage;
    InputProcessor pauseStage;
    PauseFragment pauseMenu;

    InputProcessor stage = Vars.currentStage;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            // TODO: FIX THIS, DOESN'T DETECT ESCAPE PRESS
            if (stage.equals(worldStage)) {
                pauseMenu.showPauseMenu(true);
                return true;
            } else if (stage.equals(pauseStage)) {
                pauseMenu.showPauseMenu(false);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
