package com.lderic.mcbridge.minecraft.text;

public class Color {
    private final String value;

    private Color(String value) {
        this.value = value;
    }

    public Color(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException("RGB values must be between 0 and 255");
        }
        this.value = String.format("#%x%x%x", r, g, b);
    }

    public String getValue() {
        return value;
    }

    public static Color BLACK = new Color("black");
    public static Color DARK_BLUE = new Color("dark_blue");
    public static Color DARK_GREEN = new Color("dark_green");
    public static Color DARK_AQUA = new Color("dark_aqua");
    public static Color DARK_RED = new Color("dark_red");
    public static Color DARK_PURPLE = new Color("dark_purple");
    public static Color GOLD = new Color("gold");
    public static Color GRAY = new Color("gray");
    public static Color DARK_GRAY = new Color("dark_gray");
    public static Color BLUE = new Color("blue");
    public static Color GREEN = new Color("green");
    public static Color AQUA = new Color("aqua");
    public static Color RED = new Color("red");
    public static Color LIGHT_PURPLE = new Color("light_purple");
    public static Color YELLOW = new Color("yellow");
    public static Color WHITE = new Color("white");
}
