package de.estim8.test.renderer.block;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import de.estim8.test.renderer.math.IntVector3;
import de.estim8.test.renderer.math.SimplexNoise;

import java.util.Random;

public enum BlockColor {
    RED(255, 0, 0, 255),
    YELLOW(255, 255, 0, 255),
    GREEN(0, 255, 0, 100),
    CYAN(0, 255, 255, 255),
    BLUE(0, 0, 255, 255),
    PURPLE(255, 0, 255, 255),
    GREY(100, 100, 100, 255);

    private final short red, green, blue, alpha;

    BlockColor(int red, int green, int blue, int alpha) {
        checkValues(red, green, blue, alpha);
        this.red = (short) red;
        this.green = (short) green;
        this.blue = (short) blue;
        this.alpha = (short) alpha;
    }
    /**
     * @return Red mapped to a float from 0.0f to 1.0f
     */
    public float getRedFloat() {
        return red / 255f;
    }

    public int getRedInt() {
        return red;
    }

    /**
     * @return Green mapped to a float from 0.0f to 1.0f
     */
    public float getGreenFloat() {
        return green / 255f;
    }

    public int getGreenInt() {
        return green;
    }

    /**
     * @return Blue mapped to a float from 0.0f to 1.0f
     */
    public float getBlueFloat() {
        return blue / 255f;
    }

    public int getBlueInt() {
        return blue;
    }

    /**
     * @return Alpha mapped to a float from 0.0f to 1.0f
     */
    public float getAlphaFloat() {
        return alpha / 255f;
    }

    public int getAlphaInt() {
        return alpha;
    }

    public ColorAttribute createDiffuse() {
        return ColorAttribute.createDiffuse(getRedFloat(), getGreenFloat(), getBlueFloat(), getAlphaFloat());
    }

    public ColorAttribute createDiffuseNoise(IntVector3 position) {
        double random = (SimplexNoise.noise(position.getX(), position.getY(), position.getZ()) / 2 + 1) / 10;
        float red = (float) (getRedFloat() - (getRedFloat() * random));
        float green = (float) (getGreenFloat() - (getGreenFloat() * random));
        float blue = (float) (getBlueFloat() - (getBlueFloat() * random));
        return ColorAttribute.createDiffuse(red, green, blue, getAlphaFloat());
    }

    public Material createMaterial() {
        return new Material(createDiffuse());
    }

    public Material createMaterialNoise(IntVector3 position) {
        return new Material(createDiffuseNoise(position));
    }

    public static BlockColor random() {
        BlockColor[] colors = BlockColor.values();
        int random = new Random().nextInt(colors.length);
        return colors[random];
    }

    /**
     * Check if the values are between 0 and 255
     * @param values - red, green, blue, alpha
     */
    private void checkValues(int... values) {
        for (int i : values) {
            if (i < 0 || i > 255) {
                throw new RuntimeException(this.name() + " has invalid values!");
            }
        }
    }
}
