package com.lderic.mcbridge.text;

public class Text {
    private final String value;
    private boolean isBold = false;
    private boolean isItalic = false;
    private boolean isUnderlined = false;
    private boolean isStrikethrough = false;
    private boolean isObfuscated = false;
    private Color color;
    private String insertion;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;


    public Text(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public boolean isUnderlined() {
        return isUnderlined;
    }

    public boolean isStrikethrough() {
        return isStrikethrough;
    }

    public boolean isObfuscated() {
        return isObfuscated;
    }

    public Color getColor() {
        return color;
    }

    public String getInsertion() {
        return insertion;
    }

    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    public Text setBold(boolean isBold) {
        this.isBold = isBold;
        return this;
    }

    public Text setItalic(boolean isItalic) {
        this.isItalic = isItalic;
        return this;
    }

    public Text setUnderlined(boolean isUnderlined) {
        this.isUnderlined = isUnderlined;
        return this;
    }

    public Text setStrikethrough(boolean isStrikethrough) {
        this.isStrikethrough = isStrikethrough;
        return this;
    }

    public Text setObfuscated(boolean isObfuscated) {
        this.isObfuscated = isObfuscated;
        return this;
    }

    public Text setColor(Color color) {
        this.color = color;
        return this;
    }

    public Text setInsertion(String insertion) {
        this.insertion = insertion;
        return this;
    }

    public Text setClickEvent(ClickEvent event) {
        this.clickEvent = event;
        return this;
    }

    public Text setHoverEvent(HoverEvent event) {
        this.hoverEvent = event;
        return this;
    }

    public boolean hasEvent() {
        return clickEvent != null || hoverEvent != null;
    }
}
