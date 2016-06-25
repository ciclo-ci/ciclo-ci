(ns ciclo.ciclo-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [ciclo.handler :refer :all]
            [ciclo.routes.ciclo :refer [jobs]]))

(facts "Example GET and POST tests"

       (fact "Test GET"
             (let [response (app (mock/request :get "/"))]
               (:status response) => 200
               (:body response) => (contains "<div class=\"column-1\">My first job</div>")
               (:body response) => (contains "<div class=\"column-1\">Another job</div>")
               (:body response) => (contains "<div class=\"column-1\">Last job</div>")))

       (fact "Test POST"
             (let [response (app (mock/request :post "/" {:name "A new job" :last-success "Never" :last-fail "Never"}))
                   new-job (filter #(= 4 (:id %)) @jobs)]
               (:status response) => 302
               (:name (first new-job)) => "A new job")))
