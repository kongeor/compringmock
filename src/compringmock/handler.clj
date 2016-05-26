(ns compringmock.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema User {:username s/Str
           :password s/Str})

(def app
  (api
    {:swagger
     {:ui "/api-docs"
      :spec "/swagger.json"
      :data {:info {:title "Sample API"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}
    (context "/auth" []
      (POST "/user" []
        :return User
        :body [user User]
        (do (println user)
            (ok user))))))
