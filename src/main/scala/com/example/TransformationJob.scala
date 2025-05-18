package com.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TransformationJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("TrafficWeatherTransformation")
      .getOrCreate()
  }
}