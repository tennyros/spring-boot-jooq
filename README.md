# Spring Boot + jOOQ + Liquibase Integration

![CI Status](https://github.com/tennyros/spring-boot-jooq/workflows/CI%20Pipeline%20with%20Gradle/badge.svg)
![Coverage](https://github.com/tennyros/spring-boot-jooq/raw/coverage-badge/.github/badges/jacoco.svg)
![Java 17](https://img.shields.io/badge/Java-17-blue)
![Spring Boot 3.4.1](https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen)
![jOOQ 3.19](https://img.shields.io/badge/jOOQ-3.19-orange)
![Liquibase 4.26](https://img.shields.io/badge/Liquibase-4.26-lightblue)

- **Spring Boot 3** - Core framework
- **jOOQ** - Type-safe SQL queries
- **Liquibase** - Database migrations
- **CI/CD** - Automated pipeline

## Technology Stack

| Component          | Version   | Purpose                        |
|--------------------|----------|--------------------------------|
| Spring Boot        | 3.4.1    | Backend framework             |
| jOOQ               | 3.19.0   | SQL class generation          |
| Liquibase          | 4.26.0   | Database version control      |
| PostgreSQL         | 15       | Primary database              |
| Testcontainers     | 1.20.5   | Integration testing           |
| SpringDoc OpenAPI  | 2.2.0    | API documentation             |

## Project Structure

```text
Build artifacts:
build/
├── generated/
│   ├── source/
│   │   └── jooq/  # Auto-generated jOOQ classes
├── reports/
│   ├── jacoco/
│   │   └── test/  # JaCoCo coverage report

Source code structure (dev branch):
README.md          # English documentation
README_RUS.md      # Russian documentation
.github/           # CI configuration 
src/
├── main/
│   ├── java/
│   │   └── com/github/tennyros/
│   │       ├── config/      # Configuration classes
│   │       ├── controller/  # REST endpoints
│   │       ├── dto/         # Data transfer objects
│   │       ├── repository/  # Data access (jOOQ)
│   │       └── service/     # Business logic
│   └── resources/
│       ├── db/changelog/    # Liquibase migrations
│       ├── application.yml  # Main configuration
│       └── application-dev.yml  # Dev profile config
├── test/
│   ├── java/
│   │    └── com/github/tennyros/
│   │       ├── integration/ # Integration tests
│   │       ├── component/   # Component tests
│   │       └── unit/        # Unit tests
│   └── resources/
│       ├── scripts          # SQL test scripts
│       └── application-test.yml # Test config
build.gradle        # Build configuration
gradle.properties   # Build variables
```

## CI Pipeline

**Triggers:**  
On pushes to feature branches and pull requests to dev

**Workflow stages:**

1. Start PostgreSQL 15 container
2. Cache Gradle dependencies
3. Execute tests (unit + integration)
4. Generate code coverage report
5. Update coverage badge
6. Send Telegram notification

**Configuration:**

```yaml
name: CI Pipeline with Gradle
on:
  push:
    branches: [feature/*, test/*, hotfix/*]
  pull_request:
    branches: [dev]
```

## Quick Start

### Prerequisites

1. **Java 17+**
2. **PostgreSQL 14+** (local or remote instance)
3. **Gradle 8.11+** (wrapper included)

### Project Setup

**1. Copy configuration file:**

```bash
cp gradle.properties.example gradle.properties
```

**2. Update credentials in gradle.properties.**

### IntelliJ IDEA Configuration

**1. In Main.java run configuration:**

```properties
VM options: -Dspring.profiles.active=dev
```

**2. Set environment variables:**

```text
DB_BASE=postgresql
DB_NAME=your_db_name
DB_PASSWORD=your_password
DB_PORT=5432
DB_SERVER=localhost
DB_USERNAME=your_user
```

### Build and Run

**1. Build project:**

```bash
./gradlew build
```

**2. Run PostgreSQL via Docker (optional, if you don’t run own DB):**

```bash
# Copy the example config (if not customized yet)  
cp docker-compose.example.yml docker-compose.yml  

# Start PostgreSQL in Docker  
docker-compose up -d spring-boot-jooq-db  
```

**3. Run application:**

```bash
./gradlew bootRun
```

**API documentation available at:**

```url
http://localhost:8080/swagger-ui.html
```

### Important

```text
If using docker-compose, PostgreSQL will be available at localhost:5438 (default credentials: postgres/root).
If you already have PostgreSQL, ensure application.yml/.env matches your DB settings.
```

## Testing

**Test types:**

```text
unit/ - Unit tests
integration/ - Tests with real database
component/ - End-to-end tests
```

**Execution:**

```bash
./gradlew test           # All tests
./gradlew test --tests "*IT"  # Integration tests only
```

## Key Features

✓ CI integration

✓ 80%+ code coverage (JaCoCo)

✓ Automatic jOOQ generation

✓ Telegram notifications
