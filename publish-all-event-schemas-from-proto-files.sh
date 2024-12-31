#!/bin/bash

# subdirectories to search for Gradle projects
subdirs=("comment" "reaction")

# Execute jibDockerBuild task for each subdirectory concurrently
for dir in "${subdirs[@]}"; do
  (cd "$dir" && ./gradlew :event-schema:publishToMavenLocal) &
done

# Wait for all jibDockerBuild tasks to complete
wait
