#!/bin/sh


# not used now, we use version of fer from maven central
set -e

git clone https://github.com/wjtk/fluent-exception-rule.git
cd fluent-exception-rule
git checkout master
mvn install
cd ..