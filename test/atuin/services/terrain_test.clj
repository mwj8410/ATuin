(ns atuin.services.terrain-test
  (:require [clojure.test :refer :all]
            [atuin.services.terrain :refer :all]
            ))

(def terrainData
  [
    [[1 0 1 {}] [0 0 1 {}] [0 0 1 {}]]
    [[0 0 1 {}] [0 0 1 {}] [0 0 1 {}]]
    [[0 0 1 {}] [0 0 1 {}] [0 0 1 {}]]
  ]
)

(deftest mountChunk
  (testing "Allows a terrain chunk to be mounted."
    (mount-chunk terrainData)))

; (deftest getChunk
;   (testing "Returns a terrain chunk that is currently mounted"
;     (get-chunk)))

(deftest getVertex
  (testing "Returns a terrain vertex"
    (println (get-vertex 0 0))))

; (deftest setVertex
;   (testing "Returns a terrain vertex"
;     (do
;       (set-vertex 0 0 [1 1 1 1])
;       ; (println (get-vertex 0 0))
;     )))
