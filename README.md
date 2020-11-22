# Inverted-Index
This program generates an inverted index for any .txt files and allows the user to search for terms and generate the top words.

Starting MyIndex
1. Download the MyIndex.jar file from the repository from the following link: https://pitt-my.sharepoint.com/:f:/g/personal/cab347_pitt_edu/Ejv3PiFM4lhKo0uCHcBg5IMB_LoUJPH0H7_L7pp2KxAKIg?e=xTUmSi
2. Unzip the jar file
3. Navigate to your terminal and type the following command: export GOOGLE_APPLICATION_CREDENTIALS=" insert path to JSON key "
4. Enter the following command: echo $GOOGLE_APPLICATION_CREDENTIALS
5. If the previous command returned the path to your key then you are ready to run the program.
6. In the terminal naviage to the folder containing your jar file and type the command: java -jar MyIndex.jar

Running MyIndex

7. Select which index to create
8. After the inverted index is created either search for a term or select top n.
9. When you are done with the code select "delete index" then close the GUI.


Walkthrough Video: https://pitt-my.sharepoint.com/:f:/g/personal/cab347_pitt_edu/Ejv3PiFM4lhKo0uCHcBg5IMB_LoUJPH0H7_L7pp2KxAKIg?e=ZjpTKm


Citations:
This program utilizes code from the google cloud dataproc repository.
- https://github.com/googleapis/java-dataproc
As well as google cloud code for uploading and downloading objects from the cluster.
- https://cloud.google.com/storage/docs/downloading-objects#code-samples

