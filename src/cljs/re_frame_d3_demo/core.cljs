(ns re-frame-d3-demo.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [devtools.core :as devtools]
            [re-frame-d3-demo.handlers]
            [re-frame-d3-demo.subs]
            [re-frame-d3-demo.views :as views]
            [re-frame-d3-demo.config :as config]
            [re-frame-d3-demo.lib]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")
    (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
