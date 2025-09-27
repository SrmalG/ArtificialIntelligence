package com.example.demo.spark;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.functions;
import scala.collection.JavaConverters;
import scala.collection.Seq;

import java.util.List;


import static org.apache.spark.sql.functions.*;

public class SparkValidation {

    private SparkValidation() {}

    public static Dataset<Row> erasingDupsDriver(Dataset<Row> dataset, List<Column> columnsToValidate) {
        return dupsEraser(
                dataset,
                JavaConverters.collectionAsScalaIterableConverter(columnsToValidate)
                        .asScala()
                        .toSeq(), columnsToValidate.get(0));
    }

    public static Dataset<Row> dupsEraser(Dataset<Row> dataset, Seq<Column> seq, Column orderByColumn) {
        final Dataset<Row> currentDataset = dataset
                .withColumn("duplicated", count("*").over(Window.partitionBy(seq)))
                .withColumn("isDuplicated",
                        when(functions.col("duplicated").$greater(1),
                                row_number().over(Window.partitionBy(seq).orderBy(orderByColumn))).otherwise(lit(-1)))
                .withColumn("errorTmp", when(col("isDuplicated").$greater(1),lit("-ERROR_ID1")))
                .withColumn("errorDesc", when(col("errorTmp").equalTo("-ERROR_ID1"), lit("-The row is duplicated")
                ).otherwise(lit("")));
        return currentDataset.drop("isDuplicated","duplicated");
    }

    public static Dataset<Row> nullsDataValidate(Dataset<Row> datasetToValidate, List<String> colsToValidate){
        final List<Column> listOfColumns = SparkUtils.listColGenerator(colsToValidate);
        return null;
    }

    public static Dataset<Row> filterBydDate(Dataset<Row> dataset, String column, Integer daysToCheck) {
        return dataset.withColumn("errorTmp",
                when(date_sub(current_date(), daysToCheck).gt(col(column)),
                        when(col("errorTmp").isNotNull(), concat(col("errorTmp"), lit("-ERROR_ID2")))
                                .otherwise(lit("-ERROR_ID2"))
                ).otherwise(col("errorTmp")))
                .withColumn("errorDesc", when(col("errorDesc").isNotNull()
                        .and(col("errorTmp").contains("ERROR_ID2")),
                        concat(col("errorDesc"), lit("-The row is outdated"))));

    }



//    public static Dataset<Row> filterDataIsNull(Dataset<Row> dataset, List<String> fieldsToValidate) {
//        List<Column> filters = fieldsToValidate.stream()
//                .map(columnName -> col(columnName).isNotNull()
//                        .and(col(columnName).notEqual("")))
//                .collect(Collectors.toList());
//
//        Column combinedFilter = filters.stream().reduce(Column::and)
//                .orElse(col(String.valueOf(Boolean.TRUE)));
//
//        Dataset<Row> result = dataset.filter(combinedFilter);
//        result.show(false);
//        return result;
//    }
}
