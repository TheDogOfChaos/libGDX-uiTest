package io.thedogofchaos.uitest;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.thedogofchaos.uitest.fragments.PauseFragment;

public class GameInputProcessor implements InputProcessor {

    PauseFragment pauseMenu = new PauseFragment();

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("Key pressed: " + keycode);
        if (keycode == Input.Keys.ESCAPE) {
            System.out.println(Vars.currentStage);
            if (Vars.currentStage == "worldStage") {
                pauseMenu.setVisible(true);
                System.out.println("pause menu should be visible now");
                Vars.currentStage = "pauseStage";
                return true;
            } else if (Vars.currentStage == "pauseStage") {
                pauseMenu.setVisible(false);
                System.out.println("pause menu should NOT be visible now");
                Vars.currentStage = "worldStage";
                return true;
            } else {
                System.out.println("Pause Menu did not render (Current stage: " + Vars.currentStage + ")");
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
