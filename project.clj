(defproject misc-scripts "0.1.0"
  :description "Misc scripts"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [lein-light-nrepl "0.0.17"]
                 [revise "0.0.6"]
                 [clj-http "3.4.1"]
                 [clj.qrgen "0.4.0"]
                 [net.mikera/core.matrix "0.60.3"]
                 [uncomplicate/clojurecuda "0.2.0"]
                 [uncomplicate/neanderthal "0.11.1"]]
  :repl-options {
                 :host "localhost"
                 :port 9090})
