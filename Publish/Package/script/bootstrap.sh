
## 追加所有Jar
CLAZZ_PATH=""
append(){
    CLAZZ_PATH=$CLAZZ_PATH":"$1
}
LIBS="$APP_HOME$_DPDC_LIBS/*"
for _FILE in $LIBS; do
    append $_FILE
done
LIBS="$APP_HOME$_CORE_LIBS/*"
for _FILE in $LIBS; do
    append $_FILE
done
LIBS=""

## 执行程序
java -cp $CLAZZ_PATH $OPTS $ENTRY $DATE_TIME $ARGS &

