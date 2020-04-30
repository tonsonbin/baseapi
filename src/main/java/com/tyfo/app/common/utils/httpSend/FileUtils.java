package com.tyfo.app.common.utils.httpSend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	/**
	 * 文件流保存成文件
	 * @param file
	 * @param ins
	 * @return
	 * @throws IOException
	 */
	public static void createFileByIO(File file,InputStream ins) throws IOException {
		
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.flush();
		os.close();
		ins.close();
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		createFileByIO(new File("E:\\temp\\FFH2A8c7c2.jpg"),new FileInputStream(new File("F:\\picture\\1.jpg")));
	}
}
