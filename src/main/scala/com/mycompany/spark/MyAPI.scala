package com.mycompany.spark

import org.apache.spark.sql.{DataFrame, SQLContext}
import com.fasterxml.jackson.databind.{ObjectMapper, DeserializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule 

object MyAPI {
  def getColumnAsJson(df : DataFrame, colName : String) : String = {
    val idx = df.columns.indexOf(colName)
    val maps = df.collect.map(r=>Map(df.columns.slice(idx, idx+1).zip(r.toSeq.slice(idx, idx+1)) : _*))
    
    
    val jackson = new ObjectMapper()
    jackson.registerModule(DefaultScalaModule)
    jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  
    val res = jackson.writeValueAsString(maps)
    res
  }
  
  def getColumnAsJson(path : String, colName : String)(implicit sqlCtx : SQLContext) : String = {
     val df = sqlCtx.read.load(path);
     val res = getColumnAsJson(df, colName)
     res
  }
}