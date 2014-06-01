#/bin/bash

selfpath=$(cd "$(dirname "$0")"; pwd)
tops=$(cd "$(dirname "$selfpath/../../..")"; pwd)
bin=$tops/script/auto_deploy.py
lastarg="-n"
if [ -z $1 ]; then
    lastarg=""
fi

$bin -t $tops -s /opt/stock-front -w icloud-stock-front -p icloud-front/icloud-stock-front -r dev --host www.buuyuu.com -u root -a eiyb4gxk $lastarg
