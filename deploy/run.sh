#!/usr/bin/env bash
#---------------------------
# Version 1.0 Feb 26 2019
# Author: Love Taneja
#---------------------------

echo "Start Search Service"

java -Dlog4j.configurationFile=./config/log4j2.xml -jar searchplans.jar --spring.config.location=./config/application.properties