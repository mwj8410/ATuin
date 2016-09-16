(ns atuin.services.terrain
  (:gen-class)
  (:require [atuin.modules.httprouter :as httprouter])
  (:import (java.io BufferedWriter FileWriter)
            TopographyService))

(def hosted-chunk (new TopographyService))

(defn mount-chunk
  [chunk-data]
  (let [ y-size (count chunk-data) ; We assume square for now
        x-size (count chunk-data)]
    (loop [y 0]
      (loop [x 0]
        (let [part-data (nth (nth chunk-data y) x)]
          (.setVertex hosted-chunk y x
            (nth part-data 0)
            (nth part-data 1)
            (nth part-data 2)))
        (if (< x (- x-size 1)) (recur (+ x 1))))
      (if (< y (- y-size 1)) (recur (+ y 1))))))

(defn get-vertex
  [x, y]
  (vec (.getVertex hosted-chunk y x)))

(defn set-vertex
  [x y new-vertex]
  (.setVertex hosted-chunk y x
    (nth new-vertex 0)
    (nth new-vertex 1)
    (nth new-vertex 2)))
