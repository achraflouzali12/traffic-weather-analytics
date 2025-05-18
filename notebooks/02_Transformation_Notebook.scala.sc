// Databricks notebook source
val bronzePath = "/mnt/datalake/bronze/traffic_weather"
val silverPath = "/mnt/datalake/silver/traffic_weather"

val df = spark.read.format("delta").load(bronzePath)

val transformedDF = df
  .withColumn("timestamp", to_timestamp(col("timestamp")))
  .withColumn("day", date_format(col("timestamp"), "yyyy-MM-dd"))
  .filter(col("traffic_density") > 0)

transformedDF.write.format("delta").mode("overwrite").save(silverPath)