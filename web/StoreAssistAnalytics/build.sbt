name := "StoreAssistAnalytics"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.19"  
)     

play.Project.playJavaSettings
