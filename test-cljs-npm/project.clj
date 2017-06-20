(defproject test-cljs-npm "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "1.1.5"]]
  :hooks [leiningen.cljsbuild]
  :dependencies [[org.clojure/clojure "1.9.0-alpha15"]
                 [org.clojure/clojurescript "1.9.518"]]
  :cljsbuild {
              :builds [{
                                        ; The path to the top-level ClojureScript source directory:
                        :source-paths ["src"]
                                        ; The standard ClojureScript compiler options:
                                        ; (See the ClojureScript compiler documentation for details.)
                        :compiler {
                                   :output-to "js/main.js"  ; default: target/cljsbuild-main.js
                                   :npm-deps {:left-pad "1.1.3"}
                                   :optimizations :simple
                                   :target :nodejs
                                   :pretty-print true}}]})
