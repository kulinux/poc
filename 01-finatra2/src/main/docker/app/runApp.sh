LIB=lib_managed
export CLASSPATH=$(find "$LIB" -name '*.jar' -printf '%p:' | sed 's/:$//')
java -Dcom.couchbase.connectTimeout=50000 com.pako.fitman.FitmanApp