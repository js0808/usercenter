#!/bin/bash

runOnBackground=${runOnBackground:-true}
APP_NAME="bjca-app-usercenter"

#取当前目录
BASE_PATH=`cd "$(dirname "$0")"; pwd`
HOME_PATH="$HOME"


# 使用到的基本变量
APP_BASE_PATH=`cd "$(dirname "$0")"; cd ..; pwd`
PIDFILEDIR="$HOME_PATH/.back/${APP_NAME}"
APP_PIDFILE="$PIDFILEDIR/pid"

mkdir -p $PIDFILEDIR

#取外部数据
XMX_VAR=1G
#去掉结尾的G
XMX_VAR=${XMX_VAR%%G}
#数值*3/4,m
XMX_V=`expr $XMX_VAR \* 3072 / 4`
#数值*3/4,m
XMS_V=`expr $XMX_VAR \* 3072 / 4`
#数值*3/8,m
XMN_V=`expr $XMX_VAR \* 3072 / 8`

#设置java运行参数
DEFAULT_JAVA_OPTS=" -server -Xmx${XMX_V}m -Xms${XMS_V}m -Xmn${XMN_V}m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "

#定义变量:
APP_PATH=${APP_PATH:-`dirname "$BASE_PATH"`}
CLASS_PATH=${CLASS_PATH:-$APP_PATH/config:$APP_PATH/lib/*}
JAVA_OPTS=${JAVA_OPTS:-$DEFAULT_JAVA_OPTS}
DEFAULT_JAR=$(find $APP_PATH/lib/ -name *.jar)
LOG_PATH="${HOME_PATH}/logs/${APP_NAME}"
if [ ! -d "$LOG_PATH" ]; then
	mkdir -p $LOG_PATH
fi

JAVA_OPTS="${JAVA_OPTS} -Dlogpath.base=${LOG_PATH}"
JAVA_OPTS="${JAVA_OPTS} ${EXTRA_OPTS}"

SERVER_ACCESSABLE_IP=$SERVER_ACCESSABLE_IP
if [ -z "$SERVER_ACCESSABLE_IP" ]; then
	SERVER_ACCESSABLE_IP=$(ifconfig eth0 |grep 'inet add'|awk -F ":" '{print $2}'|awk '{print $1}')
fi
SERVER_ACCESSABLE_PORT=$SERVER_ACCESSABLE_PORT
if [ -z "$SERVER_ACCESSABLE_PORT" ]; then
	SERVER_ACCESSABLE_PORT=10000
fi
export SERVER_ACCESSABLE_IP=${SERVER_ACCESSABLE_IP}
export SERVER_ACCESSABLE_PORT=${SERVER_ACCESSABLE_PORT}
export SERVER_ENVIROMENT=${PROJECTENV}

echo "=========env========"
echo "APP_PATH=$APP_PATH"
echo "BASE_PATH=$BASE_PATH"
echo "DEFAULT_JAR=$DEFAULT_JAR"
echo "CLASS_PATH=$CLASS_PATH"
echo "LOG_PATH=LOG_PATH"
echo "JAVA_OPTS=JAVA_OPTS"
echo "=========env========"

cd $APP_PATH
ulimit -HSn ${ULIMIT}

#
#
# 获取当前服务的pid
# 1、优先使用 APP_PIDFILE变量， 如果里面存储的pid不存在，使用步骤2
# 2、使用$APP_NAME进行查找，如果查找到多条，使用全路径查找, 否则直接用appName查询
# 3、如果查不到返回值为非0
#
getPID(){

  [ -f "$APP_PIDFILE" ] && appId=$(cat $APP_PIDFILE)
  if [ ! -z "$appId" ];then
    ps -p $appId 1>/dev/null
    [ "$?" = "1" ] && appId=""
  fi
  if [ -z "$appId" ]; then
     count=$(pgrep -f $APP_NAME |grep java|wc -l)
     [ "$count" == "0" ] &&  return 1
    # if [ "$count" -gt "1" ]; then
       appId=$(ps aux|grep java| grep "$APP_BASE_PATH/"|grep -v grep|awk '{print $2}')
     # else
      # appId=$(pgrep -f "\b$APP_NAME\b"   -U $UID)
     # fi
  fi
  [ -z "$appId" ] && return 2
  echo $appId
  return 0
}

# 修改获取pid的方式
exist(){
    cid=$(getPID)
    if [ "$?" = "0" -a ! -z "$cid" ]; then
      return 0
    else
      return 1
    fi
}

start(){
		if exist; then
				echo "$APP_NAME is already running."
				exit 1
		else
	    		cd $APP_PATH
			if [ "$runOnBackground" = "true" ]; then
					nohup java $JAVA_OPTS -cp $CLASS_PATH -jar $DEFAULT_JAR $APP_NAME > $LOG_PATH/console.log 2>&1 &
				# record pid to $APP_PIDFILE
				RET=$?; APPID=$!
			  echo $APPID > ${APP_PIDFILE}
        echo "INFO: $APP_NAME is started. pidfile created : ${APP_ID} (pid  $APPID) ; operate status $RET"
			else
				java $JAVA_OPTS -cp $CLASS_PATH -jar $DEFAULT_JAR $APP_NAME
 
			fi
		fi
}

stop(){
		runningPID=$(getPID)
		if [ "$?" = "0" -a  "$runningPID" ]; then
				echo "$APP_NAME pid: $runningPID"
        count=0
        kwait=5
        echo "$APP_NAME is stopping, please wait..."
        kill -15 $runningPID
					until [[ `ps --pid $runningPID 2> /dev/null | grep -c $runningPID 2> /dev/null` -eq '0' ]] || [ $count -gt $kwait ]
		        do
		            sleep 1
		            let count=$count+1;
		        done

	        if [ $count -gt $kwait ]; then
	            kill -9 $runningPID
	        fi
        clear
        echo "$APP_NAME is stopped."
    else
    		echo "$APP_NAME has not been started."
    fi
}

check(){
   if exist; then
   	 echo "$APP_NAME is alive."
   	 exit 0
   else
   	 echo "$APP_NAME is dead."
   	 exit -1
   fi
}

restart(){
        stop
        start
}

case "$1" in

start)
        start
;;
stop)
        stop
;;
restart)
        restart
;;
check)
        check
;;
*)
        echo "available operations: [start|stop|restart|check]"
        exit 1
;;
esac
