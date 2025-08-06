(ns duct.client.http
  (:refer-clojure :exclude [get])
  (:require [cljs-http.client :as http]
            [clojure.string :as str]))

(defn- ->path [x]
  (if (keyword? x) (name x) (str x)))

(defn- add-starting-slash [s]
  (if (str/starts-with? s "/") s (str "/" s)))

(defn- parse-url [url]
  (if (vector? url)
    (add-starting-slash (str/join "/" (map ->path url)))
    url))

(defn- update-request [method url params]
  (http/request {:method method
                 :url (parse-url url)
                 :transit-params params
                 :headers {"Accept" "application/transit+json"
                           "X-Ring-Anti-Forgery" "1"}}))

(defn get
  "Get a resource from a URL with an optional map of query parameters."
  ([url] (get url {}))
  ([url params]
   (http/request {:method :get
                  :url (parse-url url)
                  :query-params params
                  :headers {"Accept" "application/transit+json"}})))

(defn post
  "Create a resource at a URL with a Transit-encoded body."
  ([url] (post url nil))
  ([url params] (update-request :post url params)))

(defn put
  "Replace a resource at a URL with a Transit-encoded body."
  ([url] (put url nil))
  ([url params] (update-request :put url params)))

(defn delete
  "Delete a resource at a URL with a Transit-encoded body."
  ([url] (delete url nil))
  ([url params] (update-request :delete url params)))

(defn patch
  "Patch a resource at a URL with a Transit-encoded body."
  ([url] (patch url nil))
  ([url params] (update-request :patch url params)))
