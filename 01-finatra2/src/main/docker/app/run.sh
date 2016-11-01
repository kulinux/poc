#!/usr/bin/env bash
#build

cp -r ../../../../lib_managed .
cp -r ../../../../target/scala-2.11/fitman_2.11-1.0.jar .

docker build -t finatra/app .
#run
docker-compose up -d

