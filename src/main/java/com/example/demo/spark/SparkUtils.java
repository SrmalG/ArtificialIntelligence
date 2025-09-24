package com.example.demo.spark;

import org.apache.spark.sql.SparkSession;

public class SparkUtils {

    private SparkUtils() {}

    public static SparkSession getSparkSession() {
        final int availableCores = Runtime.getRuntime().availableProcessors();
        final int Cores4Spark = Math.max(1, availableCores / 2);

        return SparkSession.builder()
                .appName("Preprocessing")
                .master("local[" + Cores4Spark + "]")
                .getOrCreate();
    }
}
