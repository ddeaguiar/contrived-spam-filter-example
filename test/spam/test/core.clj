(ns spam.test.core
  (:use [spam.core])
  (:use [clojure.test]))

(deftest should-return-correct-spam-probability
  (is (= 3/8 (probability_of :spam))))

(deftest should-return-correct-ham-probability
  (is (= 5/8 (probability_of :ham))))
