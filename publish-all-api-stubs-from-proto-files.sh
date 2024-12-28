#!/bin/bash

# subdirectories to search for Gradle projects
subdirs=("content" "comment" "reaction" "user")

# Execute jibDockerBuild task for each subdirectory concurrently
for dir in "${subdirs[@]}"; do
  (cd "$dir" && ./gradlew :api:publishToMavenLocal) &
done

# Wait for all jibDockerBuild tasks to complete
wait
