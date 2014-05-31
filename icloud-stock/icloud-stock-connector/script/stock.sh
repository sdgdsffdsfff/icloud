#!/bin/sh

cd /opt/fetchstock
# This script start and stop the index server
basepath=`pwd`
libpath=.:$basepath/conf:$basepath/lib/*
libclass=com.icloud.stock.app.AppMain
 
#(($#!=1))&&{ usage; exit 1; }

start() {
test -d /data/log/stock/fetch/ || mkdir /data/log/stock/fetch/ -p
java -Xms128M -Xmx1024M -classpath $libpath $libclass $1>> /data/log/stock/fetch/history.log 2>&1 &
}
 
stop() {
kill -9 `ps aux | grep $libclass | grep -v grep | awk '{print $2}'`
}
 
usage(){
echo "syntax error!"
echo "Usage:"
echo "$(basename $0) fetchHistory|stop|analysis|all"
}

if [ $# != 1 ];
then
  usage; exit 1;
fi
case $1 in
fetchHistory)    start fetchHistory;;
analysis)    start analysis;;
all) 	start all;;
stop)     stop;;
*)        usage;;
esac
