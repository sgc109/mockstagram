#!/bin/bash

./register-custom-domain.sh
./publish-all-api-stubs-from-proto-files.sh
./publish-all-event-schemas-from-proto-files.sh
./run-all-with-build.sh
