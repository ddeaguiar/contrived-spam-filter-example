(ns spam.core)

(def bag {:spam ["offer is secret",
                  "click secret link",
                  "secret sports link"]
           :ham  ["play sports today",
                  "want play sports",
                  "secret sports event",
                  "sports is today",
                  "sports cost money"]})

(defn bag-size []
  (+ (count (bag :spam))
     (count (bag :ham))))

(defn words [message]
  (re-seq #"\w+" message))

(defn inc-freq [type]
  (apply merge-with + (map frequencies (map words (bag type)))))

(defn probability-of 
  ([type]
    (/ (count (bag type)) (bag-size)))
  ([message type]
    (let [freqs (inc-freq type)]
      (/ (or (freqs message) 0)
       (apply + (vals freqs))))))
