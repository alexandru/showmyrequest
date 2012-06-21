Just a simple helper I'm using to test my clients.
It simply parses the request and dumps it as an YAML object.

Visit:

[http://displaymyrequest.herokuapp.com/hello?param=world&another=Alex](http://displaymyrequest.herokuapp.com/hello?param=world&another=Alex)

Some use-cases:

- in case your client accesses a website that does geo-IP filtering, you may want to play with the X-Forwarded-For header
- you may want to see if the Referer or Cookie headers were passed correctly
- etc...
 
