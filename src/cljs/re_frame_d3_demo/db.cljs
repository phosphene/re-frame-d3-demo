(ns re-frame-d3-demo.db)

(def default-db
  {:circles [{:name "circle 1"
              :x 10
              :y 10
              :r 10
              :color "black"}
             {:name "circle 2"
              :x 35
              :y 35
              :r 15
              :color "red"}
             {:name "circle 3"
              :x 100
              :y 100
              :r 30
              :color "blue"}]})
