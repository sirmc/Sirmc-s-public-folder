#!/usr/bin/python

# Script to restart wifi if internet is down

iface = 'wlan0'

import urllib2
import syslog
from subprocess import call
try:
    urllib2.urlopen("http://google.com", timeout = 2)
except urllib2.URLError, e:
    syslog.syslog(syslog.LOG_ERR, 'Network down, restarting interface: ' + iface)
    call(["ifdown", iface])
    call(["ifup", iface])
