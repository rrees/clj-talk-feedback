(ns clj-talk-feedback.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults secure-site-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))


(def production?
    (= "production" (get (System/getenv) "APP_ENV")))

(def development? (not production?))

(def app
  (wrap-defaults app-routes
      (if production? (assoc secure-site-defaults :proxy true) site-defaults)))
