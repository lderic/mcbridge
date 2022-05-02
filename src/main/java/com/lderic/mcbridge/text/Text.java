package com.lderic.mcbridge.text;

public class Text {
    public static final Text NULL = new Text("null");
    public static final Text NEW_LINE = new Text("\\n");
    private final String value;
    private boolean isBold = false;
    private boolean isItalic = false;
    private boolean isUnderlined = false;
    private boolean isStrikethrough = false;
    private boolean isObfuscated = false;
    private boolean isInverse = false;
    private Color color;
    private String insertion;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    public Text(String value) {
        this.value = value;
    }

    public static Text of(String value) {
        return new Text(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isBold() {
        return isBold;
    }

    public Text setBold(boolean isBold) {
        this.isBold = isBold;
        return this;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public Text setItalic(boolean isItalic) {
        this.isItalic = isItalic;
        return this;
    }

    public boolean isUnderlined() {
        return isUnderlined;
    }

    public Text setUnderlined(boolean isUnderlined) {
        this.isUnderlined = isUnderlined;
        return this;
    }

    public boolean isStrikethrough() {
        return isStrikethrough;
    }

    public Text setStrikethrough(boolean isStrikethrough) {
        this.isStrikethrough = isStrikethrough;
        return this;
    }

    public boolean isObfuscated() {
        return isObfuscated;
    }

    public Text setObfuscated(boolean isObfuscated) {
        this.isObfuscated = isObfuscated;
        return this;
    }

    public boolean isInverse() {
        return isInverse;
    }

    public Text setInverse(boolean isInverse) {
        this.isInverse = isInverse;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Text setColor(Color color) {
        this.color = color;
        return this;
    }

    public boolean hasColor() {
        return color != null;
    }

    public String getInsertion() {
        return insertion;
    }

    public Text setInsertion(String insertion) {
        this.insertion = insertion;
        return this;
    }

    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    public Text setClickEvent(ClickEvent event) {
        this.clickEvent = event;
        return this;
    }

    public HoverEvent getHoverEvent() {
        return hoverEvent;
    }

    public Text setHoverEvent(HoverEvent event) {
        this.hoverEvent = event;
        return this;
    }

    public boolean hasEvent() {
        return clickEvent != null || hoverEvent != null;
    }
}
