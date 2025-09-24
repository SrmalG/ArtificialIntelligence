package com.example.demo.service.impl;

import com.example.demo.service.SparkService;
import com.example.demo.spark.SparkUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SparkServiceImpl implements SparkService {

    @Override
    public Dataset<Row> preprocessingDriver(final MultipartFile file) throws IOException {

        final SparkSession spark = SparkUtils.getSparkSession();

        File tempFile = File.createTempFile("upload-",
                file.getOriginalFilename() != null ? file.getOriginalFilename() : "data");
        file.transferTo(tempFile);

        String fileName = file.getOriginalFilename().toLowerCase();
        Dataset<Row> dataset;

        if (fileName.endsWith(".csv")) {
            dataset = spark.read()
                    .option("header", "true")
                    .option("inferSchema", "true")
                    .csv(tempFile.getAbsolutePath());

        } else if (fileName.endsWith(".parquet")) {
            dataset = spark.read().parquet(tempFile.getAbsolutePath());
        } else {
            throw new IllegalArgumentException("Formato de archivo no soportado. Solo CSV o Parquet");
        }
        tempFile.deleteOnExit();
        dataset.show(false);
        return dataset;
    }

}
