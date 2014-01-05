#!/bin/sh

set -e

git clone https://github.com/wjtk/fluent-exception-rule.git
cd fluent-exception-rule
git checkout 0.2.0
mvn install
cd ..