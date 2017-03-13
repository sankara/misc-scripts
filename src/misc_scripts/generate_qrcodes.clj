(ns misc-scripts.generate-qrcodes
  (:require [clj-http.client :as http]))

(defn shorten [url]
  (let [response (http/get "https://api-ssl.bitly.com/v3/shorten/"
                           {:query-params {
                                           :login ""
                                           :apiKey ""
                                           :longUrl url
                                           }
                            :as :json})]
    (-> response :body :data :url)))
                                        ;(shorten "https://www.google.com")

(defn short-qrcode [url filename]
  (let [short-url (shorten url)
        response (http/get "https://zxing.org/w/chart"
                           {:query-params {
                                           :cht "qr"
                                           :chs "250x250"
                                           :chld "L"
                                           :choe "UTF-8"
                                           :chl short-url}
                            :as :stream})]
    (clojure.java.io/copy (:body response) (java.io.File. filename))))

;(short-qrcode "https://www.google.com" "/tmp/google.png")

(defn extract-sku [url]
  (first (clojure.string/split (second (clojure.string/split (last (clojure.string/split url #"\?")) #"=")) #"&")))
;(extract-sku (first urls))

(map #(short-qrcode % (clojure.string/join ["/tmp/" (extract-sku %) ".png"])) urls)

#_(def urls ["https://www.google.com"])
    
