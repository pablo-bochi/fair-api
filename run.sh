#!/bin/bash

function down_app_container() {
    echo "Stoping latest docker image"
    docker-compose down
}

function delete_latest_docker_image() {
    echo "Deleting latest docker image..."
    docker rmi -f fair-api:latest
}

function build_application() {
    echo "Building app..."
    ./gradlew clean build
}

function build_docker_image() {
    echo "Building docker image..."
    docker image build -t fair-api -f Dockerfile .
}

function up_app_container() {
    echo "Starting latest docker image"
    docker-compose up
}

export DOCKER_BUILDKIT=0
export COMPOSE_DOCKER_CLI_BUILD=0

time (down_app_container)
time (delete_latest_docker_image)
time (build_application)
time (build_docker_image)
time (up_app_container)