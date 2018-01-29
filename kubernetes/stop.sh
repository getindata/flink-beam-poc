#!/bin/bash

kubectl delete -f jobmanager-deployment.yml
kubectl delete -f taskmanager-deployment.yml
kubectl delete -f jobmanager-service.yml
