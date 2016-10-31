FROM java:8

WORKDIR /root

EXPOSE 8888
COPY *.jar /root


CMD ["java", "-Dcom.couchbase.connectTimeout=50000", "com.pako.fitman.FitmanApp"]

