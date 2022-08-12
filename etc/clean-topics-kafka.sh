#/usr/bin/sh

$KAFKA_HOME/bin/kafka-topics.sh --delete --topic heart --bootstrap-server localhost:9092

$KAFKA_HOME/bin/kafka-topics.sh --delete --topic glucose --bootstrap-server localhost:9092

$KAFKA_HOME/bin/kafka-topics.sh --delete --topic alert --bootstrap-server localhost:9092

