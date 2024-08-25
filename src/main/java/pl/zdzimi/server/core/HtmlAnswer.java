package pl.zdzimi.server.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HtmlAnswer extends Answer {

  private String name;
  private String fileType;

  public HtmlAnswer(String name, String fileType) {
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
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write("<!DOCTYPE html>"
            + "\n<html lang=\"pl\""
            + "xmlns=\"http://www.w3.org/1999/xhtml\">"
            + "\n<head>\n<meta charset=\"UTF-8\">"
            + "\n<title>test</title>"
            + "\n</head>"
            + "\n<body>"
            + "\n<h1>Test</h1>"
            + "\n</body>"
            + "\n</html>");
        printWriter.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  @Override
  byte[] getAnswerBytes() {
    File file = new File(name + "." + fileType);

    Scanner scanner = null;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    StringBuilder sb = new StringBuilder();
    while (scanner.hasNextLine()) {
      sb.append(scanner.nextLine());
    }
    return sb.toString().getBytes();
  }

}
