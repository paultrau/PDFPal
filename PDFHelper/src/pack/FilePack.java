package pack;
import java.io.File;

public class FilePack {
	private String ID;
	private String suffix;
	private String prefix;
	private File theFile;
	private String filename;
	
	public FilePack(File myFile) {
		this.theFile = myFile;
		this.filename = myFile.getName();
		this.ID = filename.substring(0,7);
		this.suffix = generator(filename,1);
		this.prefix = generator(filename,2);
	}
	public String generator(String theFilename,int mode) {
		String final1 = "";
		String final2 = "";
		int numScores = 0;
		for (int i = 0; i < theFilename.length()-1;i++) {
			if ((String.valueOf(theFilename.charAt(i))).equals("_")){
				numScores++;
			}
			if (numScores==3) {
				final1 = theFilename.substring(0,i+1);
				final2 = theFilename.substring(i+1,theFilename.length()-4);
				break;
			}
		}
		if (numScores!=3) {
			return "error100";
		}
		if (mode==2) {
		return final1;
		}else {return final2;}	
	}


	public String toString() {
		return "Package: " + this.filename;
	}
	//getters setters
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public File getTheFile() {
		return theFile;
	}

	public void setTheFile(File theFile) {
		this.theFile = theFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
