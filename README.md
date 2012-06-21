Just a simple helper I'm using to test my clients.  It simply parses
the request and dumps it as an YAML object. The code is a little
hackish but it works.

Sample request:

[http://showmyrequest.herokuapp.com/?first_name=Alex&email=not+available](http://showmyrequest.herokuapp.com/?first_name=Alex&email=not+available)

Sample response (notice the YAML format):

```yaml
Method: GET
Remote-Addr: 109.101.232.97
Remote-Port: 47370
Real-IP: 109.101.242.97
GeoIP-Location:
  city: Bucharest
  country: Romania
  latitude: 44.433304
  country-code: RO
  longitude: 26.100006
Headers:
  Host: showmyrequest.herokuapp.com
  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
  X-Forwarded-For: 109.101.232.97
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

To deploy on Heroku first create a new app:

```bash
heroku apps:create <app-name> -s cedar
```

Then deploy:

```bash
git push heroku master
```
