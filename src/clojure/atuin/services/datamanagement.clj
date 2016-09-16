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

(declare get-active-world-list)

(defn add-world-to-index
  "Adds an entry to the current world index."
  [worldName collectionName]
  (spit
    (string/join "/" [(get config :dataPath) "activeWorld.index"])
    (conj [] ; get-active-world-list
      [worldName collectionName (str (java.util.UUID/randomUUID))])
    :append false))

(defn create-world-instance
  "Creates a new blank world in the configured dataPath."
  [worldName height width]
  (let [generic-chunck (generate-default-chunk)
        container-path (string/join "/" [(get config :dataPath) worldName])
        index-file-path (string/join "/" [container-path "world.index"])
      ]
    (do
      ; create the dir
      (.mkdir (java.io.File. container-path))
      ; create each chunk file and save the UUIDs to a tracking array that get
      ; saved to the `world.index` file within the container directory
      (spit index-file-path (loop [y 0 col []]
        (if (< y height)
          (recur (+ y 1) (conj col (loop [x 0 row []]
            (if (< x width)
              (let [key (str (java.util.UUID/randomUUID))]
                ; generate the chunk file using the UUID as the file name
                (spit
                  (string/join "/" [container-path (string/join "." [key "chunk"])])
                  generic-chunck :append false)
                (recur (+ x 1) (conj row key)))
              ; return the row data to the outer loop
              row))))
          ; return the completeled data structure to the index file creation
          col))
        :append false))))

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
  "Generates a default terrain data chunk"
  []
  (loop [x 0 data []]
    (if (< x 1024)
      (recur (+ x 1) (conj data (loop [y 0 row []]
        (if (< y 1024)
          (recur (+ y 1) (conj row [0 0 1 {}]))
          row))))
      data)))

(defn open-file
  [path file-name]
  (read-string (slurp (string/join "/" [path file-name]))))
