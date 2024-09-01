package pl.zdzimi.server.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class JsResponse extends Response {

  public JsResponse(String fileName, String fileType) {
    super(fileName, fileType);
    this.contentType = Collections.singletonList("text/javascript");
  }

  @Override
  protected void createFile() {
    File file = new File(this.fileName + "." + this.fileType);
    if (!file.exists()) {
      try {
        file.createNewFile();
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write("alert(\"Hello! I am an alert!!!\");");
        printWriter.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

}
