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

(defn words [message]
  (re-seq #"\w+" message))

(defn freq [type]
  (map frequencies (map words (bag type))))

(defn inc-freq [type]
  (apply merge-with + (freq type)))

(defn probability-of 
  ([type]
    (/ (cnt type) (bag-size)))
  ([message type]
    (let [freqs (inc-freq type)]
      (/ (freqs  message)
       (apply + (vals freqs))))))
