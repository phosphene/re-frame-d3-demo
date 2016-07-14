(ns re-frame-d3-demo.handlers
    (:require [re-frame.core :as re-frame]
              [re-frame-d3-demo.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :update
 (fn [db [_ idx param val]]
   (println "idx " idx "param " param "val " val)
   (assoc-in db/default-db [:circles idx param ] val)))
