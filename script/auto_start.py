#!/usr/bin/python

import os
import sys
import ssh
import argparse

parser = argparse.ArgumentParser(description='Process some integers.')
parser.add_argument("-t", "--tops",
                  dest="tops", required=True,
                  help="absolute path of tz(tops) derectory")
parser.add_argument("-s", "--tomcat",
                  dest="tomcat", required=True,
                  help="absolute path of target tomcat server, in remote pc")
parser.add_argument("-w", "--webapp",
                  dest="webapp", required=True,
                  help="webapp name of target web project, exclude filename suffix (.war).")
parser.add_argument("-p", "--project",
                  dest="project", required=True,
                  help="relative path of gradle project file, relative to tz(tops) directory")
parser.add_argument("-n", "--no-package",
                  dest="package", action='store_false',
                  help="if specified, target project won't be packaged.")
parser.add_argument("-r", "--profile",
                  dest="profile",
                  help="profile for gradle building")
parser.add_argument("--host",
                  dest="host", required=True,
                  help="address of remote host")
parser.add_argument("-o", "--port",
                  dest="port", type=int, default=22,
                  help="port of remote ssh service")
parser.add_argument("-u", "--username",
                  dest="username", required=True,
                  help="username of remote host")
parser.add_argument("-a", "--password",
                  dest="password", required=True,
                  help="password of remote user")


args = parser.parse_args()

dict = {
        'tops':args.tops,
        'tomcat':args.tomcat,
        'webapp':args.webapp,
        'project':args.project 
       }

client = ssh.SSHClient()
client.set_missing_host_key_policy(ssh.AutoAddPolicy())
client.connect(args.host, port=args.port, username=args.username, password=args.password)
sftp = client.open_sftp()

def coloration(s, color):
    return "\033[0;" + color + "m" + str(s) + "\033[0m"

def green(s):
    return coloration(s, "32")

def purple(s):
    return coloration(s, "35")

def localCmd(cmd):
    print green('==>') + ' executing "' + purple(cmd) + '"'
    os.system(cmd)

def sshExec(cmd):
    print green('==>') + ' executing "' + purple(cmd) + '"'
    return client.exec_command(cmd)

def sshCmd(cmd):
    stdin, stdout, stderr = sshExec(cmd)
    line = stdout.readline()
    while len(line) > 0:
        print line,
        line = stdout.readline()
    if len(stderr.read()) > 0:
        sys.exit('execute cmd failed.')

def sshRun(cmd):
    stdin, stdout, stderr = sshExec(cmd)
    if len(stderr.read()) > 0:
        sys.exit('execute cmd failed.')
    return stdout.read()


def transfer(target, dest):
    print green('==>') + ' scp ' + purple(target) + ' --> ' + purple(dest)
    sftp.put(target, dest)

def package():
    cmd = 'gradle clean war -p %(tops)s -b %(tops)s/%(project)s/build.gradle' % dict
    if args.profile:
        cmd += ' -Pprofile=' + args.profile
    localCmd(cmd)

def upload():
    transfer('%(tops)s/%(project)s/target/libs/%(webapp)s.war' % dict, '/tmp/%(webapp)s.war' % dict)

def tomcat():
    pid = sshRun("ps aux|grep %(tomcat)s/bin|grep -v 'grep'|awk '{print $2}'" % dict).strip()
    if len(pid) != 0:
        sshCmd("kill -9 " + pid)
    #sshCmd('rm -rf %(tomcat)s/webapps/%(webapp)s*' % dict)
    #sshCmd('rm -rf %(tomcat)s/work/*' % dict)
    #sshCmd('cp /tmp/%(webapp)s.war %(tomcat)s/webapps/' % dict)
    sshCmd('%(tomcat)s/bin/startup.sh' % dict)
    print green('==> starting tomcat ...')
    stdin, stdout, stderr = sshExec('tail -f %(tomcat)s/logs/catalina.out' % dict)

    loop_counter = 0
    line = stdout.readline()
    while len(line) > 0:
        print line,
	loop_counter = loop_counter + 1
        if loop_counter > 10 and 'INFO: Server startup in ' in line:
            break
        line = stdout.readline()

#if (args.package):
#    package()
#upload()
tomcat()


