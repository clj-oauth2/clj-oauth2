(def dev-dependencies
  '[[ring "1.1.5"]])

(defproject tk.skuro/clj-oauth2 "0.5.1"
  :description "clj-http and ring middlewares for OAuth 2.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/data.json "0.1.1"]
                 [clj-http "0.5.6"]
                 [uri "1.1.0"]
                 [commons-codec/commons-codec "1.6"]]
  :exclusions   [org.clojure/clojure-contrib]
  :dev-dependencies ~dev-dependencies
  :profiles {:dev {:dependencies ~dev-dependencies}}
  :repositories {"stuartsierra-releases" "http://stuartsierra.com/maven2"}
  :aot [clj-oauth2.OAuth2Exception
        clj-oauth2.OAuth2StateMismatchException])
