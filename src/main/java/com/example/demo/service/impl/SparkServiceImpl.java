package com.example.demo.service.impl;

import com.example.demo.service.SparkService;
import com.example.demo.spark.SparkUtils;
import com.example.demo.spark.SparkValidation;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

import static org.apache.spark.sql.functions.col;

@Service
public class SparkServiceImpl implements SparkService {

    @Override
    public Dataset<Row> preprocessingDriver(final MultipartFile file, List<String> columns, String dateColumn) throws IOException {
        final SparkSession spark = SparkUtils.getSparkSession();
        final Dataset<Row> dataset = SparkUtils.obtainDatasetFromInput(file, spark);
        final Dataset<Row> dataset1 = SparkValidation.erasingDupsDriver(dataset, SparkUtils.listColGenerator(columns));
        final Dataset<Row> dataset2 = SparkValidation.filterBydDate(dataset1,dateColumn, 1000);
        dataset2.filter(col("errorTmp").isNotNull()).show(200,false);
        return dataset;
    }

}
