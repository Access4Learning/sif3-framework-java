#!/bin/sh

SERVICE_NAME=$1

echo "Shut Down Agent: $SERVICE_NAME"
SERVICE_PID=`ps --width 10000 -o pid,command | grep "systemic.sif3.demo.rest.consumer.StudentConsumerService $SERVICE_NAME" | awk '{print $1}'`

kill -HUP $SERVICE_PID

echo RIP $SERVICE_PID - $SERVICE_NAME 


