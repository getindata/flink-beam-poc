#!/bin/bash

kubectl create -f jobmanager-deployment.yml
kubectl create -f taskmanager-deployment.yml
kubectl create -f jobmanager-service.yml
