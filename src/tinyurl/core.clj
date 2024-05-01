(ns tinyurl.core
  (:require [compojure.core :refer [defroutes DELETE GET POST PUT]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [tinyurl.repository :refer [delete-url get-url save-url
                                        search-collision update-url]])
  (:gen-class))

(defn generate-short-url [len]
  (apply str (repeatedly len #(rand-nth "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"))))

(defn valid-url? [url]
  (re-matches #"^(https?):\/\/[^\s\/$.?#].[^\s]*$" url))

(defn is-admin? [headers]
  (= (System/getenv "APP_AUTH") (get headers "auth")))

(defn shorten-url [original-url]
  (let [short-url (generate-short-url 6)]
    (save-url short-url original-url)
    short-url))

(defn redirect-url [short-url]
  (if-let [original-url (not-empty (get-url short-url))]
    {:status 302
     :headers {"Location" (-> original-url first :original_url)}}
    {:status 404
     :body "URL not found."}))

(defroutes app-routes
  (POST "/normal-url" request
    (let [original-url (slurp (:body request))]
      (if-not (valid-url? original-url)
        {:status 400} 
        
        (or (-> (search-collision original-url) first :short_url)
               (shorten-url original-url)))))
  (GET "/:short-url" [short-url]
    (redirect-url short-url))
  (DELETE "/short-url/:short-url" request
    (if (is-admin? (:headers request))
      (if (delete-url (:short-url (:params request)))
        {:status 200}
        {:status 404})
      {:status 401}))
  (PUT "/short-url/:short-url" request
    (if (is-admin? (:headers request))
     (let [original-url (slurp (:body request)) short-url (:short-url (:params request))]
       (if (valid-url? original-url)
         (try 
           (if (update-url short-url original-url)
             {:status 200}
             {:status 404})
           (catch java.sql.BatchUpdateException _
             {:status 409})) 
         {:status 400}))
      {:status 401}))
  (route/not-found "Page not found"))

(defn -main []
  (jetty/run-jetty (var app-routes) {:port 3000}))

