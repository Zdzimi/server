package pl.zdzimi.server.core;

import java.io.File;
import java.io.IOException;

public class JsAnswer extends Answer {

    private String name;
    private String fileType;

    public JsAnswer(String name, String fileType) {
        this.name = name;
        this.fileType = fileType;
    }

    @Override
    boolean fileExists() {
        return new File(name + "." + fileType).exists();
    }

    @Override
    void createFile() {
        File file = new File(name + "." + fileType);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    byte[] getAnswerBytes() {
        File file = new File(name + "." + fileType);
        String s = file.toString();
        return s.getBytes();
    }
}
