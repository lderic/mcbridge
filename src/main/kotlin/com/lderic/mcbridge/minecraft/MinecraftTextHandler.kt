package com.lderic.mcbridge.minecraft

import com.lderic.mcbridge.text.ClickEvent
import com.lderic.mcbridge.text.HoverEvent
import com.lderic.mcbridge.text.Text
import com.lderic.mcbridge.text.TextChain

internal fun Text.handleMinecraftText(allowEvent: Boolean = true): String {
    val sb = StringBuilder()
    sb.append("{")
    sb.append("\"text\":\"").append(this.toString()).append("\"")
    if (this.isBold) {
        sb.append(",\"bold\":true")
    }
    if (this.isItalic) {
        sb.append(",\"italic\":true")
    }
    if (this.isUnderlined) {
        sb.append(",\"underlined\":true")
    }
    if (this.isStrikethrough) {
        sb.append(",\"strikethrough\":true")
    }
    if (this.isObfuscated) {
        sb.append(",\"obfuscated\":true")
    }
    if (this.color != null) {
        sb.append(",\"color\":\"").append(this.color).append("\"")
    }
    if (this.insertion != null) {
        sb.append(",\"insertion\":\"").append(this.insertion).append("\"")
    }
    if (allowEvent) {
        if (this.clickEvent != null) {
            sb.append(",\"clickEvent\":").append(this.clickEvent.handleMinecraftText())
        }
        if (this.hoverEvent != null) {
            sb.append(",\"hoverEvent\":").append(this.hoverEvent.handleMinecraftText())
        }
    }
    sb.append("}")
    return sb.toString()
}

internal fun TextChain.handleMinecraftText(allowEvent: Boolean = true): String {
    val sb = StringBuilder()
    sb.append("[")
    for (text in this) {
        sb.append(text.handleMinecraftText(allowEvent)).append(",")
    }
    sb.deleteCharAt(sb.length - 1)
    sb.append("]")
    return sb.toString()
}

internal fun HoverEvent.handleMinecraftText(): String {
    val sb = StringBuilder()
    sb.append("{")
    sb.append("\"action\":\"").append(this.action.actionName).append("\"")
    if (this.chain != null) {
        sb.append(",\"contents\":").append(this.chain.handleMinecraftText(false)).append("\"")
    }
    sb.append("}")
    return sb.toString()
}

internal fun ClickEvent.handleMinecraftText(): String {
    val sb = StringBuilder()
    sb.append("{")
    sb.append("\"action\":\"").append(this.action.actionName).append("\"")
    if (this.value != null) {
        sb.append(",\"value\":").append(this.value).append("\"")
    }
    sb.append("}")
    return sb.toString()
}