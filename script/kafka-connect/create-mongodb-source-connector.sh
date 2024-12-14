#!/bin/bash

curl -i -X POST -H "Content-Type: application/json" \
     -H "Accept:application/json" \
     --data @../../init/kafka-connect/mongo-source-connector.json \
     http://localhost:8083/connectors/
