package de.estim8.test.renderer.math;

import com.badlogic.gdx.math.Vector3;
import lombok.Data;

public class IntVector3 {
    private int x, y, z;

    public IntVector3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public IntVector3 setX(int x) {
        this.x = x;
        return this;
    }
    
    public IntVector3 addX(int x) {
        this.x += x;
        return this;
    }

    public int getY() {
        return y;
    }

    public IntVector3 setY(int y) {
        this.y = y;
        return this;
    }

    public IntVector3 addY(int y) {
        this.y += y;
        return this;
    }

    public int getZ() {
        return z;
    }

    public IntVector3 setZ(int z) {
        this.z = z;
        return this;
    }

    public IntVector3 addZ(int z) {
        this.z += z;
        return this;
    }

    public Vector3 getVector3() {
        return new Vector3(x, y, z);
    }

    public IntVector3 multiply(int value) {
        return new IntVector3(getX() * value, getY() * value, getZ() * value);
    }

    @Override
    public String toString() {
        return "IntVector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
