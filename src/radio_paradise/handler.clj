(ns radio-paradise.handler
  (:use [compojure.core]
        [clojure.string :only [split]])
  (:require [compojure.handler :as handler]
            [clj-http.client :as request]
            [clojure.data.json :as json]
            [compojure.route :as route]))

(def +memo-expire+ 10000)

(defn crop [html]
  (subs html (.indexOf html "<a") (.indexOf html "<img")))

(defn now []
  (System/currentTimeMillis))

(defn select [html]
  (subs html (+ (.indexOf html "<b>") 3) (.indexOf html "</b>")))

(defn parse [input]
  (reverse (split input #"\n+")))

(defn strip [html]
  (.trim (.replaceAll html "<[^<]+>" "\n")))

(defn now-playing []
  (parse (strip (crop (:body (request/get "http://www.radioparadise.com/ajax_rp2_playlist.php"))))))

(defn response [&_]
  (json/write-str {:now-playing (now-playing)
                   :last-update (now)
                   :contact "@4zjs"
                   :source "http://radioparadise.com" }))

(def response-memo (memoize response))

(defn call-response []
  (response-memo (quot (now) +memo-expire+)))

(defroutes app-routes
  (GET "/" {params :params}
       (let [callback (:callback params)]
         (if callback
           (str callback "(" (call-response) ")")
           (call-response)
         ))))

(def app
  (handler/site app-routes))
