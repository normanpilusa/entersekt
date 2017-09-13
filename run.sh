#!/bin/bash

xhost +
echo "Building Docker Image"
docker build -t entersekt:test .

echo "Starting Docker Container"
docker run --rm --name webapp entersekt:test
