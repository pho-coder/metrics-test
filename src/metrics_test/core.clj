(ns metrics-test.core
  (:require [metrics.reporters.jmx :as jmx]
            [metrics.core :refer [new-registry]]
            [metrics.counters :refer [counter inc! value]])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (def JR (jmx/reporter {}))
  (jmx/start JR)
  (let [reg (new-registry)
        users-connected (counter reg "users-connected")]
    (while true
      (prn (value users-connected))
      (Thread/sleep 1000)
      (inc! users-connected 10)))
  (jmx/stop JR))
