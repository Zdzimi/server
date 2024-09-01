package pl.zdzimi.server.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class HtmlResponse extends Response {

  public HtmlResponse(String fileName, String fileType) {
    super(fileName, fileType);
    this.contentType = Collections.singletonList("text/html");
  }

  @Override
  protected void createFile() {
    File file = new File(this.fileName + "." + this.fileType);
    if (!file.exists()) {
      try {
        file.createNewFile();
        PrintWriter printWriter = new PrintWriter(file);

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<script type=\"text/javascript\" src=\"http://localhost:8080/js/js\"></script>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>");
        sb.append("asdoaj f");
        sb.append("</h1>");
        sb.append("</body>");
        sb.append("</html>");

        printWriter.write(sb.toString());
        printWriter.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

}
