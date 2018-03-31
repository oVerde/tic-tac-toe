(ns tic-tac-toe.views
  (:require [re-frame.core      :as re-frame]
            [tic-tac-toe.events :as events]
            [tic-tac-toe.subs   :as subs]))

(defn cell [x y]
 (let [c (re-frame/subscribe [::subs/cell x y])
       winner (re-frame/subscribe [::subs/winner])]
  (fn []
    (case @c
      :x [:span "X"]
      :o [:span "O"]
      (if @winner
        [:span]
        [:button {:on-click #(re-frame/dispatch [::events/move x y])
                  :style {:width 30
                          :height 30}}
         " "])))))


(defn grid []
  [:table
   [:tbody
    (for [y (range 3)]
      [:tr
       (for [x (range 3)]
         [:td {:style {:width 30
                       :height 30
                       :text-align :center}}
          [cell x y]])])]])

(defn main-panel []
  (let [db     (re-frame/subscribe [::subs/db])
        turn   (re-frame/subscribe [::subs/turn])
        winner (re-frame/subscribe [::subs/winner])]
    (fn []
      [:div
       (if @winner
         [:div
          (name @winner) " is the winner!"]
         [:div
          (name @turn) " to play."])
       [grid]
       [:button {:on-click #(re-frame/dispatch [::events/initialize-db])}
        "RESET"]
       [:div
        (pr-str @db)]])))



