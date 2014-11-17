#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/auto_deploy_notomcat.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /opt/fetchstock -w icloud-stock-connector -p icloud-stock/icloud-stock-connector -r test --host www.buuyuu.com -u root -a eiyb4gxk -bin stock.sh $lastarg
