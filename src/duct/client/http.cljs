(ns duct.client.http
  (:refer-clojure :exclude [get])
  (:require [cljs-http.client :as http]
            [clojure.string :as str]))

(defn- ->path [x]
  (if (keyword? x) (name x) (str x)))

(defn- parse-url [url]
  (if (vector? url)
    (str/join "/" (map ->path url))
    url))

(defn- update-request [method url params]
  (http/request {:method method
                 :url (parse-url url)
                 :transit-params params
                 :headers {"Accept" "application/transit+json"
                           "X-Ring-Anti-Forgery" "1"}}))

(defn get
  ([url] (get url {}))
  ([url params]
   (http/request {:method :get
                  :url (parse-url url)
                  :query-params params
                  :headers {"Accept" "application/transit+json"}})))

(defn post
  ([url] (post url {}))
  ([url params] (update-request :post url params)))

(defn put
  ([url] (put url {}))
  ([url params] (update-request :put url params)))

(defn delete
  ([url] (delete url {}))
  ([url params] (update-request :delete url params)))

(defn patch
  ([url] (patch url {}))
  ([url params] (update-request :patch url params)))
