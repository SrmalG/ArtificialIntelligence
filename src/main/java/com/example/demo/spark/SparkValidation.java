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

    /**
     * Duplicated driver method
     * @param dataset - dataset to validate
     * @param columnsToValidate - Columns which are going to be validated
     * @return the dataset validated
     */
    public static Dataset<Row> erasingDupsDriver(Dataset<Row> dataset, List<Column> columnsToValidate) {
        return dupsEraser(
                dataset,
                JavaConverters.collectionAsScalaIterableConverter(columnsToValidate)
                        .asScala()
                        .toSeq(), columnsToValidate.get(0));
    }

    /**
     * Method in charge of performing the duplicates validation
     * @param dataset - The dataset
     * @param seq - Seq of Columns
     * @param orderByColumn - The order by Column
     * @return The dataset validated
     */
    public static Dataset<Row> dupsEraser(Dataset<Row> dataset, Seq<Column> seq, Column orderByColumn) {
        final Dataset<Row> currentDataset = dataset
                .withColumn("duplicated", count("*").over(Window.partitionBy(seq)))
                .withColumn("isDuplicated",
                        when(functions.col("duplicated").$greater(1),
                                row_number().over(Window.partitionBy(seq).orderBy(orderByColumn))).otherwise(lit(-1)))
                .withColumn("errorTmp", when(col("isDuplicated").$greater(1),lit("-ERROR_ID1")))
                .withColumn("errorDesc", when(col("errorTmp").equalTo("-ERROR_ID1"), lit("-The row is duplicated")
                ).otherwise(lit("")));

        return currentDataset
                .drop("isDuplicated","duplicated");
    }

    /**
     * Method in charge of Filtering by date, not taking into account outdated rows
     * @param dataset - The dataset
     * @param column - Columns where to perform the validation
     * @param daysToCheck - The days to check
     * @return The validated dataset.
     */
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

    /**
     * Method in charge of finding null or empty values in thw row
     * @param dataset - The datasaet which is going to be validated
     * @return - The validated dataset
     */
    public static Dataset<Row> filterByEmptyFields(Dataset<Row> dataset) {
        //MOCKED DATA
        final List<String> columns = List.of(
                "Index",
                "Customer Id",
                "First Name",
                "Last Name",
                "Company",
                "City",
                "Country",
                "Phone 1",
                "Phone 2",
                "Email",
                "Subscription Date",
                "Website"
        );

        final Column nullOrEmptyCondition = columns.stream()
                .map(colName -> col(colName).isNull().or(trim(col(colName)).equalTo("")))
                .reduce(Column::or)
                .orElse(lit(false));

        Dataset<Row> withErrors = dataset.withColumn("errorTmp",
                when(nullOrEmptyCondition,
                        when(col("errorTmp").isNotNull(), concat(col("errorTmp"), lit("-ERROR_ID3")))
                                .otherwise(lit("-ERROR_ID3")))
                        .otherwise(col("errorTmp"))
        );
        withErrors = withErrors.withColumn("errorDesc",
                when(col("errorDesc").isNotNull().and(col("errorTmp").contains("-ERROR_ID3")),
                        concat(col("errorDesc"), lit("-There are null or empty fields")))
                        .when(col("errorTmp").contains("-ERROR_ID3"), lit("There are null or empty fields"))
                        .otherwise(col("errorDesc"))
        );
        return withErrors;
    }
}
