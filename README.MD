# kafka-example
This project serves as an illustration of how a message Production and Consumption solution using Kafka infrastructure can operate.

## Components

Currently, the solution comprises two main parts:

### plataforma kafka
Kafka is containerized using the conduktor/kafka-stack-docker-compose project. This setup facilitates the availability of the 
environment in a straightforward manner. The YAML files from this project have been copied into the kafka directory within the kafka-example project.

The original repository of conductor/kafka-stack-docker-compose can be found [here](https://github.com/conduktor/kafka-stack-docker-compose?tab=readme-ov-file).

### kafka-example
This encompasses a set of classes responsible for message production and consumption.

# Compilation
The project is built using Maven, JDK 21, and spring-boot 3.2.4.
```console
mvn clean install
```
This command compiles the project and generates a JAR file.

# Excecution
## kafka
### Windows Only
Check if the iphlpsvc service on Windows is running. If it is, stop it as it occupies port 9092 of Kafka.

```console
net stop iphlpsvc
```
> It's important to note that there might be other processes on Windows/Linux/Mac already utilizing port 9092. Please verify and terminate such processes if necessary.

1. Navigate to the kafka folder and execute the following command to start the environment with Zookeeper and a Kafka instance:
```console
docker compose -f zk-single-kafka-single.yml up
```

### Topic Creation
> You'll need the Kafka installation to run the commands below.
> If for any reason you forgot to check about processess running at 9092 the kafka docker compose will run but the topic creation and listing part here will fail.

Linux or Mac:
```console
kafka-topics.sh --create --topic example-cgi --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
```
Windows:
```console
kafka-topics.bat --create --topic example-cgi --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
```
The expected output should be as follows:

```
kafka-topics.bat --create --topic example-cgi --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092
Created topic example-cgi.

# kafka-topics.bat --list --bootstrap-server localhost:9092
example-cgi

# kafka-topics.bat --describe --bootstrap-server localhost:9092
Topic: example-cgi      TopicId: IPRBwZRBSISCMc76-Lekwg PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: example-cgi      Partition: 0    Leader: 1       Replicas: 1     Isr: 1
```
Now, the topic is created and ready to receive messages.

## Message Producer
Simply run the main class of the project pt/com/daniel/kafka/ProducerApp.java. It will start a Spring Boot application listening on port 8080, awaiting messages from the API of this project.

## Message Consumer
Just execute the main class of the project pt/com/daniel/kafka/ConsumerApp.java. It will start a Spring Boot application listening on port 18080, awaiting messages from the API of this project.

## Envio de mensagens
You can utilize any REST client and make a POST request to localhost:8080.

URL: http://localhost:8080/api/v1/messages

JSON BODY

```
{
    "message":"Minha mensagem"
}
```