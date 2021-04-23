#!/bin/bash

docker rm -f $(docker ps -qa) || true
docker rmi devopscloudweek2/devopsapp:develop || true
