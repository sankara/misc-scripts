(ns test-cljs-npm.core
  (:require left-pad))

(enable-console-print!)
(println "Hello, world")
(println (left-pad 42 5 0))
