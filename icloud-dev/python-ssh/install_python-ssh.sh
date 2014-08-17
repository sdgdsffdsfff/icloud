#!/bin/bash
sudo apt-add-repository -y ppa:chris-lea/python-ssh
sudo apt-get update
sudo apt-get install -y python-libssh2 python-ssh
