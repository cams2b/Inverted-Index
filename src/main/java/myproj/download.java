package myproj;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.nio.file.Paths;

public class download {
  public static void downloadObject(
      String projectId, String bucketName, String objectName, String destFilePath) {


    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    Blob blob = storage.get(BlobId.of(bucketName, objectName));
    blob.downloadTo(Paths.get(destFilePath));

    System.out.println(
        "Downloaded object "
            + objectName
            + " from bucket name "
            + bucketName
            + " to "
            + destFilePath);
  }
  
  public static void main(String[] args) {
	  
	    // The ID of your GCP project
	    String projectId = "invindex";

	    // The ID of your GCS bucket
	    String bucketName = "dataproc-staging-us-central1-1037520934349-n3dl9izu";

	    // The ID of your GCS object
	    String objectName = "index_out/part-r-00000";

	    // The path to which the file should be downloaded
	  	String path = System.getProperty("user.dir");
	  	path = path + "/topIn.txt";
	  	System.out.println("Working directory = " + path);
	  	downloadObject(projectId, bucketName, objectName, path);
	  	
	  
  }
  
  
  
  
  
  
  
  
}