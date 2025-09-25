# DGS Demo App

A small demo application using **Spring Boot 3**, **Netflix DGS (GraphQL)**, and the **Altair UI**.

## 🚀 Features
- GraphQL endpoint available at `/graphql`
- Sample queries and mutations (`books`, `bookById`, `addBook`)
- Altair client integrated at `/altair` (enabled only in the `dev` profile)

## 📦 Prerequisites
- Java 21
- Maven 3.9+

## ▶️ Run the App
```bash
./mvnw spring-boot:run
```

Once started:
- GraphQL endpoint: [http://localhost:8080/graphql](http://localhost:8080/graphql)
- Altair UI: [http://localhost:8080/altair](http://localhost:8080/altair)

## 🔍 Example Queries

### Get all books
```graphql
query {
  books {
    id
    title
    author
    year
  }
}
```

### Get a book by ID
```graphql
query {
  bookById(id: "123") {
    title
    author
  }
}
```

### Add a new book
```graphql
mutation {
  addBook(input: {title: "Refactoring", author: "Martin Fowler", year: 1999}) {
    id
    title
  }
}
```

## ⚙️ Profiles
- `dev` → Altair enabled
- `prod` → Altair disabled

```bash
java -jar target/dgs-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 🛠️ Troubleshooting
- **404 at /altair** → make sure `graphql-spring-boot-autoconfigure` is included with proper exclusions and `graphql.altair.enabled=true` is set.
- **Unmapped fields: [Query._service]** → harmless; can be removed by disabling Federation (`dgs.graphql.federation.enabled=false`).
- **CORS errors** (when calling from an external frontend) → add a CORS configuration for `/graphql`.

## 📚 Useful Links
- [Netflix DGS Framework](https://netflix.github.io/dgs/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Altair GraphQL Client](https://altairgraphql.dev/)
