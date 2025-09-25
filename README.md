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
- `dev` → Altair enabled, CORS enabled for local frontend development
- `prod` → Altair disabled, CORS disabled by default

```bash
java -jar target/dgs-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## 🌍 CORS Configuration
In development mode, you often need to allow requests from a frontend running on another port (e.g., React on `localhost:3000`).  
We provide a simple `CorsConfig` class under `com.example.dgs.demo.config` which is only active in the `dev` profile.

```java
package com.example.dgs.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS configuration for local development.
 *
 * This configuration allows requests from a local frontend
 * (e.g., React running on http://localhost:3000) to reach the
 * GraphQL endpoint (/graphql). It is only active when the
 * application runs with the "dev" profile to avoid exposing
 * CORS settings in production.
 */
@Configuration
@Profile("dev")
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphql")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

Run the app in `dev` profile:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🛠️ Troubleshooting
- **404 at /altair** → make sure `graphql-spring-boot-autoconfigure` is included with proper exclusions and `graphql.altair.enabled=true` is set.
- **Unmapped fields: [Query._service]** → harmless; can be removed by disabling Federation (`dgs.graphql.federation.enabled=false`).
- **CORS errors** (when calling from an external frontend) → enable the `dev` profile to activate the `CorsConfig`.

## 📚 Useful Links
- [Netflix DGS Framework](https://netflix.github.io/dgs/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Altair GraphQL Client](https://altairgraphql.dev/)
