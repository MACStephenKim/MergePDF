package neomerger;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//import mergePDF.PDDocument;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

public class MergeProcessor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println(args.length);
		
		String sourcePath = args.length > 0 ? args[0] : "\\\\gunflint\\global\\shared\\bing\\neo\\";
		String destPath = args.length > 1 ? args[1] : "\\\\gunflint\\global\\shared\\bing\\neo\\merged\\";
		
		//String sourcePath = "\\\\gunflint\\global\\shared\\bing\\neo\\";
		//String destPath = "\\\\gunflint\\global\\shared\\bing\\neo\\merged\\";
		
		mergeFiles(sourcePath, destPath);
	    

	}
	
	public static void mergeFiles(String a, String b) throws IOException{
		File folder = new File(a);
		List<NeoPage> NPList = new ArrayList<NeoPage>(); 
		listFiles(folder, NPList);
		
		// sort list by order
		Collections.sort(NPList,  new Comparator<NeoPage>() {
			public int compare (NeoPage o1, NeoPage o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
		
		System.out.println(">>>>> Sorted fileNames");
		
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		
		for(NeoPage page : NPList) {
			//System.out.println(page.getFileName());
			File file = new File(a+page.getFileName());
		    PDDocument doc = PDDocument.load(file);

		    //adding the source files
		    PDFmerger.addSource(file);
		    
		    doc.close();
		}
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
		Date date = new Date();
		
	    //Setting the destination file
	    PDFmerger.setDestinationFileName(b + "merged " + dateFormat.format(date)+".pdf");
	    
	    File f = new File(b + "merged " + dateFormat.format(date)+".pdf");
	    if(f.exists()){
	    	f.delete();
	    	PDFmerger.mergeDocuments(null);
	    }
	    else{
	    	PDFmerger.mergeDocuments(null);
	    }
		// now merge files
		//String mergedPath = mergePdfs(NPList);
	    
	    System.out.println("Documents merged");
	}
	
	
	public static void listFiles(final File folder, List<NeoPage> nPList) {
		
		System.out.println(">>>>> Unsorted fileNames");
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()){
				//listFiles(fileEntry);
			} else {
				//System.out.println(fileEntry.getName());
				NeoPage np = new NeoPage(fileEntry.getName());
				nPList.add(np);				
			}
		}
		
	}
	

}
