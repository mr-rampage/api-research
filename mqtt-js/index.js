import * as mqtt from 'mqtt';

const client = mqtt.connect('mqtt://localhost:1883', { clientId: 'nodejs' });
const topic = 'sensors';

client.on('connect', () => {
  client.subscribe(topic, function(e) {
    console.log(`${e ? 'failed to' : 'successfully'} subscribed to ${topic}`);

    client.on('message', function(topic, message) {
      console.log(`Received message on ${topic}`, message.toString());
    });
  });
});
