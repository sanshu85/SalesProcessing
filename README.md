This Application processes the Sales Notification Messages.

Assumption is that the Third party will be sending the messages using REST api to this application and internally the messages will be sent to queue.In ideal world we would be using message brokers like RabbitMQ,ActiveMQ but since the purpose of this test is to test the processing logic of the sales,hence we are using simple LinkedList as queue to demonstrate the sales processing application implementation

This application uses an in memory database
https://github.com/sanshu85/SalesProcessing.git

Currently this application creates sample 50 records for testing and report generation.

When you run this application,you can see the reports after every 10th record and an adjustment report after 50 records.

You can run this application in any IDE like eclipse and Intellij IDEA Using Run as Application option

Or

You can run the jar as below 

java -jar salesprocessing-0.0.1.jar