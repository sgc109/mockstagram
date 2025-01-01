#!/bin/bash

docker compose -f ./monitoring/signoz/docker/clickhouse-setup/docker-compose-minimal.yaml up -d
