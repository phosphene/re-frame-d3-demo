(ns re-frame-d3-demo.views
    (:require [re-frame.core :as re-frame]
              [re-frame-d3-demo.lib :as lib]))



(defn main-panel []
  (let [data (re-frame/subscribe [:circles])]
    (fn []
      [:div {:class "container"}
       [:div {:class "row"}
        [:div {:class "col-md-5"}
         [lib/d3-circle @data]]
        [:div {:class "col-md-5"}
         [lib/sliders @data]]]]
      )))
