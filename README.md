# MessageQueue
Learn how to work with a large flow of tasks using RabbitMQ message broker, a popular implementation of MQ pattern

###Project structure:
1) StudentsDataProducer - web-application. Create a html form and configure RabbitMq exchange, queue and bind.
2) FoodApplicationGenerator - one of consumer. Get a message from "social_food" queue and create pdf-document.
3) FinancialAssistanceApplicationGenerator - one of consumer. Get a message from "financial_assistance" queue and create pdf-document.
4) TransportationCostsApplicationGenerator - one of consumer. Get a message from "transportation_costs" queue and create pdf-document.

![Project structure](/materials/structure.png)