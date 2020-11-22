package myproj;
/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * https://github.com/googleapis/java-dataproc
 */

// [START dataproc_submit_hadoop_fs_job]



import com.google.api.client.util.Lists;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dataproc.v1.HadoopJob;
import com.google.cloud.dataproc.v1.Job;
import com.google.auth.Credentials;
import com.google.cloud.dataproc.v1.JobControllerClient;
import com.google.cloud.dataproc.v1.JobControllerSettings;
import com.google.cloud.dataproc.v1.HadoopJob;
import com.google.cloud.dataproc.v1.JobMetadata;
import com.google.cloud.dataproc.v1.JobPlacement;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.lang.*;
import myproj.upload;

public class CallTopN {

  public static ArrayList<String> stringToList(String s) {
    return new ArrayList<>(Arrays.asList(s.split(" ")));
  }

  public static void submitHadoopFsJob(String argument) throws IOException, InterruptedException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "invindex";
    String region = "us-central1";
    String clusterName = "phaedrus";
    String hadoopFsQuery = argument;
    submitHadoopFsJob(projectId, region, clusterName, hadoopFsQuery);
  }

  public static void submitHadoopFsJob(
      String projectId, String region, String clusterName, String hadoopFsQuery)
      throws IOException, InterruptedException {
    String myEndpoint = String.format("%s-dataproc.googleapis.com:443", region);
    
        

    // Configure the settings for the job controller client.
    JobControllerSettings jobControllerSettings =
        JobControllerSettings.newBuilder().setEndpoint(myEndpoint).build();

    // Create a job controller client with the configured settings. Using a try-with-resources
    // closes the client,
    // but this can also be done manually with the .close() method.
    try (JobControllerClient jobControllerClient =
        JobControllerClient.create(jobControllerSettings)) {

      // Configure cluster placement for the job.
      JobPlacement jobPlacement = JobPlacement.newBuilder().setClusterName(clusterName).build();

      // Configure Hadoop job settings. The HadoopFS query is set here.
      HadoopJob hadoopJob =
          HadoopJob.newBuilder()
              .setMainJarFileUri("file:///home/cabeeche/TopN.jar")  
              .addAllArgs(stringToList(hadoopFsQuery))
              .build();

      Job job = Job.newBuilder().setPlacement(jobPlacement).setHadoopJob(hadoopJob).build();
      
      

      // Submit an asynchronous request to execute the job.
      OperationFuture<Job, JobMetadata> submitJobAsOperationAsyncRequest =
          jobControllerClient.submitJobAsOperationAsync(projectId, region, job);

      Job response = submitJobAsOperationAsyncRequest.get();

      // Print output from Google Cloud Storage.
      Matcher matches =
          Pattern.compile("gs://(.*?)/(.*)").matcher(response.getDriverOutputResourceUri());
      matches.matches();

      Storage storage = StorageOptions.getDefaultInstance().getService();
      Blob blob = storage.get(matches.group(1), String.format("%s.000000000", matches.group(2)));

      System.out.println(
          String.format("Job finished successfully: %s", new String(blob.getContent())));

    } catch (ExecutionException e) {
      // If the job does not complete successfully, print the error message.
      System.err.println(String.format("submitHadoopFSJob: %s ", e.getMessage()));
    }
  } // END OF SUBMIT HADOOP JOB
  
  public static void callTop(int n) throws IOException, InterruptedException {
	  
	String topn = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//top_out ";
	topn = topn + Integer.toString(n);
	submitHadoopFsJob(topn);
	
	// download.
    String projectId = "invindex";

    // The ID of your GCS bucket
    String bucketName = "dataproc-staging-us-central1-1037520934349-n3dl9izu";

    // The ID of your GCS object
    String objectName = "top_out/part-r-00000";

    // The path to which the file should be downloaded
  	String path = System.getProperty("user.dir");
  	path = path + "/top.txt";
  	System.out.println("Working directory = " + path);
  	download.downloadObject(projectId, bucketName, objectName, path);
  	
  	
  	
  	// delete 
  	
    // The ID of your GCS object
    String obj0 = "top_out/part-r-00000";
    String obj1 = "top_out/_SUCCESS";
    String obj2 = "top_out/";

    // The path to your file to upload
    //String filePath = "/Users/cameronbeeche/Desktop/Fall/cs1660/project/input/I.txt";
  
	System.out.println(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
	upload.deleteObject(projectId, bucketName, obj0);
	upload.deleteObject(projectId, bucketName, obj1);
	upload.deleteObject(projectId, bucketName, obj2);
  }
  
  
  public static String[][] tableInput(int n) throws IOException {
	  	String path = System.getProperty("user.dir");
	  	path = path + "/top.txt";
	  	
	  	BufferedReader br = new BufferedReader(new FileReader(path));
	  	
	  	String line = br.readLine();
	  	String[][] data = new String[n][2];
	  	
	  	int counter = 0;
	  	while(line != null) {
	  		//System.out.println(line);
	  		String curr[] = line.split("\t");
	  		//System.out.println(curr[0]);
	  		data[counter][0] = curr[0];
	  		data[counter][1] = curr[2];
	  		counter++;
	  		line = br.readLine();
	  	}
	  	
	  	return data;
	  
	  
	  
  }
  
  public static void main(String[] args) throws IOException {
	  
	  tableInput(4);
  }
  
  
  

  
  
  
  
}