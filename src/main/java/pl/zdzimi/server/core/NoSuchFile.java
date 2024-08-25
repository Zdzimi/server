package pl.zdzimi.server.core;

public class NoSuchFile extends Answer {

    @Override
    boolean fileExists() {
        return false;
    }

    @Override
    void createFile() {
    }

    @Override
    byte[] getAnswerBytes() {
        return "Bad request".getBytes();
    }

}
