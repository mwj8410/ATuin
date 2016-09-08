(ns atuin.services.datamanagement-test
  (:require [clojure.test :refer :all]
            [atuin.services.datamanagement :refer :all]
            ))

(require '[clojure.string :as string])

(deftest activeWorld
  (testing "Adds world entries"
    (add-world-to-index "Super Awesome World" "exampleWorld"))

  (testing "Creates a new world"
    (create-world-instance "exampleWorld" 1 1))
  (testing "Opens the Active World index file"
    (is (not (nil? (nth (get-active-world-list) 0)))))
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
  (testing
    (is (= (count (generate-default-chunk)) 1024)))
  (testing "helper: allows opening a file"
    (is (= (nth (open-file
        (string/join "/" [(.getCanonicalPath (clojure.java.io/file ".")) "data"])
        "testfile.txt"
      ) 0)  "test content")))
)
