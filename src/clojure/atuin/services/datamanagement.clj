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

(declare open-file)

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
(defn open-file
  [path file-name]
  (read-string (slurp (string/join "/" [path file-name]))))
