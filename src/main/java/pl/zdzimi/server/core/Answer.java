package pl.zdzimi.server.core;

public abstract class Answer {

    public static Answer answer(Request request) {
        String url = request.getUrl();
        String[] split = url.split("/");
        String name = split[split.length - 2];
        String fileType = split[split.length - 1];

        switch (fileType) {
            case "html" : return new HtmlAnswer(name, fileType);
            case "js" : return new JsAnswer(name, fileType);
            default: return new NoSuchFile();
        }
    }

    public byte[] getAnswer() {
        if (!fileExists()) {
            createFile();
        }
        return this.getAnswerBytes();
    }

    abstract boolean fileExists();
    abstract void createFile();
    abstract byte[] getAnswerBytes();

}
