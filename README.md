# Duct HTTP client

A simple ClojureScript HTTP client for the [Duct][] framework. It wraps
[cljs-http][] and is designed to talk to a Duct webserver via
[Transit][] over JSON.

[duct]: https://github.com/duct-framework/duct
[cljs-http]: https://github.com/r0man/cljs-http
[transit]: https://github.com/cognitect/transit-clj

## Installation

Add the following dependency to your deps.edn file:

    org.duct-framework/client.http {:mvn/version "0.1.0"}

Or to your Leiningen project file:

    [org.duct-framework/client.http "0.1.0"]

## Usage

This library provides functions for the main HTTP methods: `get`, `post`,
`put`, `patch` and `delete`.

```clojurescript
(ns example.client
  (:require [duct.client.http :as http]))

;; get data from the server via a string URL
(http/get "/user/1")

;; ...or with vector format for URLs
(http/get [:user 1])

;; query parameters can also be added
(http/get [:user :search] {:q "alice"})
```

The vector format turns each element into a string, then separates
them by a slash. A starting slash is added if missing.

The value returned is a core.async channel, so to get the result you
will need a go block.

```clojurescript
(ns example.client
  (:require [clojure.core.async :refer [(<! go)]]
            [duct.client.http :as http]))

(go (let [user (<! (http/get [:user 1]))]
      (prn (:user/id user))))
```

With `get`, the second argument is a map of query parameters. With all
other functions, the second argument represents the body of the request.
This will be serialised using the Transit format.

```clojurescript
(http/post [:user] {:user/email "alice@example.com"})
```

## License

Copyright © 2025 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
