(ns clj-oauth2.ring-test
  (:use clojure.test
        [clojure.data.json :only [json-str]]
        [clojure.pprint :only [pprint]])
  (:require [clj-oauth2.ring :as base]))



(deftest request-uri
    (testing
      "does not write port if standard"
      (is (= (base/request-uri {:scheme "http"
                                :server-name "example.com"
                                :server-port 80
                                :uri "/"}
                               {})
             "http://example.com/")))
    (testing
      "uses default port if not specified"
      (is (= (base/request-uri {:scheme "http"
                                :server-name "example.com"
                                :uri "/"}
                               {})
             "http://example.com/")))
    (testing
      "writes port if non-standard"
      (is (= (base/request-uri {:scheme "http"
                                :server-name "example.com"
                                :server-port 1234
                                :uri "/"}
                               {})
             "http://example.com:1234/")))
    (testing
      "forces https if set in params"
      (is (= (base/request-uri {:scheme "http"
                                :server-name "example.com"
                                :server-port 80
                                :uri "/"}
                               {:force-https true})
             "https://example.com/"))))

(deftest is-callback
    (testing
      "is-callback returns true for valid urls"
      (is (base/is-callback
            {:scheme "https"
             :server-name "example.com"
             :uri "/oauth/callback"}
            {:redirect-uri "https://example.com/oauth/callback"})))
    (testing
      "is-callback returns false for invalid urls"
      (is (not (base/is-callback
                 {:scheme "https"
                  :server-name "example.com"
                  :uri "/not-oauth-path"}
                 {:redirect-uri "https://example.com/oauth/callback"})))))
