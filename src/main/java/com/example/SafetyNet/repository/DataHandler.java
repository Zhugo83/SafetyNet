package com.example.SafetyNet.repository;

import com.example.SafetyNet.model.Data;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.io.*;

@Repository
public class DataHandler {

    private final Data data;

    private static String FILE_PATH = "data.json";

    public DataHandler() throws IOException {
        String temp = getFromResource(FILE_PATH);
        this.data = JsonIterator.deserialize(temp, Data.class);
    }

    public DataHandler(String filepath) throws IOException {

        String temp = getFromResource(filepath);
        //InputStream json = new ClassPathResource("data.json").getInputStream();
        //String temp = IOUtils.toString(json, StandardCharsets.UTF_8);
        this.data = JsonIterator.deserialize(temp, Data.class);
    }

    private String getFromResource(String s) throws IOException {
        InputStream is = new ClassPathResource(s).getInputStream();
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }

    public Data getData() {
        return data;
    }
    
    public void save() throws IOException {
        String json = JsonStream.serialize(data);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.json"), StandardCharsets.UTF_8));
        writer.write(json);
    }
}
