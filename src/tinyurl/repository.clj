(ns tinyurl.repository
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:dbtype (System/getenv "DB_DRIVER")
   :host (System/getenv "DB_HOST")
   :dbname (System/getenv "DB_NAME")
   :user (System/getenv "DB_USER")
   :password (System/getenv "DB_PASSWORD")})

(defn has-changes? [rows-update]
  (not (some #(= 0 %) rows-update)))

(defn save-url [short-url original-url] 
  (jdbc/insert! db-spec :urls {:original_url original-url :short_url short-url}))

(defn get-url [short-url] 
  (let [original-url (jdbc/query db-spec ["SELECT original_url FROM urls WHERE short_url = ?" short-url])]
    original-url))

(defn search-collision [original-url]
  (let [short-url (jdbc/query db-spec ["SELECT short_url FROM urls WHERE original_url = ?" original-url])]
    short-url))

(defn delete-url [short-url] 
  (let [rows-deleted (jdbc/delete! db-spec :urls ["short_url = ?" short-url])]
     (has-changes? rows-deleted)))

(defn update-url [short-url original-url]
  (let [rows-update (jdbc/update! db-spec :urls {:original_url original-url} ["short_url = ?" short-url])]
    (has-changes? rows-update)))