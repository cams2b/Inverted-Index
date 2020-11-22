package myproj;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.io.File;
import java.util.zip.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class upload {
  public static void uploadObject(String projectId, String bucketName, String objectName, String filePath) throws IOException {
    // The ID of your GCP project
    //String projectId = "invindex";

    // The ID of your GCS bucket
    //String bucketName = "your-unique-bucket-name";

    // The ID of your GCS object
    // String objectName = "your-object-name";

    // The path to your file to upload
    // String filePath = "path/to/your/file"

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    
    BlobId blobId = BlobId.of(bucketName, objectName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));

    System.out.println(
        "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
  }
  
  public static String[] chooseFile() {
	  String val = "test";
	  JFileChooser chooser = new JFileChooser();
	  //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	  chooser.setMultiSelectionEnabled(true);
	  int value = chooser.showOpenDialog(null);
	  String[] retArr = new String[3];
	  
	  if (value == JFileChooser.APPROVE_OPTION) {
		  //return chooser.getSelectedFile().getAbsolutePath();
		  File[] files = chooser.getSelectedFiles();
		  retArr = new String[files.length];
		  // iterate over files
		  for(int i = 0; i < files.length; i++) {
			  
			  retArr[i] = files[i].getAbsolutePath();
			  
		  }
	  }
	  
	  
	  return retArr;
	  
  }
  public static void deleteObject(String projectId, String bucketName, String objectName) {
	    // The ID of your GCP project
	    // String projectId = "your-project-id";

	    // The ID of your GCS bucket
	    // String bucketName = "your-unique-bucket-name";

	    // The ID of your GCS object
	    // String objectName = "your-object-name";

	    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
	    storage.delete(bucketName, objectName);

	    System.out.println("Object " + objectName + " was deleted from " + bucketName);
	  }
  
 
  

  

  
  public static void main(String[] args) throws IOException {
	  
	    // The ID of your GCP project
	    String projectId = "invindex";

	    // The ID of your GCS bucket
	    String bucketName = "dataproc-staging-us-central1-1037520934349-n3dl9izu";

	    // The ID of your GCS object
	    String objectName = "index_out/part-r-00000";
	    String obj1 = "index_out/_SUCCESS";
	    String obj2 = "index_out";

	    // The path to your file to upload
	    String filePath = "/Users/cameronbeeche/Desktop/Fall/cs1660/project/input/I.txt";
	  
		System.out.println(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
		deleteObject(projectId, bucketName, objectName);
		deleteObject(projectId, bucketName, obj1);
		deleteObject(projectId, bucketName, obj2);
		
		

		//uploadObject(projectId, bucketName, objectName, filePath);
  }
  
  
}




