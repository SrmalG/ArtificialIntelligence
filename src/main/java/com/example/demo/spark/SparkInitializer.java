package com.example.demo.spark;

import org.apache.spark.sql.SparkSession;

public class SparkInitializer {

    private SparkInitializer() {}

    public static SparkSession getSparkSession() {
        final int coresDisponibles = Runtime.getRuntime().availableProcessors();
        final int coresParaSpark = Math.max(1, coresDisponibles / 2);

        return SparkSession.builder()
                .appName("SparkApp")
                .master("local[" + coresParaSpark + "]")
                .getOrCreate();
    }
}
