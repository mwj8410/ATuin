(ns atuin.services.datamanagement
  (:gen-class)
  (:import (java.io BufferedWriter FileWriter))
            )

(require '[clojure.string :as string])

; This will need to be passed in
(def config {
  :dataPath (string/join "/" [(.getCanonicalPath (clojure.java.io/file ".")) "data"])
  :staticPath (string/join "/" [(.getCanonicalPath (clojure.java.io/file ".")) "staticData"])
})

(declare generate-default-chunk)
(declare open-file)

(defn create-world-instance
  [worldName]
  (let [generic-chunck (generate-default-chunk)
        container-path (string/join "/" [(get config :dataPath) worldName])
        index-file-path (string/join "/" [container-path "world.index"])
      ]
    (do
      (.mkdir (java.io.File. container-path))
      ; (println (str (java.util.UUID/randomUUID)))
      (spit (string/join "/" [container-path "def.chunk"]) generic-chunck :append false)
      (spit index-file-path [] :append false)
  )))

(defn get-active-world-list
  []
  (open-file (get config :dataPath) "activeWorld.index"))

(defn get-pc-list
  []
  (open-file (get config :dataPath) "pc.index"))

(defn get-world-template-list
  []
  (open-file (string/join "/" [(get config :staticPath) "templates"]) "template.index"))

(defn save-pc-list
  [file-data]
  (spit
    (string/join "/" [(get config :dataPath) "pc.index"])
    file-data :append false)
    )

; helper function
(defn generate-default-chunk
  []
  (loop [x 0 data []]
    (if (< x 1024)
      (recur (+ x 1) (conj data (loop [y 0 row []]
        (if (< y 1024)
          (recur (+ y 1) (conj row [0 0 1 0]))
          row))))
      data)))

(defn open-file
  [path file-name]
  (read-string (slurp (string/join "/" [path file-name]))))
