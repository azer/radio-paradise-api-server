(ns radio-paradise.api
  (:use [compojure.core])
  (:require [compojure.handler :as handler]
            [clojure.data.json :as json]
            [radio-paradise.now-playing :as now-playing]
            [compojure.route :as route]))

(def +memo-expire+ 10000)

(defn now []
  (System/currentTimeMillis))

(defn response [&_]
  (json/write-str {:now-playing (now-playing/songs)
                   :last-update (now)
                   :contact "@4zjs"
                   :source "http://radioparadise.com" }))

(def response-memo (memoize response))

(defn call-response []
  (response-memo (quot (now) +memo-expire+)))

(defroutes urls
  (GET "/" {params :params}
       (let [callback (:callback params)]
         (if callback
           (str callback "(" (call-response) ")")
           (call-response)
           ))))

(def application (handler/site urls))
