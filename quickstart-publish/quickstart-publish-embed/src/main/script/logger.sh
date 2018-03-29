#!/bin/sh


## 脚本所在目录
CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

## 应用根目录
APP_HOME=$( cd $CURRENT_DIR && cd ..;pwd)

#------------------------------------------------------------------

export APP_HOME

SCRIPT="rivi"
export SCRIPT

LOG_LEVEL="$1"
export LEVEL

LOG_NAME="$2"
export LOG_NAME

bash $APP_HOME/bin/setENV

