# Geo Data Service

Geo Data Service — это Spring Boot приложение для управления геопространственными данными, включая работу с регионами, слоями, функциями, гексагонами H3, а также продуктами и пользователями.

---

## Описание проекта

Данный проект предоставляет REST API для хранения и обработки данных о географических регионах, слоях с геопризнаками, продуктах и пользователях. В основе лежит агрегация данных по гексагонам системы H3 с разбивкой по годам.

---

## Технологии

- Java 23
- Spring Boot 3.4.5
- Spring Data JPA (Hibernate)
- PostgreSQL
- Docker и Docker Compose
- Gradle 7.6
- Lombok
- H3 (Uber's Hexagonal Hierarchical Spatial Index)
- SpringDoc OpenAPI (Swagger UI)

---

## Структура проекта

- `entity` — JPA сущности
- `repository` — интерфейсы для работы с базой данных
- `service` — бизнес-логика приложения
- `mapper` — преобразование entity в DTO
- `model` — DTO и модели данных для клиента
- `controller` — (рекомендуется реализовать) REST контроллеры

---

## Быстрый старт

### Настройка окружения

Создайте .env файл или задайте переменные окружения:

```dotenv
DB_HOST=localhost
DB_PORT=5432
DB_NAME=postgres
DB_USERNAME=postgres
DB_PASSWORD=your_password
```

### Запуск через Docker Compose

Убедитесь, что Docker и Docker Compose установлены.

```bash
docker-compose up --build
```

Приложение будет доступно по адресу: http://localhost:8080

### Локальный запуск без Docker

Для запуска локально:

./gradlew bootJar
java -jar build/libs/geo-surf-spring-boot.jar
