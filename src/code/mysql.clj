(ns code.mysql
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def regex #"(?m)UPDATE (.*) SET (.*) = (.*) WHERE id = (.*);")

(comment
  (def file (slurp "f"))
  (->> (s/split-lines file)
       (map #(re-find regex %))
       (map #(take-last 2 %))
       (group-by first)
       (map (fn [[k vs]]
              (let [ids (map last vs)]
                (str "UPDATE a SET b = " k
                     " WHERE id IN (" (s/join ", " ids) ");")))))
  :rcf)
