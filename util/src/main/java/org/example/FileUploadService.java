package org.example;

import java.io.*;
public class FileUploadService {

    private String url;
    private String username;
    private String password;

    public FileUploadService() throws IOException {
        File file = new File("/Users/historyData/datasource.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
        String[] info = br.readLine().split(",");
        this.url = info[0];
        this.username = info[1];
        this.password = info[2];
    }
}
