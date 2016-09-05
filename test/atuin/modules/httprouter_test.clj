(ns atuin.modules.httprouter-test
  (:require [clojure.test :refer :all]
            [atuin.modules.httprouter :refer :all]
            ))

(deftest httprouter-test
  ; (testing "contains config"
  ;   (is (> (count config))))
  (testing "contains routList"
    (is (> (count routList)))))
