

import AssemblyKeys._ // put this at the top of the file

assemblySettings

test in assembly := {}

jarName in assembly := "all.jar"
mainClass in assembly := Some("com.pako.fitman.FitmanApp")

