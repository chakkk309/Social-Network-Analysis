package com.spark.graphx.example

import org.apache.spark.graphx.{Edge, Graph, VertexId, VertexRDD}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import Array._
import scala.language.postfixOps

object SparkGraph {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Twitter Analysis").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val user = sc.textFile("src/main/resources/twitter_combined_user.txt")
    val vertices: RDD[(VertexId, String)] = sc.parallelize(
      user.first().split(" ").distinct.map( i => {
      (i.toLong, i + "user")
    }))

    val followRelationship = sc.textFile("src/main/resources/twitter_combined.txt")
    val edges: RDD[Edge[String]] = followRelationship.map(_.split(" ")).map { arr =>
      val followeeId = arr(0).toLong
      val followerId = arr(1).toLong
      Edge(followeeId, followerId, "follow")
    }

    val graph = Graph(vertices, edges)
    val ranks = graph.pageRank(0.0001).vertices
    ranks.take(5).foreach(println(_))
//    graph.vertices.foreach(x => println(s"${x._1}-->${x._2}"))
//    graph.edges.foreach(x => println(s"src:${x.srcId}，dst:${x.dstId},attr:${x.attr}"))

    println("numVertices" + graph.numVertices)
    println("numEdges" + graph.numEdges)
    graph.inDegrees.foreach(println)
    graph.outDegrees.foreach(println)
  }

}