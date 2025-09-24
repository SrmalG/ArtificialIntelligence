package com.example.demo.spark;

import org.apache.spark.sql.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class SparkUtils {

    private SparkUtils() {}

    /**
     * Method in charge of retrieving the SparkSession
     * @return The SparkSession
     */
    public static SparkSession getSparkSession() {
        final int availableCores = Runtime.getRuntime().availableProcessors();
        final int Cores4Spark = Math.max(1, availableCores / 2);

        return SparkSession.builder()
                .appName("Preprocessing")
                .master("local[" + Cores4Spark + "]")
                .getOrCreate();
    }

    /**
     * Method in charge of generating a Dataset from the input file
     * @param file - The file
     * @param sparkSession - The sparkSession
     * @return Dataset
     * @throws IOException
     */
    public static Dataset<Row> obtainDatasetFromInput(final MultipartFile file,final SparkSession sparkSession) throws IOException {
        final File tempFile = File.createTempFile("upload-",
                file.getOriginalFilename() != null ? file.getOriginalFilename() : "data");
        final String fileName = file.getOriginalFilename().toLowerCase();
        file.transferTo(tempFile);
        Dataset<Row> dataset;
        if (fileName.endsWith(".csv")) {
            dataset = sparkSession.read()
                    .option("header", "true")
                    .option("inferSchema", "true")
                    .csv(tempFile.getAbsolutePath());

        } else if (fileName.endsWith(".parquet")) {
            dataset = sparkSession.read().parquet(tempFile.getAbsolutePath());
        } else {
            throw new IllegalArgumentException("Formato de archivo no soportado. Solo CSV o Parquet");
        }
        tempFile.deleteOnExit();
        return dataset;
    }


    public static List<Column> listColGenerator(List<String> cols) {
        return cols.stream()
                .map(org.apache.spark.sql.functions::col)
                .collect(Collectors.toList());

    }


}
