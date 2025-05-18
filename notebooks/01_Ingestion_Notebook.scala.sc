// Databricks notebook source
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

val eventHubsConnection = "<CONN_STRING>"

val schema = new StructType()
  .add("timestamp", StringType)
  .add("location", StringType)
  .add("traffic_density", DoubleType)
  .add("weather_condition", StringType)
  .add("temperature", DoubleType)

val df = spark.readStream
  .format("eventhubs")
  .option("eventhubs.connectionString", eventHubsConnection)
  .load()
  .selectExpr("CAST(body AS STRING) as jsonPayload")
  .withColumn("data", from_json(col("jsonPayload"), schema))
  .select("data.*")

df.writeStream
  .format("delta")
  .option("checkpointLocation", "/mnt/datalake/checkpoints/notebook_ingestion")
  .start("/mnt/datalake/bronze/traffic_weather")