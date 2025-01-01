#!/bin/bash

./build-all-gradle-projects.sh
docker compose up -d --build
