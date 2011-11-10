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
  (if (seq? message)
    message
    (re-seq #"\w+" message)))

(defn inc-freq [type]
  (apply merge-with + (map frequencies (map words (bag type)))))

(defn probability-of 
  ([type]
    (/ (count (bag type)) (bag-size)))
  ([message type]
    (let [coll (words message)]
      (if (< 1 (count coll))
        (* (probability-of (first coll) type)
           (probability-of (rest coll) type))
      (let [freqs (inc-freq type)]
        (/ (or (freqs (first coll)) 0)
           (apply + (vals freqs))))))))

(defn probability-spam [message]
  (let [p-spam (probability-of :spam)
        p-ham  (probability-of :ham)
        p-message-given-spam (probability-of message :spam)
        p-message-given-ham (probability-of message :ham)]
  (/ (* p-message-given-spam p-spam)
     (+ (* p-message-given-spam p-spam)
            (* p-message-given-ham p-ham))
         )))
