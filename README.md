# radio-paradise

HTTP API to get the current and last playing songs of [Radio Paradise](http://radioparadise.com)

Try it at: [radioparadise.heroku.com](http://radioparadise.heroku.com)

```bash
$ curl radioparadise.heroku.com
{
  now-playing: [...]
}

$ curl radioparadise.heroku.com?callback=foo
foo({
  now-playing: 'foobar'
})
```

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server
