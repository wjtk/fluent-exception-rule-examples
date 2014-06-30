#!/bin/sh

set -e

git clone https://github.com/wjtk/fluent-exception-rule.git
cd fluent-exception-rule
git checkout master
mvn install
cd ..