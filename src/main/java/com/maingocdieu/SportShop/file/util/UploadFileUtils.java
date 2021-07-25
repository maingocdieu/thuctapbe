package com.maingocdieu.SportShop.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;


public class UploadFileUtils {
	public static final String root = "D:/";
	public static void writeOrUpdate(byte[] bytes, String path) {
      File file = new File(StringUtils.substringBeforeLast(root + path, "/"));
      if ( !file.exists() ) {
          file.mkdir();
      }
      try (FileOutputStream fileOutputStream = new FileOutputStream(new File(root + path))) {
          fileOutputStream.write(bytes);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
