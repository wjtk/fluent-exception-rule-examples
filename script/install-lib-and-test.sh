#!/bin/sh

# exit on first error
set -e

cd ../fluent-exception-rule
mvn clean install
cd ../fluent-exception-rule-example
mvn test
