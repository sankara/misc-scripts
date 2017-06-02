(ns misc-script.mars-rover.commander
  (:use [misc-script.mars-rover.rover]))

(defprotocol ICommander
  (execute [this rover instructions]))

(declare execute-instruction)

(defrecord Commander [rover]
  ICommander
  (execute [this rover instructions]
    (loop [rover rover
           instructions (seq instructions)]
      (if (empty? instructions)
        rover
        (recur
         (execute-instruction rover (first instructions))
         (rest instructions))))))

                                        ; (def r1 (->Rover [1 2] [0 1]))
                                        ; (def cmd (->Commander r1))
                                        ; (execute cmd r1 "LM")
;; Private functions
(defn- execute-instruction [rover instruction]
  (case instruction
    \M (move rover)
    \L (rotate rover 90)
    \R (rotate rover -90)))
                                        ; (def rover (->Rover [0 0] [0 1]))
                                        ; (execute-instruction rover \L)
                                        ; (execute-instruction rover \R)
                                        ; (execute-instruction rover \M)
