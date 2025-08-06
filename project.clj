(defproject org.duct-framework/client.http "0.1.0-SNAPSHOT"
  :description "A ClojureScript HTTP client for the Duct framework"
  :url "https://github.com/duct-framework/client.http"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.12.1"]
                 [org.clojure/clojurescript "1.12.42"]
                 [cljs-http "0.1.48"]]
  :aliases
  {"test" ["with-profile" "+test" "run" "-m" "kaocha.runner"]}
  :profiles
  {:test {:dependencies [[lambdaisland/kaocha "1.91.1392"]
                         [com.lambdaisland/kaocha-cljs "1.5.154"]]}})
