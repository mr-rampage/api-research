import grpc from 'grpc';
import * as protoLoader from '@grpc/proto-loader';

const helloProto = grpc.loadPackageDefinition(createPackageDefinition(`${__dirname}/hello.proto`)).hello;
createHelloServer("127.0.0.1:50051", helloProto);
createHelloClient('127.0.0.1:8080', helloProto);

function sayHello(call, callback) {
  callback(null, {message: `Reply from Node.js ${call.request.name}`})
}

function createHelloServer(url, helloProto) {
  const server = new grpc.Server();
  server.addService(helloProto.Greeter.service, {sayHello});
  server.bind(url, grpc.ServerCredentials.createInsecure());
  console.log(`gRPC server is running on ${url}`);
  server.start();
}

function createHelloClient(url, helloProto) {
  const client = new helloProto.Greeter(url, grpc.credentials.createInsecure());
  client.sayHello({name: 'Node.js'}, function(err, response) {
    console.log(response, err);
  });
}

function createPackageDefinition(file) {
  return protoLoader.loadSync(
    file,
    {keepCase: true,
      longs: String,
      enums: String,
      defaults: true,
      oneofs: true
    });
}
