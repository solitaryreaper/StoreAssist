name := "StoreAssistBackend"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.twilio.sdk" % "twilio-java-sdk" % "3.4.1"  
)     

play.Project.playJavaSettings
