#!/usr/bin/env bash
#build
cp /c/project/poc/52-paco/01-finatra2/target/scala-2.11/fitman_2.11-1.0.jar .

docker build -t finatra/app .
#run
docker-compose up -d

#docker run -d --name db -p 8091-8094:8091-8094 -p 11210:11210 couchbase
