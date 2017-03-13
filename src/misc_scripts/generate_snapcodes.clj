(ns misc-scripts.generate-snapcodes
  (:require [clj-http.client :as http]))

(def auth-params {
                  :auth_ticket ""
                  :user_id     ""
                  :username    ""})

(def headers {
              "Host"            "app.snapchat.com"
              "User-Agent"      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:51.0) Gecko/20100101 Firefox/51.0"
              "Accept"          "*/*"
              "Accept-Language" "en"
              "Referer"         "https://scan.snapchat.com/"
              "Content-Type"    "application/x-www-form-urlencoded; charset=UTF-8"
              "Origin"          "https://scan.snapchat.com"
              })

(defn generate-code [url]
  (let [response (http/post "https://app.snapchat.com/scan/scan_quick_url"
                            {:query-params auth-params
                             :headers      headers
                             :form-params  {"visitUrl" url}
                             :as :json})]
    (-> response :body :scannableId)))
                                        ;(generate-code "https://www.google.com")

  
(defn download-snapcode [url filename]
  (let [response (http/get "https://app.snapchat.com/scan/scan_quick_url"
                           {:query-params (merge auth-params {:scannable_id (generate-code url) :type "png"})
                            :headers headers
                            :as :stream})]
    (clojure.java.io/copy (:body response) (java.io.File. filename))))
                                        ;(download-snapcode "https://www.google.com" "/tmp/google.png")

(defn extract-sku [url]
  (last (clojure.string/split (last (clojure.string/split url #"\?")) #"=")))

(map #(download-snapcode %1 (clojure.string/join ["/tmp/" (extract-sku %1) ".png"]))
     [
      "https://www.google.com"
      ])
