(ns radio-paradise.now-playing
  (:use [clojure.string :only [split]])
  (:require [clj-http.client :as request]))

(defn crop [html]
  (subs html (.indexOf html "<a") (.indexOf html "<img")))

(defn parse [input]
  (reverse (split input #"\n+")))

(defn strip [html]
  (.trim (.replaceAll html "<[^<]+>" "\n")))

(defn songs []
  (parse (strip (crop (:body (request/get "http://www.radioparadise.com/ajax_rp2_playlist.php"))))))
