package Modele;

import java.awt.Color;

public class ColorData {
    public int r, g, b;

    public ColorData() {}

    public ColorData(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    public Color toColor() {
        return new Color(r, g, b);
    }
}

