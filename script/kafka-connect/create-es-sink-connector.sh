#!/bin/bash

curl -i -X POST -H "Content-Type: application/json" \
     -H "Accept:application/json" \
     --data @../../init/kafka-connect/es-sink-connector.json \
     http://localhost:8083/connectors/
