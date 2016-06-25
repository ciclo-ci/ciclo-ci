(ns ciclo.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ciclo.routes.ciclo :refer [ciclo-routes]]))

(defn init []
  (println "Ciclo is starting..."))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes ciclo-routes app-routes)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
