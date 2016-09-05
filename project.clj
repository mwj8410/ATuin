(defproject atuin "0.1.0-SNAPSHOT"
  :description ""
  :url "http://example.com/FIXME"
  :license {:name ""
            :url ""}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot atuin.core
  :target-path "target/%s"
  :test-paths ["test" "test/atuin"]
  :profiles {:uberjar {:aot :all}}
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
)
