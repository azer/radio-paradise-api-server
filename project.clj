(defproject radio-paradise "0.1.0-SNAPSHOT"
  :description "Alternative Radio Paradise App"
  :url "http://paradise.azer.io"
  :main radio-paradise.server
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [org.clojure/data.json "0.2.3"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [clj-http "0.7.7"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler radio-paradise.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
