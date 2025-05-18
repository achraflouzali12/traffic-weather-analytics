package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object IngestionJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("TrafficWeatherIngestion")
      .getOrCreate()

  }
}