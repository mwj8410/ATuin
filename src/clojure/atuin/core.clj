(ns atuin.core
  (:gen-class)
  (:require [atuin.modules.httprouter :as httprouter]
            [atuin.services.datamanagement :as datamanagement])
  (:import (com.sun.net.httpserver HttpExchange
                    HttpHandler)
           (java.net ServerSocket Socket SocketException)
           (java.io BufferedReader
                    InputStreamReader
                    OutputStreamWriter
                    PrintWriter
                    InputStreamReader)
            phaeserver.PhaeServer
            ))

(require '[clojure.string :as str])

(def config {:port 8080})

(defn -main
  "Starts a server"
  [& args]
  (PhaeServer/startServer
    (httprouter/mount-routs (PhaeServer/createServer (get config :port 80)))))
