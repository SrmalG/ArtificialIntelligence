package com.example.demo.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SparkService {

    Dataset<Row> preprocessingDriver(MultipartFile file) throws IOException;
}
