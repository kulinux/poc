#build
docker build -t finatra/couchbase .
#run
docker-compose up -d

#docker run -d --name db -p 8091-8094:8091-8094 -p 11210:11210 couchbase
