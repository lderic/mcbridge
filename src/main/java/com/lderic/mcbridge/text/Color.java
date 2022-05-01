package com.lderic.mcbridge.text;

public class Color {
    private final int rgb;

    public Color(int r, int g, int b) {
        this(r << 16 | g << 8 | b);
    }

    public Color(int rgb) {
        this.rgb = rgb;
    }

    public static final Color BLACK = new Color(0x000000);
    public static final Color DARK_BLUE = new Color(0x0000AA);
    public static final Color DARK_GREEN = new Color(0x00AA00);
    public static final Color DARK_AQUA = new Color(0x00AAAA);
    public static final Color DARK_RED = new Color(0xAA0000);
    public static final Color DARK_PURPLE = new Color(0xAA00AA);
    public static final Color GOLD = new Color(0xFFAA00);
    public static final Color GRAY = new Color(0xAAAAAA);
    public static final Color DARK_GRAY = new Color(0x555555);
    public static final Color BLUE = new Color(0x5555FF);
    public static final Color GREEN = new Color(0x55FF55);
    public static final Color AQUA = new Color(0x55FFFF);
    public static final Color RED = new Color(0xFF5555);
    public static final Color LIGHT_PURPLE = new Color(0xFF55FF);
    public static final Color YELLOW = new Color(0xFFFF55);
    public static final Color WHITE = new Color(0xFFFFFF);


    public int getRgb() {
        return rgb;
    }

    public String toString() {
        return String.format("#%06x", rgb);
    }
}
