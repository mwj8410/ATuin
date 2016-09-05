(ns atuin.core-test
  (:require [clojure.test :refer :all]
            [atuin.core :refer :all]))

(deftest core-test
  (testing "contains routList"
    (is (> (count routList)))))
