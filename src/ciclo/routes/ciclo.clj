(ns ciclo.routes.ciclo
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [ciclo.views.ciclo :refer [common-layout
                                       read-job
                                       add-job-form
                                       edit-job-form]]))

(def jobs (atom [{:id 1 :name "My first job" :last-success "2016-06-23T10:05:23.003Z" :last-fail "Never"}
                 {:id 2 :name "Another job" :last-success "Never" :last-fail "Never"}
                 {:id 3 :name "Last job" :last-success "2016-06-24T23:02:02.002Z" :last-fail "2016-06-23T14:00:00.000Z"}]))

(defn next-id []
  (->>
   @jobs
   (map :id)
   (apply max)
   (+ 1)))

(defn get-route [request]
  (common-layout
   (for [job @jobs]
     (read-job job))
   (add-job-form)))

(defn post-route [request]
  (let [name (get-in request [:params :name])
        last-success (get-in request [:params :last-success])
        last-fail (get-in request [:params :last-fail])]
    (swap! jobs conj {:id (next-id) :name name :last-success last-success :last-fail last-fail})
    (response/redirect "/")))

(defn delete-job [id]
  (def jobs (atom (into [] (filter #(not= (:id %) id) @jobs)))))

(defn delete-route [request]
  (let [id (Integer. (get-in request [:params :id]))]
    (def jobs (atom (into [] (filter #(not= (:id %) id) @jobs))))
    (response/redirect "/")))

(defn edit-route [request]
  (let [id (Integer. (get-in request [:params :id]))]
    (common-layout
     (edit-job-form
      (first (filter #(= (:id %) id) @jobs))))))

(defn update-route [request]
  (let [id (Integer. (get-in request [:params :id]))
        name (get-in request [:params :name])
        last-success (get-in request [:params :last-success])
        last-fail (get-in request [:params :last-fail])]
    (def job (first (filter #(= (:id %) id) @jobs)))
    (def new_job (assoc job :name name :last-success last-success :last-fail last-fail))
    (delete-job id)
    (swap! jobs conj new_job)
    (response/redirect "/")))

(defroutes ciclo-routes
  (GET "/" [] get-route)
  (POST "/" [] post-route)
  (POST "/delete/:id" [] delete-route)
  (GET "/edit/:id" [id] edit-route)
  (POST "/edit/:id" [id] update-route))
