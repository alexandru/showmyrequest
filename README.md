Just a simple helper I'm using to test my clients.
It simply parses the request and dumps it as an YAML object.

Visit this URL:

[http://displaymyrequest.herokuapp.com/some/path?first_name=Alex&email=not+available](http://displaymyrequest.herokuapp.com/some/path?first_name=Alex&email=not+available)

Sample response (notice the YAML format):

```yaml
URI: /some/path
Method: GET
Query: first_name=Alex&email=not+available
Remote-Addr: 10.218.13.171
Remote-Host: 10.218.13.171
Remote-Port: 52486
Headers:
  Host: displaymyrequest.herokuapp.com
  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
  X-Forwarded-For: 86.127.153.169
  Accept-Language: en-us,en;q=0.5
  X-Forwarded-Port: '80'
  Connection: keep-alive
  User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:13.0) Gecko/20100101 Firefox/13.0.1
  X-Forwarded-Proto: http
  Accept-Encoding: gzip, deflate
Parameters:
  email:
  - not available
  first_name:
  - Alex
```

Some use-cases:

- in case your client accesses a website that does geo-IP filtering,
  you may want to play with the X-Forwarded-For header to simulate a
  request from another city
- you may want to see if the Referer or Cookie or other headers were
  passed correctly
- etc...
 
