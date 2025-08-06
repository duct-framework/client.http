(ns duct.client.http-test
  (:require [cljs.test :refer [deftest is]]
            [duct.client.http :as http]
            [cljs-http.client :as cljs-http]))

(deftest test-http-get
  (let [request (atom {})]
    (with-redefs [cljs-http/request #(reset! request %)]
      (http/get "/foo" {:q "bar"})
      (is (= {:method :get
              :url "/foo"
              :query-params {:q "bar"}
              :headers {"Accept" "application/transit+json"}}
             @request)))))

(deftest test-http-post
  (let [request (atom {})]
    (with-redefs [cljs-http/request #(reset! request %)]
      (http/post "/foo" {:name "bar"})
      (is (= {:method :post
              :url "/foo"
              :transit-params {:name "bar"}
              :headers {"Accept" "application/transit+json"
                        "X-Ring-Anti-Forgery" "1"}}
             @request)))))
