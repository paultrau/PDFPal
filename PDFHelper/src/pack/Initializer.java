package pack;

import java.io.File;
import java.io.FileInputStream;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
public class Initializer {
	public Initializer() {
		
	}
	
	public static File[] FileArrayLoad(String path) {
        System.out.println("[+] Loading Files...");
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    }}
		System.out.println("[+] Files Loaded!");
		return listOfFiles;
	}
		
	
	public static void FileLoad(String path) throws Exception{
		File theFile = new File(path);
		FileInputStream fis = new FileInputStream(theFile);
		PDDocument document = PDDocument.load(fis);

		System.out.println("Number of Pages: " +document.getPages().getCount());

		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String docText = pdfTextStripper.getText(document);

		System.out.println(docText);

		document.close();
		fis.close();
	}
}
