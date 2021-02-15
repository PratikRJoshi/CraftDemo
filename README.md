##Intuit Craft Demo Statement:

1. Get a `hello world` Dropwizard app working locally via Postman - should be able to run `GET` and `POST` on the app via Postman
2. Connect the app to `mysql` running in a docker container
3. Insert the `POST` values into the db via Postman POST call; `GET` back the values from the DB via Postman
4. Add <some API> (Yelp, Yahoo finance, etc) in the app logic that allows CRUD ops - `GET`, `POST`, `DELETE`, `UPDATE` that go into and come out of the db
5. Decide how to set up the entire environment in docker
6. <optional> Add a basic html page that sends requests to the API endpoints defined above - POST into the DB and GET back from the db and show on the UI
7. <optional> Host the app on AWS ec2 instance and connect it to the db running on that same instance