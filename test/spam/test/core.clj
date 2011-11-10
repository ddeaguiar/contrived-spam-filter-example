(ns spam.test.core
  (:use [spam.core])
  (:use [clojure.test]))

(deftest should-return-correct-spam-probability
  (is (= 3/8 (probability-of :spam))))

(deftest should-return-correct-ham-probability
  (is (= 5/8 (probability-of :ham))))

(deftest should-calculate-correct-spam-probability-for-simple-message
  (is (= 3/9 (probability-of "secret" :spam))))
