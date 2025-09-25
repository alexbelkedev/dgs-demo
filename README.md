# DGS Demo App

Eine kleine Demo-Anwendung mit **Spring Boot 3**, **Netflix DGS (GraphQL)** und **Altair UI**.

## 🚀 Features
- GraphQL Endpoint unter `/graphql`
- Beispiel-Queries und -Mutationen (`books`, `bookById`, `addBook`)
- Altair Client integriert unter `/altair` (nur im Dev-Profil aktiviert)

## 📦 Voraussetzungen
- Java 21
- Maven 3.9+

## ▶️ Starten
```bash
./mvnw spring-boot:run
```

Danach erreichbar:
- GraphQL Endpoint: [http://localhost:8080/graphql](http://localhost:8080/graphql)
- Altair UI: [http://localhost:8080/altair](http://localhost:8080/altair)

## 🔍 Beispiel-Queries

### Alle Bücher abfragen
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

### Ein Buch nach ID
```graphql
query {
  bookById(id: "123") {
    title
    author
  }
}
```

### Neues Buch hinzufügen
```graphql
mutation {
  addBook(input: {title: "Refactoring", author: "Martin Fowler", year: 1999}) {
    id
    title
  }
}
```

## ⚙️ Profiles
- `dev` → Altair aktiviert
- `prod` → Altair deaktiviert

```bash
java -jar target/dgs-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 🛠️ Troubleshooting
- **404 bei /altair** → sicherstellen, dass `graphql-spring-boot-autoconfigure` mit Exclusions eingebunden ist und `graphql.altair.enabled=true` gesetzt ist.
- **Unmapped fields: [Query._service]** → harmlos; kann durch Deaktivieren der Federation entfernt werden (`dgs.graphql.federation.enabled=false`).
- **CORS-Fehler** (bei externem Frontend) → CORS-Config für `/graphql` hinzufügen.

## 📚 Nützliche Links
- [Netflix DGS Framework](https://netflix.github.io/dgs/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Altair GraphQL Client](https://altairgraphql.dev/)
