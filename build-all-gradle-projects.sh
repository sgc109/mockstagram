#!/bin/bash

# Gradle 프로젝트들이 있는 서브 디렉터리들
subdirs=("content" "user" "reaction")  # 쉼표 뒤 공백 제거

# Execute jibDockerBuild task for each subdirectory concurrently
for dir in "${subdirs[@]}"; do
  (cd "$dir" && ./gradlew jibDockerBuild) &
done

# Wait for all jibDockerBuild tasks to complete
wait
