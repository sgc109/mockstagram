#!/bin/bash

# Gradle 프로젝트를 찾기 위한 기준이 되는 파일명
gradle_files=("build.gradle" "settings.gradle")

# 서브 디렉터리를 DFS 방식으로 순회하여 Gradle 프로젝트 디렉터리 찾기
find_gradle_dirs() {
  local dir="$1"

  # 현재 디렉터리에서 Gradle 프로젝트 파일이 있는지 확인
  for gradle_file in "${gradle_files[@]}"; do
    if [ -f "$dir/$gradle_file" ]; then
      echo "$dir"
      return
    fi
  done

  # 2-depth까지 DFS 방식으로 순회
  for subdir in "$dir"/*; do
    if [ -d "$subdir" ]; then
      find_gradle_dirs "$subdir"
    fi
  done
}

# 최상위 디렉터리에서 Gradle 프로젝트 찾기
subdirs=()
find_gradle_dirs "." | while read -r dir; do
  subdirs+=("$dir")
done

# Gradle 프로젝트가 있는 서브 디렉터리에서 jibDockerBuild 작업을 병렬로 실행
for dir in "${subdirs[@]}"; do
  (cd "$dir" && ./gradlew jibDockerBuild) &
done

# 모든 병렬 작업이 끝날 때까지 기다리기
wait
