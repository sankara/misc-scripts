(ns misc-script.mars-rover
  (:require [clojure.core.matrix :as matrix]))

;; Direction is a unit vector. Making it easy to compute movements and transformation
(def rover {:position [0,0] :dir (matrix/transpose [0, 1])})

;; Moving a rover is simply the vector sum of position and the direction
(defn move [rover]
  (assoc rover :position (map + (:position rover) (matrix/transpose (:dir rover)))))

                                        ;(move rover)

;; The rotation matrix assumes counter-clockwise
(defn- rotation-matrix [θ]
  (let [θ (Math/toRadians θ)]
    [[(Math/cos θ) (- (Math/sin θ))]
     [(Math/sin θ) (Math/cos θ)]]))

                                        ;(rotation-matrix 90)

;; Rotate is vector multiplication of the direction unit column vector by the rotation matrix
;; Again θ is based on counter-clockwise movement
(defn rotate [rover θ]
  (assoc rover :dir
         (map #(Math/round %); Stupid floating point maths
              (matrix/transpose
               (matrix/mmul (rotation-matrix θ) (:dir rover))))))

                                        ;(rotate rover 90)

(defn execute-instruction [rover instruction]
  (case instruction
    "M" (move rover)
    "L" (rotate rover 90)
    "R" (rotate rover -90)))

                                        ; (execute-instruction rover "L")

(defn 
