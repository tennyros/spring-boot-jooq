# Spring Boot + jOOQ + Liquibase

![CI Status](https://github.com/tennyros/spring-boot-jooq/workflows/CI%20Pipeline%20with%20Gradle/badge.svg)
![Coverage](https://github.com/tennyros/spring-boot-jooq/raw/coverage-badge/.github/badges/jacoco.svg)
![Java 17](https://img.shields.io/badge/Java-17-blue)
![Spring Boot 3.4.1](https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen)
![jOOQ 3.19](https://img.shields.io/badge/jOOQ-3.19-orange)
![Liquibase 4.26](https://img.shields.io/badge/Liquibase-4.26-lightblue)

- **Spring Boot 3** - основной фреймворк
- **jOOQ** - типобезопасный SQL
- **Liquibase** - управление миграциями
- **CI** - пайплайн

## Технологический стек

| Компонент          | Версия   | Назначение                     |
|--------------------|----------|--------------------------------|
| Spring Boot        | 3.4.1    | Бэкенд-фреймворк              |
| jOOQ               | 3.19.0   | Генерация SQL-классов         |
| Liquibase          | 4.26.0   | Миграции базы данных          |
| PostgreSQL         | 15       | Основная СУБД                 |
| Testcontainers     | 1.20.5   | Интеграционное тестирование   |
| SpringDoc OpenAPI  | 2.2.0    | Документация API              |

## Структура проекта

```text
При собрке проекта формируются:
build/
├── generated/
│   ├── source/
│   │   └── jooq/  # Автосгенерированные jOOQ классы, согласно структуры таблиц БД
├── reports/
│   ├── jacoco/
│   │   └── test/  # JaСoСo отчет о покрытии

Структура кода, расположенного в репозитории (dev ветка):
README.md          # Английская версия документации
README_RUS.md      # Русская версия документации
.github/           # CI 
src/
├── main/
│   ├── java/
│   │   └── com/github/tennyros/
│   │       ├── config/      # Конфигурации
│   │       ├── controller/  # REST API
│   │       ├── dto/         # Data Transfer Objects
│   │       ├── repository/  # Доступ к данным (jOOQ)
│   │       └── service/     # Бизнес-логика
│   └── resources/
│       ├── db/changelog/    # Миграции Liquibase
│       ├── application.yml  # Общие настройки
│       └── application-dev.yml  # Конфигурация при разработке
├── test/
│   ├── java/
│   │    └── com/github/tennyros/
│   │       ├── integration/ # Интеграционные тесты
│   │       ├── component/   # Сквозные тесты
│   │        └── unit/       # Модульные тесты
│   └── resources/
│       ├── scripts          # SQL скрипты для тестов
│       └── application.yml  # Конфигурация при тестировании
build.gradle        # Конфигурация сборки
gradle.properties   # Настройки переменных для build.gradle
```

## CI Пайплайн

**Активация:**  
При пушах в feature-ветки и pull request'ах в dev

**Основные этапы:**

1. Запуск PostgreSQL 15 в контейнере
2. Кэширование Gradle-зависимостей
3. Запуск тестов (unit + integration)
4. Генерация отчета о покрытии кода
5. Обновление бейджа coverage
6. Уведомление в Telegram

**Конфигурация:**

```yaml
name: CI Pipeline with Gradle
on:
  push:
    branches: [feature/*, test/*, hotfix/*]
  pull_request:
    branches: [dev]
```

## Быстрый старт

### Предварительные требования

1. **Java 17+**
2. **PostgreSQL 14+** (локально или доступ к серверу БД)
3. **Gradle 8.11+** (используется wrapper из проекта)

### Настройка проекта

**1. Скопируйте файл конфигурации:**

```bash
cp gradle.properties.example gradle.properties
```

**2. Укажите в файле gralde.properties свои креды.**

### Для запуска в IntelliJ IDEA

**1. Укажите в конфигурации запуска Main.java:**

```properties
VM options: -Dspring.profiles.active=dev
```

**2. Установите переменные окружения:**

```text
DB_BASE=postgresql
DB_NAME=your_db_name
DB_PASSWORD=your_password
DB_PORT=5432
DB_SERVER=localhost
DB_USERNAME=your_user
```

### Сборка и запуск

**1. Собрать проект:**

```bash
./gradlew build
```

**2. Запуск PostgreSQL через Docker (опционально, если не запущена своя БД):**

```bash
# Копируем пример конфига (если ещё не настроен)
cp docker-compose.example.yml docker-compose.yml

# Запускаем PostgreSQL в Docker
docker-compose up -d spring-boot-jooq-db
```

**3. Запустить приложение:**

```bash
./gradlew bootRun
```

**После запуска документация API будет доступна:**

```url
http://localhost:8080/swagger-ui.html
```

### Важно

```text
Если вы используете docker-compose, PostgreSQL будет доступен на localhost:5433 (логин/пароль по умолчанию: postgres/root).
Если у вас уже есть PostgreSQL, убедитесь, что настройки в application.yml/.env соответствуют вашей БД.
```

## Тестирование

**Типы тестов:**

```text
unit/ - модульные тесты
integration/ - тесты с реальной БД
component/ - сквозные тесты
```

**Запуск:**

```bash
./gradlew test  # Все тесты
./gradlew test --tests "*IT"  # Только интеграционные
```

## Ключевые особенности

✓ CI интеграция

✓ Покрытие кода 80%+ (JaCoCo)

✓ Автоматическая генерация jOOQ

✓ Уведомления в Telegram
