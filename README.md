# ciclo

Ciclo will be, someday, a job execution manager...
but right now it's just my approximation to clojure on web development.
I followed [Jarrod C Taylor Compojure series][] up to part 3, and took a different approach, since this was just a spike without DB.

[Jarrod C Taylor Compojure series]: http://www.jarrodctaylor.com/posts/Compojure-Address-Book-Part-1/ "Jarrod C Taylor Compojure series"

## Roadmap

My personal roadmap on this project includes:
- Add tests to all current operations
- Delete operation should not be a POST, but a DELETE
- Make `next-id` still work when there's no job
- Make the job listing to keep the order
- Add `state` field
- Make `last-success` and `last-fail` determine job `state`
- Add more fields
- Add ClojureScript to the mix
- Improve delete and update job operations
- Sooner or later this will need a database, with its own setup, for persistence
- Put these on a Docker setup
- Make CSS to be generated with Sass

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[Leiningen]: https://github.com/technomancy/leiningen "Leiningen"

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyleft 2016 Ignasi Fosch
