package com.lderic.mcbridge.minecraft.text;

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

    public Text setBold(boolean bold) {
        this.isBold = bold;
        return this;
    }

    public Text setItalic(boolean italic) {
        this.isItalic = italic;
        return this;
    }

    public Text setUnderlined(boolean underlined) {
        this.isUnderlined = underlined;
        return this;
    }

    public Text setStrikethrough(boolean strikethrough) {
        this.isStrikethrough = strikethrough;
        return this;
    }

    public Text setObfuscated(boolean obfuscated) {
        this.isObfuscated = obfuscated;
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

    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"text\":\"").append(value).append("\"");
        if (isBold) {
            sb.append(",\"bold\":true");
        }
        if (isItalic) {
            sb.append(",\"italic\":true");
        }
        if (isUnderlined) {
            sb.append(",\"underlined\":true");
        }
        if (isStrikethrough) {
            sb.append(",\"strikethrough\":true");
        }
        if (isObfuscated) {
            sb.append(",\"obfuscated\":true");
        }
        if (color != null) {
            sb.append(",\"color\":\"").append(color.getValue()).append("\"");
        }
        if (insertion != null) {
            sb.append(",\"insertion\":\"").append(insertion).append("\"");
        }
        if (clickEvent != null) {
            sb.append(",\"clickEvent\":").append(clickEvent.toJsonString());
        }
        if (hoverEvent != null) {
            sb.append(",\"hoverEvent\":").append(hoverEvent.toJsonString());
        }
        sb.append("}");
        return sb.toString();
    }
}
