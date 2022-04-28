package com.lderic.mcbridge.minecraft.text;

public class ClickEvent {
    public enum Action {
        OPEN_URL("open_url"),
        RUN_COMMAND("run_command"),
        SUGGEST_COMMAND("suggest_command"),
        CHANGE_PAGE("change_page"),
        COPY_TO_CLIPBOARD("copy_to_clipboard");

        private final String actionName;

        Action(String actionName) {
            this.actionName = actionName;
        }


        public String getActionName() {
            return actionName;
        }
    }
    private final Action action;
    private final String value;

    public ClickEvent(Action action, String value) {
        this.action = action;
        this.value = value;
    }

    public String toJsonString() {
        return "{\"action\":\"" + action.getActionName() + "\",\"value\":\"" + value + "\"}";
    }
}