(defproject tinyurl "0.1.0-SNAPSHOT"
  :main tinyurl.core
  :aot [tinyurl.core]
  :description "URL Shortener"
  :url "http://example.com/FIXME"
  :license {:name "GPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-core "1.12.1"]
                 [ring/ring-jetty-adapter "1.12.1"]
                 [compojure "1.7.1"]
                 [org.postgresql/postgresql "42.7.3"]
                 [org.clojure/java.jdbc "0.7.12"]]
  :uberjar-name "tinyurl.jar"
  :repl-options {:init-ns tinyurl.core})
