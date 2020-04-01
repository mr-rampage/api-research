import { makeAugmentedSchema } from 'neo4j-graphql-js';
import { typeDefs } from './graphql-schema';
import neo4j from 'neo4j-driver';
import { ApolloServer } from 'apollo-server';

const schema = makeAugmentedSchema({ typeDefs });

const driver = neo4j.driver(
  'bolt://localhost:7687',
  neo4j.auth.basic('neo4j', 'letmein')
);

const server = new ApolloServer({ schema, context: { driver }});

server.listen(3003, '0.0.0.0')
  .then(({url}) => {
    console.info(`GraphQL API ready at ${url}`);
  });
