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

## 🛠️ Технологический стек

| Компонент          | Версия   | Назначение                     |
|--------------------|----------|--------------------------------|
| Spring Boot        | 3.4.1    | Бэкенд-фреймворк              |
| jOOQ               | 3.19.0   | Генерация SQL-классов         |
| Liquibase          | 4.26.0   | Миграции базы данных          |
| PostgreSQL         | 15       | Основная СУБД                 |
| Testcontainers     | 1.20.5   | Интеграционное тестирование   |
| SpringDoc OpenAPI  | 2.2.0    | Документация API              |

## 📂 Структура проекта
```
При собрке проекта формируются:
build/
├── generated/
│   ├── source/
│   │   └── jooq/  # Автосгенерированные jOOQ классы, согласно структуры таблиц БД
├── reports/
│   ├── jacoco/
│   │   └── test/  № JaСoСo отчет о покрытии

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
│       └── application.yml  # Конфигурация при разработке
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

## 🔄 CI Пайплайн

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
```
name: CI Pipeline with Gradle
on:
  push:
    branches: [feature/*, test/*, hotfix/*]
  pull_request:
    branches: [dev]
```

## 🚀 Быстрый старт

### Предварительные требования:
1. **Java 17+**
2. **PostgreSQL 14+** (локально или доступ к серверу БД)
3. **Gradle 8.11+** (используется wrapper из проекта)

### Настройка проекта:
**1. Скопируйте файл конфигурации:**
```
cp gradle.properties.example gradle.properties
```
**2. Укажите в файле gralde.properties свои креды**

### Для запуска в IntelliJ IDEA:

**1. Укажите в конфигурации запуска Main.java:**
```
VM options: -Dspring.profiles.active=dev
```
**2. Установите переменные окружения:**
```
DB_BASE=postgresql
DB_NAME=your_db_name
DB_PASSWORD=your_password
DB_PORT=5432
DB_SERVER=localhost
DB_USERNAME=your_user
```
### Сборка и запуск:

**1. Собрать проект:**

```
./gradlew build
```
**2. Запустить приложение:**

```
./gradlew bootRun
```

**После запуска документация API будет доступна:**
```
http://localhost:8080/swagger-ui.html
```

### Важно!

Перед запуском убедитесь, что PostgreSQL сервер запущен

## 🧪 Тестирование

**Типы тестов:**
```
unit/ - модульные тесты
integration/ - тесты с реальной БД
component/ - сквозные тесты
```

**Запуск:**
```
./gradlew test  # Все тесты
./gradlew test --tests "*IT"  # Только интеграционные
```

## 📌 Ключевые особенности

✓ CI/интеграция

✓ Покрытие кода 80%+ (JaCoCo)

✓ Автоматическая генерация jOOQ

✓ Уведомления в Telegram