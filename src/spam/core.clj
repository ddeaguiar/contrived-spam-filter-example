(ns spam.core)

(def bag {:spam ["offer is secret",
                  "click secret link",
                  "secret sports link"]
           :ham  ["play sports today",
                  "want play sports",
                  "secret sports event",
                  "sports is today",
                  "sports cost money"]})

(defn cnt [type]
  (cond (= :spam type)
          (count (bag :spam))
        (= :ham type)
          (count (bag :ham))))

(defn bag-size []
  (+ (cnt :spam)
     (cnt :ham)))

(defn probability_of [type]
  (/ (cnt type) (bag-size)))
