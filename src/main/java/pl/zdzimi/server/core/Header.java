package pl.zdzimi.server.core;

public class Header {

    private final String key;
    private final String value;

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Header: %s: %s", key, value);
    }

}
