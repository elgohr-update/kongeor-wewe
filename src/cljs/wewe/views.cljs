(ns wewe.views
  (:require
   [re-frame.core :as re-frame]
   [wewe.subs :as subs]
   [wewe.util :as util]
   ))


;; home

(defn home-panel []
  (let [weather @(re-frame/subscribe [::subs/weather])]
    [:div
     [:div.card
      [:div.card-content
       [:div.media
        [:div.media-left
         [:i.fa.fa-bolt]
         #_[:figure.image.is-48x48
          [:img {:src "https://bulma.io/images/placeholders/96x96.png"
                 :alt "placeholder"}]]]
        [:div.media-content
         [:p.title.is-4 (:name weather)]
         [:p (str (:weather weather))]
         [:p (str (:sys weather))]
         [:p (str (:main weather))]]]]]]))


;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:section.section
     [:div.container
      [:h1.title "wewe!"]
      [show-panel @active-panel]]]))
