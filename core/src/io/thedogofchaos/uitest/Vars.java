package io.thedogofchaos.uitest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Vars {
    public static Skin gameSkin = new Skin(Gdx.files.internal("metalui/metal-ui.json"));
    public static InputProcessor prevStage;
    public static InputProcessor currentStage;
}
