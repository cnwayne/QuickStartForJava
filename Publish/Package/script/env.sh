
## JVM参数
OPTS=

## 程序入口
ENTRY="com.wayneleo.quickstart.startup.Bootstrap"

## 依赖Jar
_DPDC_LIBS="/lib"

## 业务Jar
_CORE_LIBS="/core"

##  应用参数
ARGS="--logging.config=$APP_HOME/conf/logback.xml"
ARGS="$ARGS --logging.path=$APP_HOME/logs/"
ARGS="$ARGS --spring.config.location=file:$APP_HOME/conf/application.yml"

#------------------------------------------------------

export OPTS
export ENTRY
export _DPDC_LIBS
export _CORE_LIBS
export ARGS

bash $APP_HOME/bin/bootstrap.sh

