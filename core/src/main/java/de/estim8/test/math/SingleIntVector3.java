package de.estim8.test.math;

public class SingleIntVector3 {
    private long position;

    /*
     * I just wanted to use bitwise stuff because i never have the chance to use it at work... and i think it's kinda cool to store 3x 20bit signed integer in a long. I actually have a bit left... :D
     */
    public SingleIntVector3(int x, int y, int z) {
        validateValue(x);
        validateValue(y);
        validateValue(z);
        position = position | ((long) ((x < 0 ? (~(x)+1) : x) & 0xFFFFF) << 42);
        position = position | ((long) ((y < 0 ? (~(y)+1) : y) & 0xFFFFF) << 21);
        position = position | ((long) ((z < 0 ? (~(z)+1) : z) & 0xFFFFF));


        if (x < 0) {
            position = position | (1L << 62);
        }

        if (y < 0) {
            position = position | (1L << 41);
        }

        if (z < 0) {
            position = position | (1L << 20);
        }
    }

    public int getX() {
        int result = (int) (position >> 42 & 0xFFFFFL);
        return (position >> 62 & 1) == 1 ? -result : result;
    }

    public int getY() {
        int result = (int) (position >> 21 & 0xFFFFFL);
        return (position >> 41 & 1) == 1 ? -result : result;
    }

    public int getZ() {
        int result = (int) (position & 0xFFFFFL);
        return  (position >> 20 & 1) == 1 ? -result : result;
    }

    @Override
    public String toString() {
        return "IntVector3{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                ", position=" + position +
                ", bits=" + Long.toBinaryString(position) +
                '}';
    }

    private void validateValue(int i) {
        if (Math.abs(i) > 0xFFFFFL) {
            throw new RuntimeException("Value is out of range +/-" + 0xFFFFFL);
        }
    }
}
