(ns compringmock.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [compringmock.handler :refer :all]
            [cheshire.core :as json]))

(defn parse-body [body]
  (json/parse-string (slurp body) true))

(deftest test-app
  (testing "user route"
    (let [user-data {:username "foo" :password "bar"}
          response (app (->
                          (mock/request :post "/auth/user")
                          (mock/body (json/generate-string user-data))
                          (mock/content-type "application/json")))]
      (is (= (-> response :body parse-body) user-data)))))
