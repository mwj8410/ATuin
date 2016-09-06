(ns atuin.services.datamanagement-test
  (:require [clojure.test :refer :all]
            [atuin.services.datamanagement :refer :all]
            ))

(require '[clojure.string :as string])

(deftest activeWorld
  (testing "Opens the Active World index file"
    (is (= (nth (get-active-world-list) 0) "exampleWorld")))
)

(deftest playerCharacter
  (testing "Allows saving content as Player Character list"
    (do
      (save-pc-list ["test"])
      (is (= (nth (open-file
          (string/join "/" [(.getCanonicalPath (clojure.java.io/file ".")) "data"])
          "pc.index"
        ) 0)  "test"))))
  (testing "Opens the Player Character index file"
    (is (= (nth (get-pc-list) 0) "test")))
  (testing "Opens the Player Character index file"
    (is (= (nth (get-world-template-list) 0) "testWorld")))
)

(deftest internals
  (testing "helper: allows opening a file"
    (is (= (nth (open-file
        (string/join "/" [(.getCanonicalPath (clojure.java.io/file ".")) "data"])
        "testfile.txt"
      ) 0)  "test content")))
)
