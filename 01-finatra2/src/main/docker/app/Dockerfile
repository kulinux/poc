FROM java:8

WORKDIR /root

EXPOSE 8888
COPY *.jar /root


ENV CLASSPATH "/root/fitman_2.11-1.0.jar"
CMD ["java", "-Dcom.couchbase.connectTimeout=50000", "com.pako.fitman.FitmanApp"]

