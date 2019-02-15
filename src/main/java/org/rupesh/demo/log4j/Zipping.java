package org.rupesh.demo.log4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipping {

	private static final String haveToZIP = System.getProperty("user.dir")
			+ "\\testReporter\\RegressionSuite_Mobile_Samsung_Note9_Build_1235";
	private static List<String> filesListInHaveToZIP;
	private static String outputZIPFile = haveToZIP + ".zip";

	public static void main(String[] args) throws IOException {
		filesListInHaveToZIP = new ArrayList<String>();
		if (new File(haveToZIP).isDirectory()) {
			File[] filesList = new File(haveToZIP).listFiles();
			for (File f : filesList) {
				generateFilesList(f);
			}

		}

		File zipFile = new File(outputZIPFile);
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
		FileInputStream fileInputStream = new FileInputStream(haveToZIP);
		for (String s : filesListInHaveToZIP) {
			zipOutputStream.putNextEntry(new ZipEntry(s));
		}

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fileInputStream.read(bytes)) >= 0) {
			zipOutputStream.write(bytes, 0, length);
		}

		zipOutputStream.closeEntry();
		fileInputStream.close();
		zipOutputStream.close();
	}

	public static void generateFilesList(File node) {
		if (node.isFile()) {
			filesListInHaveToZIP.add(node.getAbsolutePath());
		}
		if (node.isDirectory()) {
			File[] tempFilesList = node.listFiles();
			for (File tempFile : tempFilesList) {
				generateFilesList(tempFile);
			}

		}

	}

}
