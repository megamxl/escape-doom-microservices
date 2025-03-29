package at.escapedoom.spring.redis.data.models;


/**
 * The state of an escape-room instance
 */
public enum EscapeRoomState {

    OPEN("open"),

    CLOSED("closed"),

    STARTED("started"),

    FINISHED("finished");

    private String value;

    EscapeRoomState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static EscapeRoomState fromValue(String value) {
        for (EscapeRoomState b : EscapeRoomState.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
