(ns misc-script.mars-rover.rover
  (:require [clojure.core.matrix :as matrix]))

(defprotocol IRover
  (move [this] "Moves the rover by 1u")
  (rotate [this θ] "Rotates the rover by angle θ"))

(declare rotation-matrix)

(defrecord Rover [position dir]
  IRover
  (move [this]
    (assoc this :position (map + position (matrix/transpose dir))))
  (rotate [this θ]
    (assoc this :dir
           (map #(Math/round %)
                (matrix/transpose
                 (matrix/mmul (rotation-matrix θ) dir))))))
                                        ; (def rover (Rover. [0,0] [0,1]))
                                        ; (move rover)
                                        ; (rotate rover 90)

;; private methods
;; The rotation matrix assumes counter-clockwise
(defn- rotation-matrix [θ]
  (let [θ (Math/toRadians θ)]
    [[(Math/cos θ) (- (Math/sin θ))]
     [(Math/sin θ) (Math/cos θ)]]))

                                        ;(rotation-matrix 90)

