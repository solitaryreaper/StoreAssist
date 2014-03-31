name := "StoreAssistBackend"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.19",  
  "com.twilio.sdk" % "twilio-java-sdk" % "3.4.2"  
)     

play.Project.playJavaSettings
