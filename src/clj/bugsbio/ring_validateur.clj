(ns bugsbio.ring-validateur
  (:require
    [validateur.validation :refer [validation-set]])
  (:import
    [bugsbio.exceptions ValidationException]))

(defn validate!
  "Given a map and a variable number of validateur validation rules,
  run the validation and either return the map if validation is
  successful, or throw a ValidationException with the resulting errors
  if not. Best used in conjunction with `wrap-validation` in order to
  catch the ValidationExceptions and return a useful 400 response."
  [model & validations]
  (let [errors ((apply validation-set validations) model)]
    (if (empty? errors)
      model
      (throw (ValidationException. errors)))))

(defn default-validation-failed-response
  [e]
  {:status 400
   :body {:error "Failed validation"
          :validation-errors (.getValidationErrors e)}})

(defn wrap-validation
  "Middleware that catches ValidationExceptions and returns a 400
  response containing the failed validation rule details."
  [handler & [response-fn]]
  #(try (handler %)
        (catch ValidationException e
          ((or response-fn default-validation-failed-response) e))))
