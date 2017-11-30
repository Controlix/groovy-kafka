import org.apache.kafka.clients.consumer.KafkaConsumer

String SERVER_HOST = "localhost:9092"
String GROUP_ID = "boot"

// Initialize Kafka connection
def props = [:]
props.put("bootstrap.servers", SERVER_HOST)
props.put("group.id", GROUP_ID);
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

println 'Starting...'

KafkaConsumer consumer = new KafkaConsumer(props)

assert consumer.listTopics()['boot.t'] != null

consumer.subscribe(['boot.t'])
consumer.seekToBeginning([])

while (true) {
    consumer.poll(10000L).each { println "Read message " + it }
}

println 'Ending...'
