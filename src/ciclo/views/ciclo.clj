(ns ciclo.views.ciclo
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html h]]))

(defn common-layout [& body]
  (html5
   [:head
    [:title "Ciclo"]
    (include-css "/css/ciclo.css")
    (include-css "http://netdna.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.css")]
   [:body
    [:div#wrapper
     [:h1#content-title "Ciclo"]
     [:div#jobs
      [:div.job.header
       [:div.column-1
        [:i.fa.fa-cog.icon-style] " Name"]
       [:div.column-2
        [:i.fa.fa-calendar-check-o.icon-style] " Last success"]
       [:div.column-3
        [:i.fa.fa-calendar-times-o.icon-style] " Last fail"]
       [:div.clear-row]]
    body]]]))

(defn add-job-form []
  (html
    [:div.job
      [:form {:action "/" :method "post"}
        [:div.column-1
          [:input#name-input {:type "text" :name "name" :placeholder "Name"}]]
        [:div.column-2
          [:input#last-success-input {:type "text" :name "last-success" :placeholder "Last success"}]]
        [:div.column-3
          [:input#last-fail-input {:type "text" :name "last-fail" :placeholder "Last fail"}]]
        [:button.button.add.fa.fa-plus {:type "submit"}]]
        [:div.clear-row]]))

(defn read-job [job]
  (html
    [:div.job
      [:div.job-text
        [:div.column-1 (h (:name job))]
        [:div.column-2 (h (:last-success job))]
        [:div.column-3 (h (:last-fail job))]]
      [:div.button-group
        [:form {:action (str "/edit/" (h (:id job))) :method "get"}
          [:button.button.edit {:type "submit"}
            [:i.fa.fa-pencil]]]
        [:form {:action (str "/delete/" (h (:id job))) :method "post"}
          [:button.button.remove {:type "submit"}
            [:i.fa.fa-remove]]]]
      [:div.clear-row]]))

(defn edit-job-form [job]
  (html
   [:div.job
    [:form {:action (str "/edit/" (:id job)) :method "post"}
     [:input {:type "hidden" :name "id" :value (h (:id job))}]
     [:div.column-1
      [:input#name-input {:type "text" :name "name" :placeholder "Name" :value (h (:name job))}]]
     [:div.column-2
      [:input#last-success-input {:type "text" :name "last-success" :placeholder "Last Success" :value (h (:last-success job))}]]
     [:div.column-3
      [:input#last-fail-input {:type "text" :name "last-fail" :placeholder "Last Fail" :value (h (:last-success job))}]]
     [:div.button-group
      [:button.button.update {:type "submit"} [:i.fa.fa-check]]]]
    [:div.clear-row]]))
