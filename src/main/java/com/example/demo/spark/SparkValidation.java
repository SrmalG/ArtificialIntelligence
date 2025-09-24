package com.example.demo.spark;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.functions;
import scala.collection.JavaConverters;
import scala.collection.Seq;

import java.util.List;
import java.util.stream.Collectors;

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
                .withColumn("errorTmp", when(col("isDuplicated").$greater$eq(2),lit("ERROR_1")));
        return currentDataset.drop("isDuplicated","duplicated");
    }


    //TODO: REUTILIZAR COLUMNA ERROR_TEMP

    public static Dataset<Row> filterDataIsNull(Dataset<Row> dataset, List<String> fieldsToValidate) {

        List<Column> filters = fieldsToValidate.stream()
                .map(columnName -> col(columnName).isNotNull()
                        .and(col(columnName).notEqual("")))
                .collect(Collectors.toList());

        Column combinedFilter = filters.stream().reduce(Column::and)
                .orElse(col(String.valueOf(Boolean.TRUE)));

        Dataset<Row> result = dataset.filter(combinedFilter);

        return result;
    }
}
