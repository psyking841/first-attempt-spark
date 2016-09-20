package com.mycompany.spark

import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{FlatSpec, Matchers}

class MyAPISpec extends FlatSpec with Matchers {
  val sqlContext = new SQLContext(
    new SparkContext(new SparkConf()
      .setMaster("local[1]")
      .setAppName(getClass.getName)
      .set("spark.ui.enabled", "false")
      .set("spark.sql.shuffle.partitions", "3")
      .set("spark.driver.allowMultipleContexts", "true")
    )
  )

  //val itemQueries = new {{cookiecutter.main_class}}Flow(sqlContext)

  // Reading example data for testing
  val testdf = sqlContext.read.json(getClass.getResource("/data.json").toString())  // Should be changed
  val testStr = MyAPI.getColumnAsJson(testdf, "name")
  //println(testStr)
  //val res = itemQueries.transform(testStr)
  
  it should "work" in {
    // my unit test
    val expected = "[{\"name\":\"Alice\"},{\"name\":\"Edward\"},{\"name\":\"Dave\"},{\"name\":\"Cale\"},{\"name\":\"Bob\"}]"

    assert(expected == testStr)

  }
}