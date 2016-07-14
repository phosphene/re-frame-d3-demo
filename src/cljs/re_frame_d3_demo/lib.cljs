(ns re-frame-d3-demo.lib
  (:require
            [re-frame.core :refer [register-handler
                                   path
                                   register-sub
                                   dispatch
                                   dispatch-sync
                                   subscribe]]

            [reagent.core :as reagent :refer [atom]]
            [cljsjs.d3]))


(def bar-width 80)
(def graph-height 450)
(def bar-sep 10)
(def max-data 50)

(def horizontal-bar-distance (+ bar-width bar-sep))

(def bar-height-multiplier (/ graph-height  max-data))
(defn rect-x [i] (* i horizontal-bar-distance))
(defn rect-y [d] (- graph-height (* d bar-height-multiplier)))
(defn rect-height [d] (* d bar-height-multiplier))
;;(def color (js/d3 (.rgb ("darkslateblue"))))

(defn d3-circle [data]
 (reagent/create-class
    {:reagent-render (fn [] [:div [:svg {:width 400 :height 800}]])

     :component-did-mount (fn []
                            (let [d3data (clj->js data)]
                              (.. js/d3
                                  (select "svg")
                                  (selectAll "circle")
                                  (data d3data)
                                  enter
                                  (append "circle")
                                  (attr "cx" (fn [d] (.-x d)))
                                  (attr "cy" (fn [d] (.-y d)))
                                  (attr "r" (fn [d] (.-r d)))
                                  (attr "fill" (fn [d] (.-color d))))))

     :component-did-update (fn [this]
                             (let [[_ data] (reagent/argv this)
                                   d3data (clj->js data)]
                               (.. js/d3
                                   (selectAll "circle")
                                   (data d3data)
                                   (attr "cx" (fn [d] (.-x d)))
                                   (attr "cy" (fn [d] (.-y d)))
                                   (attr "r" (fn [d] (.-r d)))))
    )}))




(defn slider [param idx value]
  [:input {:type "range"
           :value value
           :min 0
           :max 500
           :style {:width "100%"}
           :on-change #(dispatch [:update idx param (-> % .-target .-value)])}])




(defn sliders [data]
  [:div (for [[idx d] (map-indexed vector data)]
          ^{:key (str "slider-" idx)}
          [:div
           [:h3 (:name d)]
           "x " (:x d) (slider :x idx (:x d))
           "y " (:y d) (slider :y idx (:y d))
           "r " (:r d) (slider :r idx (:r d))])])
