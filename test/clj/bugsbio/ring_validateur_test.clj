(ns bugsbio.ring-validateur-test
  (:require
    [bugsbio.ring-validateur :as rv]
    [validateur.validation   :as v]
    [expectations            :refer :all]))

(defn handler
  [params]
  (rv/validate! params
                (v/presence-of :name))
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    (str "Hello, " (:name params))})

(def app
  (-> handler
      (rv/wrap-validation)))

;; If validation succeeds you get the response you expect
(expect {:status  200
         :headers  {"Content-Type"  "text/plain"}
         :body    "Hello, Russell"}

        (app {:name "Russell"}))

;; If it fails you get a 400 with the details
(expect {:status 400
         :body {:error             "Failed validation"
                :validation-errors {:name #{"can't be blank"}}}}
        (app {:name nil}))

;; You can set your own custom response function
(def app-with-custom-response
  (-> handler
      (rv/wrap-validation (fn [e] {:status 400
                                   :body "You got validation errors :-("
                                   :headers {"Content-Type" "text/plain"}}))))

(expect {:status 400
         :body "You got validation errors :-("
         :headers {"Content-Type" "text/plain"}}

        (app-with-custom-response {:name nil}))
