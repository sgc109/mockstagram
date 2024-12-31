#!/bin/bash

# subdirectories to search for Gradle projects
subdirs=("content" "comment" "reaction" "user" "notification")

# Execute jibDockerBuild task for each subdirectory concurrently
for dir in "${subdirs[@]}"; do
  (cd "$dir" && ./gradlew jibDockerBuild) &
done

# Wait for all jibDockerBuild tasks to complete
wait
