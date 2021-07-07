package pack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller{
	ObservableList choices = FXCollections.observableArrayList();
	
	@FXML
	public AnchorPane myPane;
	@FXML
	public Button submit;
	@FXML
	public TextField pdfSuff1;
	@FXML
	public TextField pdfSuff2;
	@FXML 
	public TextField pdfSuff3;
	@FXML 
	public TextField pdfSuff4;
	@FXML
	public TextField pdfSuff5;
	@FXML
	public ChoiceBox<?> dropAA;
	@FXML
	public ChoiceBox<?> dropBA;
	@FXML
	public ChoiceBox<?> dropCA;
	@FXML
	public ChoiceBox<?> dropDA;
	@FXML
	public ChoiceBox<?> dropEA;
	@FXML
	public TextField browseText;
	@FXML
	public TextField finalSuffix;
	
	public String[] inputs = new String[4];
	public static boolean flag = false;
	public String userFilepath = "";
	public String finalFilepath = "";
	public Runner m = new Runner();
	public ObservableList<FilePack> filePackage = FXCollections.observableArrayList();
	public ArrayList<String> IDList = new ArrayList<String>();
	public ArrayList<List<FilePack>> matrix = new ArrayList<List<FilePack>>();
	HashMap<String, List<FilePack>> myHash = new HashMap<String, List<FilePack>>();
	File[] listOfFiles;
	public ArrayList<List<FilePack>> sortedMatrix = new ArrayList<List<FilePack>>();
	public ArrayList<FilePack> finalPacks = new ArrayList<FilePack>();
	
	public void browseHit(ActionEvent event) {
		final DirectoryChooser myChooser = new DirectoryChooser();
		Stage stage = (Stage) myPane.getScene().getWindow();
		File folder = myChooser.showDialog(null);
		userFilepath = folder.getAbsolutePath();
		System.out.println("[+]File loaded: "+ userFilepath);
		browseText.setText("Loaded Folder Successfully!");
		listOfFiles = Initializer.FileArrayLoad(userFilepath);
		try {
			fillPackage();}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(0);
			}

	}
	// "main" function 
	public void suffSubmit(ActionEvent event)throws IOException{
		setFields();

		fillIDList();
		generateHash();
		fillMatrix();
		sortMatrix();
		try {
		generateFinalPacks();}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			generateFinalPacks();}
			catch(Exception e) {
				e.printStackTrace();
			}
		
		AlertBox.display("PDF PAL", "File Exported: "+finalFilepath);
	}
	
	//Assigns to input array the strings typed in by the user
	private void setFields() throws IOException{
		inputs[0] = pdfSuff1.getText();
		inputs[1] = pdfSuff2.getText();
		inputs[2] = pdfSuff3.getText();
		inputs[3] = pdfSuff4.getText();
		System.out.println("[+] Input received...");
		for (String word: inputs) {
			System.out.println(word);
		}
	}
	
	public void fillPackage() throws Exception{
		for (File file:listOfFiles) {
			FilePack pack = new FilePack(file);
			filePackage.add(pack);
			if (pack.getSuffix().equals("error100")){
				Stage stage = (Stage)submit.getScene().getWindow();
				stage.close();
				AlertBox.display("PDF PAL", "ERROR 100: IMPROPER FILE FORMAT, PLEASE FORMAT FILENAMES CORRECTLY");
				throw  new Exception("Bad format.");
			}
		}
	}
	
	@SuppressWarnings("exports")
	public void fillIDList() {
		for (FilePack pack:filePackage) {
			if (IDList.contains(pack.getID())) {}
			else {
				IDList.add(pack.getID());
			}
		}
	}
	public void generateHash() {
		
		for (FilePack myPack:filePackage) {
			String key = myPack.getID();
			if (myHash.containsKey(key)) {
				List<FilePack> list = myHash.get(key);
				list.add(myPack);
			}else {
				List<FilePack> list = new ArrayList<FilePack>();
				list.add(myPack);
				myHash.put(key, list);
			}
		}
	}
	
	public void fillMatrix() {
		System.out.println("[+] Matrix loading...");
		for (String ID: IDList) {
			matrix.add(myHash.get(ID));
		}
		System.out.println("[+] Matrix loaded: "+ matrix);
	}
	
	//sorts each filepack list
	public void sortMatrix() {
		System.out.println("[+] Matrix sorting...");
		for (List<FilePack> myList: matrix) {
			Collections.sort(myList, new FileSorter(pdfSuff1.getText(), pdfSuff2.getText(), pdfSuff3.getText(), pdfSuff4.getText(),pdfSuff5.getText()));;
			//add sortedList to sortedMatrix `
			sortedMatrix.add(myList);
			}
	System.out.println("[+] Matrix Sorted!");
	}
	
	public void generateFinalPacks() {
		final DirectoryChooser myChooser = new DirectoryChooser();
		Stage stage = (Stage) myPane.getScene().getWindow();
		File folder = myChooser.showDialog(null);
		finalFilepath = folder.getAbsolutePath();
		for (List<FilePack> myList: sortedMatrix) {
			try {getReport(myList);}
			catch(Exception e) {
				System.out.println("ERROR: FILE NOT FOUND");
			}	
			
		}
	}
	
	public void getReport(List<FilePack> theList) throws IOException {
		PDFMergerUtility pdfMerger = new PDFMergerUtility();
		pdfMerger.setDestinationFileName(finalFilepath+"/" + theList.get(0).getPrefix() + finalSuffix.getText()+".pdf");
		for (FilePack pack: theList) {
			try {pdfMerger.addSource(pack.getTheFile());}
			catch(Exception e) {
				System.out.println("ERROR: FILE NOT FOUND");
			}
		}
		pdfMerger.mergeDocuments(null);

	}
	
}