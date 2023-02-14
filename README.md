--> This project is a entire backend of an E-commerce app with different services having different responsibilities. The backend services have a common API gateway so that all the services do not get directly exposed to the client(web or app).\
--> Also the API gateway has OAuth 2.0 security backed with Okta auth so that only the authenticated users get access to the services and their access rights are also authorized.\
--> The microservices nteract with each other depending on the services being called.\
--> The following project also has concepts like Feign Client, Rest Template, API Gateway, Circuit Breaker, Rate Limiter etc.\
-->The project has 3 services, one service registory, one config server, one cloud gateway 3 databases and one API Gateway.\

-->The following are the services:\

1) Product Service - This service contains all the Product information and is also responsible for reducing the quantity of products in the database when and order is successfully placed. All the functionalities could be seen in the service and I have added suitable comments also in it to explain them.\
2) Order Service - this service is responsible for placing the order. All the functionalities could be seen in the service and I have added suitable comments also in it to explain them.\
3) Payment Service - this service is responsible for making the payment. All the functionalities could be seen in the service and I have added suitable comments also in it to explain them.\

--> Config server is uploaded on the github and contains all the repeating and relevant configurations.\

--> Service registory is where all the services are there on the cloud.\

--> Cloud Gateway is basically the API Gateway.\

--> Rest all is self explanatory in the code when one would go through all the microservices created.
