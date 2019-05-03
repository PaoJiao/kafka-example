# kafka-example
spring boot &amp; kafka example

## prepare

1. start zookeeper

`zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties`

2. start kafka

`kafka-server-start /usr/local/etc/kafka/server.properties`

test kafka using console producer & consumer

- start console producer: `kafka-console-producer --broker-list localhost:9092 --topic example-kafka-topic`

- start console consumer: `kafka-console-consumer --bootstrap-server localhost:9092 --topic example-kafka-topic --from-beginning`

3. run srping boot

## testing

1. test spring boot service if working

```sh
curl -X GET \
  http://127.0.0.1:8080/kafka/hello \
  -H 'cache-control: no-cache'
```

result: `{"code":200,"message":"OK"}`

2. send message to kafka

```sh
curl -X POST \
  http://127.0.0.1:8080/kafka/send \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"title": "kafka",
	"body": "hello"
}'
```

3. in IDEA, on console pane, see if consumer working.
