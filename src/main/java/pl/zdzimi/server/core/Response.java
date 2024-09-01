package pl.zdzimi.server.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public abstract class Response {

  protected String fileName;
  protected String fileType;
  protected List<String> contentType;

  public Response(String fileName, String fileType) {
    this.fileName = fileName;
    this.fileType = fileType;
  }

  public static Response getResponse(String fileName, String fileType) {
    switch (fileType) {
      case "html":
        return new HtmlResponse(fileName, fileType);
      case "js":
        return new JsResponse(fileName, fileType);
      default:
        throw new IllegalArgumentException();
    }
  }

  public String getResponse() {
    if (!fileExists()) {
      createFile();
    }
    return getResponseAsString();
  }

  protected abstract void createFile();

  public String getResponseAsString() {
    File file = new File(this.fileName + "." + this.fileType);
    Scanner scanner = null;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    while(scanner.hasNextLine()) {
      sb.append(scanner.nextLine());
    }
    scanner.close();
    return sb.toString();
  }

  private boolean fileExists() {
    return new File(this.fileName + "." + this.fileType).exists();
  }

  public List<String> getContentType() {
    return this.contentType;
  }
}
