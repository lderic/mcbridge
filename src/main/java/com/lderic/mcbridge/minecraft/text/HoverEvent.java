package com.lderic.mcbridge.minecraft.text;

public class HoverEvent {
    public enum Action {
        SHOW_TEXT("show_text"),
        SHOW_ITEM("show_item"),
        SHOW_ENTITY("show_entity");

        private final String actionName;

        Action(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return actionName;
        }
    }

    private final Action action;
    private final TextChain chain;

    public HoverEvent(Action action, Text... texts) {
        this(action, TextChain.of(texts));
    }

    /**
     * Remember that text in this event can't have any event.
     * @param action type of event
     * @param chain text chain
     */
    public HoverEvent(Action action, TextChain chain) {
        this.action = action;
        this.chain = chain;
        check();
    }

    public void check() {
        chain.forEach(text -> {
            if (text.hasEvent()) {
                throw new IllegalArgumentException("Text in hover event can't have any event.");
            }
        });
    }

    public String toJsonString() {
        return "{\"action\":\"" + action.getActionName() + "\",\"contents\":" + chain.toJsonString() + "}";
    }
}


