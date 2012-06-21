Just a simple helper I'm using to test my clients.  It simply parses
the request and dumps it as an YAML object. The code is a little
hackish but it works.

Sample request:

[http://displaymyrequest.herokuapp.com/some/path?first_name=Alex&email=not+available](http://displaymyrequest.herokuapp.com/some/path?first_name=Alex&email=not+available)

Sample response (notice the YAML format):

```yaml
URI: /some/path
Method: GET
Query: first_name=Alex&email=not+available
Remote-Addr: 10.125.47.117
Remote-Host: 10.125.47.117
Remote-Port: 51488
GeoIP-Location:
  city: Bucharest
  country: Romania
  latitude: 44.433304
  country-code: RO
  longitude: 26.100006
Real-IP: 86.127.153.169
Headers:
  Host: displaymyrequest.herokuapp.com
  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
  X-Forwarded-For: 86.127.153.169
  Accept-Language: en-us,en;q=0.5
  X-Forwarded-Port: '80'
  Connection: keep-alive
  User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:13.0) Gecko/20100101 Firefox/13.0.1
  X-Forwarded-Proto: http
  Cache-Control: max-age=0
  Accept-Encoding: gzip, deflate
Parameters:
  email:
  - not available
  first_name:
  - Alex
```

To deploy on Heroku first create a new app:

```bash
heroku apps:create <app-name> -s cedar
```

Then deploy:

```bash
git push heroku master
```
