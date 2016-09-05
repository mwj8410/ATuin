(ns atuin.modules.httprouter
  (:gen-class)
  (:import (com.sun.net.httpserver HttpExchange
                    HttpHandler)
           (java.net ServerSocket Socket SocketException)
           (java.io BufferedReader
                    InputStreamReader
                    OutputStreamWriter
                    PrintWriter
                    InputStreamReader)
            phaeserver.PhaeServer))

(def routList [
  {
    :uri "/test"
    :handler (proxy [HttpHandler] []
      (handle [httpExchange]
        (do
          (println (PhaeServer/readRequestBody httpExchange))
          (def response "Test")
          (. httpExchange sendResponseHeaders 200 (. response length))
          (def outStream (. httpExchange getResponseBody))
          (. outStream write (. response getBytes))
          (. outStream close))))
  }
])

(defn mount-routs
  [server]
  (loop [ i 0 data routList]
    (PhaeServer/mountRout server ((nth data i) :uri) ((nth data i) :handler))
    (if (< i (- (count data) 1))
      (recur (+ i 1) data)))
  server)
