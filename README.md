# MessageQueue
Learn how to work with a large flow of tasks using RabbitMQ message broker, a popular implementation of MQ pattern

### Project structure:
1) StudentsDataProducer - web-application. Create a html form and configure RabbitMq exchange, queue and bind.
2) FoodApplicationGenerator - one of consumer. Get a message from "social_food" queue and create pdf-document.
3) FinancialAssistanceApplicationGenerator - one of consumer. Get a message from "financial_assistance" queue and create pdf-document.
4) TransportationCostsApplicationGenerator - one of consumer. Get a message from "transportation_costs" queue and create pdf-document.

![Project structure](/materials/structure.png)

5) ConsentGenerator - one of consumer. Get a message from "grant_other_document" queue and create pdf-document
6) GuaranteeLetterGenerator - one of consumer. Get a message from "grant_other_document" queue and create pdf-document
7) GrantApplicationGenerator - one of consumer. Get a message from "grant_other_document" queue and create pdf-document
8) GrantApplicationGenerator - one of consumer. Get a message from "grant_contracts" queue and create pdf-document

![Project structure2](/materials/structure2.png)

### About exchange:
In project implement two type of exchange: 
1) SOCIAL_ASSISTANCE_EXCHANGE - fanout type
2) GRANT_EXCHAGNE - topic type

## How run:
1) Open application.properties of 'consumer' applications and set path to directory where documents will save.
2) Run MQrabbit in docker: docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
3) Run StudentsDataProducer - it's configure exchange and queue of MQRabbit.
4) Run all consumer. 
5) Open http://localhost:8080 and fill web-form
6) Run directory with documents and check.