#!/bin/bash

# Gradle 프로젝트들이 있는 서브 디렉터리들
subdirs=("content" "user")  # 쉼표 뒤 공백 제거

# 각 서브 디렉터리에서 jibDockerBuild 작업을 병렬로 실행
for dir in "${subdirs[@]}"; do
  (cd "$dir" && ./gradlew jibDockerBuild) &
done

# 모든 병렬 작업이 끝날 때까지 기다리기
wait
