(ns dieter.asset.garden
  (:require [garden.core :refer [css]]
            [dieter.pools :as pools]
            [dieter.settings :as settings]
            [dieter.asset :as asset]))

(defn preprocess-garden*
  "Actually preproccess the given garden file."
  [file]
  (css (load-file file)))

(defn preprocess-garden
  "Preprocess the given file as garden then pass it on."
  [file]
  (asset/memoize-file file preprocess-garden*))

(defrecord Garden [file]
  dieter.asset.Asset
  (read-asset [this _]
    (let [file (:file this)]
      (assoc this :content (preprocess-garden file)))))

(asset/register "garden" map->Garden)
