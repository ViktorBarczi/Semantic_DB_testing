# Semantic DB Testing (Spring Boot)

A Spring Boot 2.7.18 application (Java 11) that demonstrates how to connect to and query multiple semantic databases — Blazegraph, GraphDB, and Stardog — via a simple REST API.

## Features

- REST endpoints for three different backends:
  - Blazegraph (SPARQL endpoint via Apache Jena)
  - GraphDB (SPARQL endpoint via Apache Jena)
  - Stardog (native Stardog Java client)
- Two types of queries for each backend:
  - **Basic** SPARQL SELECT
  - **Full-text search (FTS)** using backend-specific extensions
- Support for two dataset sizes (64MB and 321MB)
- Query execution time measurement

## Project Structure

```
src/main/java/BP/
├── SpringApp.java       # Application entry point
├── ControllerPart.java  # REST controller, defines API endpoints
├── ServicePart.java     # Service layer, runs queries and formats results
└── Utils.java           # Query builders and database URLs
pom.xml                  # Maven configuration
```

## Dependencies

- Spring Boot Starter, Spring Boot Starter Web, Stardog Java Client, Apache Jena ARQ, SLF4J Simple

## API Endpoints

Base path: `/testing`

**Basic queries**  
- `GET /testing/blazegraph/basic/{name}`  
- `GET /testing/graphdb/basic/{name}`  
- `GET /testing/stardog/basic/{name}`  

**Full-text search**  
- `GET /testing/blazegraph/fts/{name}`  
- `GET /testing/graphdb/fts/{name}`  
- `GET /testing/stardog/fts/{name}`  

**Large dataset versions** (append `/big`)  
- `GET /testing/blazegraph/basic/{name}/big`  
- `GET /testing/graphdb/basic/{name}/big`  
- `GET /testing/stardog/basic/{name}/big`  
- `GET /testing/blazegraph/fts/{name}/big`  
- `GET /testing/graphdb/fts/{name}/big`  
- `GET /testing/stardog/fts/{name}/big`  

## Configuration

Database URLs and credentials are currently stored in `Utils.java`.  
For production, move these to `application.properties` or environment variables.

Example:
```properties
blazegraph.url.64=http://localhost:9999/blazegraph/namespace/kb/sparql
blazegraph.url.321=http://localhost:9999/blazegraph/namespace/big/sparql
graphdb.url.64=http://localhost:7200/repositories/BP
graphdb.url.321=http://localhost:7200/repositories/GB
stardog.server=http://localhost:5820
stardog.db.64=BP
stardog.db.321=BIG
stardog.user=admin
stardog.pass=${STARDOG_PASSWORD}
```

## How It Works

- **ControllerPart** maps HTTP GET requests to methods that call `ServicePart` with the right SPARQL query.
- **ServicePart** runs the query:
  - For Blazegraph and GraphDB, uses Apache Jena's HTTP SPARQL service.
  - For Stardog, uses the Stardog Java client.
- **Utils** returns the correct endpoint URL and builds the SPARQL query string.

## Build and Run

```bash
mvn clean package
mvn spring-boot:run
# or
java -jar target/semantic-db-testing-0.0.1-SNAPSHOT.jar
```

Default port: `8080`.

## Example Request

```bash
curl "http://localhost:8080/testing/blazegraph/basic/ExampleName"
```

## License

MIT or your preferred license.
