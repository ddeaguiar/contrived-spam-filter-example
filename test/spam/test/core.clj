(ns spam.test.core
  (:use [spam.core])
  (:use [clojure.test]))

(deftest should-return-correct-spam-probability
  (is (= 3/8 (probability-of :spam))))

(deftest should-return-correct-ham-probability
  (is (= 5/8 (probability-of :ham))))

(deftest should-calculate-correct-word-probability-given-spam
  (is (= 3/9 (probability-of "secret" :spam))))

(deftest probability-of-unknown-word-should-be-zero
  (is (zero? (probability-of "foobar" :spam))))

(deftest should-calculate-correct-sentence-probability-given-spam
  (is (= 1/81 (probability-of "secret is secret" :spam))))

(deftest should-calculate-correct-spam-probability-given-sentence
  (is (= 25/26 (probability-spam "secret is secret"))))
