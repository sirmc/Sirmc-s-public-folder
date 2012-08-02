#!/usr/bin/python

# Script to create html interface which controls output on raspberry pi 
#
#

from wsgiref.simple_server import make_server
from cgi import parse_qs, escape
import wiringpi  
from time import sleep  

html = """
<div style='width: 100%; text-align: center'>
<a style='display: block; width: 100%; text-decoration: none; background: green; color: black; font-size: 200pt;' href='?mode=on'>On</a>
<br />
<a style='display: block; width: 100%; text-decoration: none;background: red; color: black; font-size: 200pt;' href='?mode=off'>Off</a>
<br />
</div>
"""

def application(environ, start_response):

    # Returns a dictionary containing lists as values.
    d = parse_qs(environ['QUERY_STRING'])

    # Get first value (if more than one)
    mode = d.get('mode', [''])[0]

    # Escape input 
    mode = escape(mode)

    # Setup raspberry pi output
    io = wiringpi.GPIO(wiringpi.GPIO.WPI_MODE_SYS)  
    io.pinMode(18,io.OUTPUT)  # Setup pin 18 (GPIO1)
    if mode == 'on':
        io.digitalWrite(18,io.HIGH)  # Turn on light
    elif mode == 'off':
        io.digitalWrite(18,io.LOW)  # Turn on light

    # Set output to html string
    response_body = html
    status = '200 OK'

    # Some header magic, create response
    response_headers = [('Content-Type', 'text/html'), ('Content-Length', str(len(response_body)))]
    start_response(status, response_headers)

    return [response_body]

# Make it serve on all addresses
# can be changed to e.g. 192.168.0.10 of you want to restric to local network
httpd = make_server('0.0.0.0', 8051, application)
httpd.serve_forever()
