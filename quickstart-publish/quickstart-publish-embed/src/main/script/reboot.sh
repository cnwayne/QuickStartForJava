#!/bin/sh

## 脚本所在目录
CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

## 应用根目录
APP_HOME=$( cd $CURRENT_DIR && cd ..;pwd)

#------------------------------------------------------------------

export APP_HOME

SCRIPT="isqi"
export SCRIPT

bash $APP_HOME/bin/setENV


