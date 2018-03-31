(ns tic-tac-toe.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
  ::db
  (fn [db]
    db))

(re-frame/reg-sub-raw
  ::cell
  (fn [db [_ x y]]
    (reaction (get-in @db [:board [x y]]))))

(re-frame/register-sub
  ::turn
  (fn [db _]
    (reaction (:turn @db))))

(re-frame/register-sub
  ::winner
  (fn [db _]
    (reaction (:winner @db))))