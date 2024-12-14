#!/bin/bash

docker exec -it mockstagram-kafka /opt/kafka/bin/kafka-topics.sh --create --topic test --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1