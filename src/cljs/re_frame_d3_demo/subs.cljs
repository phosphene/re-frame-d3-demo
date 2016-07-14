(ns re-frame-d3-demo.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))


(re-frame/register-sub
 :data
 (fn [db]
   (reaction (:data @db))))


(re-frame/register-sub
 :circles
 (fn [db]
   (reaction (:circles @db))))
