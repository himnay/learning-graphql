# Learning GraphQL

Production-grade Spring Boot GraphQL demo — **Spring for GraphQL**, PostgreSQL, Flyway, ShedLock, Prometheus, Grafana, TestContainers, JUnit 5.

---

## Tech Stack

| Layer              | Technology                              |
|--------------------|-----------------------------------------|
| Language           | Java 25 (virtual threads)               |
| Framework          | Spring Boot 4.1.0                       |
| GraphQL            | Spring for GraphQL (spring-graphql)     |
| Database           | PostgreSQL 16                           |
| Migrations         | Flyway                                  |
| Distributed Locks  | ShedLock 7.7.0 (JDBC provider)          |
| Observability      | Micrometer + Prometheus + Grafana       |
| Testing            | JUnit 5, TestContainers                 |
| Build              | Maven 3.9+                              |

---

## Project Structure

```
learning-graphql/
├── docker-compose.yml              # PostgreSQL + Prometheus + Grafana
├── docker/
│   ├── prometheus/prometheus.yml
│   └── grafana/provisioning/
├── insomnia-collection.json        # API test collection
│
├── graphql-service1/               # GraphQL SERVER (port 8080)
│   └── src/main/
│       ├── java/com/org/graphql/
│       │   ├── controller/         # @QueryMapping, @MutationMapping, @SchemaMapping
│       │   ├── entity/             # JPA entities (Jakarta EE)
│       │   ├── enums/              # SubjectEnum
│       │   ├── exception/          # StudentNotFoundException, GraphQlExceptionHandler
│       │   ├── mapper/             # StudentMapper (Factory Method pattern)
│       │   ├── model/              # Java 25 records (DTOs + Input types)
│       │   ├── repository/         # Spring Data JPA
│       │   ├── scheduler/          # ShedLock scheduled tasks (Template Method)
│       │   ├── service/            # StudentService, AuthorService
│       │   └── strategy/           # SubjectFilterStrategy (Strategy pattern)
│       └── resources/
│           ├── graphql/schema.graphqls
│           └── db/migration/       # Flyway V1–V3
│
└── graphql-service2/               # GraphQL CLIENT (port 8081)
    └── src/main/
        ├── java/com/org/graphql/
        │   ├── client/             # HttpGraphQlClient-based StudentClient
        │   ├── config/             # GraphQLClientConfig
        │   └── controller/         # REST → GraphQL proxy (reactive)
        └── resources/
```

---

## Design Patterns (Gang of Four)

| Pattern         | Where Applied                                                       |
|-----------------|---------------------------------------------------------------------|
| Factory Method  | `StudentMapper.toDto()`, `SubjectFilterStrategy.of()`               |
| Template Method | `AbstractScheduler.executeScheduledTask()` → `performTask()`        |
| Strategy        | `SubjectFilterStrategy` — pluggable subject filtering               |
| Builder         | Lombok `@Builder` on entities; Java 25 record pattern on DTOs       |
| Singleton       | Spring `@Bean` singletons for all services, repositories, configs   |

---

## Quick Start

### 1. Start infrastructure

```bash
docker-compose up -d
```

| Service    | URL                                 |
|------------|-------------------------------------|
| PostgreSQL | `localhost:5432`                    |
| Prometheus | http://localhost:9091               |
| Grafana    | http://localhost:3001 (admin/admin) |

### 2. Run service1 (GraphQL server)

```bash
cd graphql-service1
mvn spring-boot:run
```

- GraphQL endpoint: http://localhost:8080/graphql
- GraphiQL IDE: http://localhost:8080/graphiql
- Actuator: http://localhost:8080/actuator

### 3. Run service2 (GraphQL client)

```bash
cd graphql-service2
mvn spring-boot:run
```

- REST API: http://localhost:8081/api/v1/students
- Actuator: http://localhost:8081/actuator

### 4. Run tests

```bash
mvn test
# Requires Docker running for TestContainers
```

---

## GraphQL API Reference

### Schema

```graphql
type Query {
    helloWorld: String
    names: [String!]!
    message: Message!
    fullName(firstName: String!, lastName: String!): String
    fullNameRequestObject(request: HelloWorldInput): String
    getStudent(id: ID): StudentDto
    authors: [Author]!
}

type Mutation {
    createStudent(student: StudentInput!): StudentDto
}
```

### Example Queries

**Get student with all fields:**
```graphql
{
    getStudent(id: "1") {
        id firstName lastName fullName email
        address { street city }
        subjects(subjectType: All) { subjectName marksObtained }
    }
}
```

**Get student with subject filter:**
```graphql
{
    getStudent(id: "2") {
        firstName
        subjects(subjectType: Java) { subjectName marksObtained }
    }
}
```

**Create student:**
```graphql
mutation CreateStudent($student: StudentInput!) {
    createStudent(student: $student) {
        id firstName lastName fullName email
        address { street city }
        subjects(subjectType: All) { subjectName marksObtained }
    }
}
```

Variables:
```json
{
    "student": {
        "firstName": "Alice",
        "lastName": "Walker",
        "email": "alice@example.com",
        "street": "Baker St",
        "city": "London",
        "subjects": [
            { "subjectName": "Java", "marksObtained": 95.0 }
        ]
    }
}
```

**Get authors with posts:**
```graphql
{
    authors { id name email posts { id title description category } }
}
```

---

## REST API (Service 2 — GraphQL Client)

| Method | Path                              | Description                                   |
|--------|-----------------------------------|-----------------------------------------------|
| GET    | `/api/v1/students/{id}`           | Fetch student via GraphQL client              |
| GET    | `/api/v1/students/{id}/filter`    | Fetch with subject filter `?subjectType=Java` |
| POST   | `/api/v1/students`                | Create student via GraphQL mutation           |

---

## ShedLock Distributed Scheduling

`StudentReportScheduler` runs every 5 minutes (configurable) and is protected by ShedLock to prevent concurrent execution across multiple instances.

```yaml
shedlock:
  default-lock-at-most-for: 30s
  default-lock-at-least-for: 10s
  student-report:
    cron: "0 */5 * * * *"
```

The shedlock table is created by Flyway migration `V3__create_shedlock_table.sql`.

---

## Observability

- **Actuator**: `/actuator/health`, `/actuator/info`, `/actuator/metrics`, `/actuator/prometheus`
- **Prometheus**: Scrapes both services at http://localhost:9091
- **Grafana**: Pre-configured Prometheus datasource at http://localhost:3001

Import a Spring Boot dashboard (e.g. Grafana dashboard ID **19004**) to get JVM, HTTP, and Hikari metrics.
