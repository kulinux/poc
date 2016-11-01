FROM java:8

WORKDIR /root

EXPOSE 8888
COPY lib_managed /root/lib_managed
COPY *.jar /root/lib_managed
COPY runApp.sh /root

RUN apt-get update && apt-get install -y dos2unix


RUN dos2unix /root/runApp.sh && apt-get --purge remove -y dos2unix && rm -rf /var/lib/apt/lists/*


CMD bash -C '/root/runApp.sh';'bash'

