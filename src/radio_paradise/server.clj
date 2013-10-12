(ns radio-paradise.server
  (:use [ring.adapter.jetty :as ring])
  (:require [radio-paradise.api :as api]))

(defn start [port]
  (run-jetty #'api/application {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (start port)))
