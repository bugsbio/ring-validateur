(defproject ring-validateur "0.1.0"
  :description "Ring middleware and helper functions for validateur"
  :url "http://github.com/bugsbio/ring-validateur"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :signing {:gpg-key "CF73E6ED"}
  :scm {:name "git"
        :url "https://github.com/bugsbio/ring-validateur"}
  :profiles {:dev {:dependencies [[expectations  "2.0.9"]]
                   :plugins [[lein-autoexpect "1.0"]
                             [lein-expectations "0.0.8"]]}}
  :java-source-paths ["src/java"]
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.novemberain/validateur "2.4.2"]])
