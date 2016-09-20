name := "MyAPI"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "1.6.2" % "provided",
"org.apache.spark" %% "spark-streaming" % "1.6.2" % "provided",
"org.apache.spark" %% "spark-mllib" % "1.6.2" % "provided",
"org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

EclipseKeys.withSource := true