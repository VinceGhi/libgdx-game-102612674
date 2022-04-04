package de.estim8.test.world.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import de.estim8.test.math.IntVector3;
import de.estim8.test.math.SimplexNoise;
import de.estim8.test.math.SingleIntVector3;

import java.util.Arrays;
import java.util.Random;

public enum BlockColor {
    RED(0, 255, 0, 0, 255),
    YELLOW(1, 255, 255, 0, 255),
    GREEN(2, 0, 255, 0, 100),
    CYAN(3, 0, 255, 255, 255),
    BLUE(4, 0, 0, 255, 255),
    PURPLE(5, 255, 0, 255, 255),
    BROWN(6, 176, 149, 107, 255),
    ORANGE(7, 242, 118, 29, 255),
    GREY(8, 100, 100, 100, 255);

    private int id;
    private final short red, green, blue, alpha;

    BlockColor(int id, int red, int green, int blue, int alpha) {
        checkValues(red, green, blue, alpha);
        this.id = id;
        this.red = (short) red;
        this.green = (short) green;
        this.blue = (short) blue;
        this.alpha = (short) alpha;
    }

    public int getId() {
        return id;
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

    public Material createMaterial() {
        return new Material(createDiffuse());
    }

    public ColorAttribute createDiffuseNoise(IntVector3 position) {
        double random = (SimplexNoise.noise(position.getX(), position.getY(), position.getZ()) / 2 + 1) / 10;
        float red = (float) (getRedFloat() - (getRedFloat() * random));
        float green = (float) (getGreenFloat() - (getGreenFloat() * random));
        float blue = (float) (getBlueFloat() - (getBlueFloat() * random));
        return ColorAttribute.createDiffuse(red, green, blue, getAlphaFloat());
    }

    public Color getNoisyColor(IntVector3 position) {
        double random = (SimplexNoise.noise(position.getX(), position.getY(), position.getZ()) / 2 + 1) / 10;
        float red = (float) (getRedFloat() - (getRedFloat() * random));
        float green = (float) (getGreenFloat() - (getGreenFloat() * random));
        float blue = (float) (getBlueFloat() - (getBlueFloat() * random));
        return new Color(red, green, blue, getAlphaFloat());
    }

    public Color getNoisyColor(SingleIntVector3 position) {
        return getNoisyColor(new IntVector3(position.getX(), position.getY(), position.getZ()));
    }

    public Material createMaterialNoise(IntVector3 position) {
        return new Material(createDiffuseNoise(position));
    }

    public static BlockColor random() {
        BlockColor[] colors = BlockColor.values();
        int random = new Random().nextInt(colors.length);
        return colors[random];
    }

    public static BlockColor getById(int id) {
        return Arrays.stream(BlockColor.values()).filter(c -> c.getId() == id).findFirst().orElse(null);
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
