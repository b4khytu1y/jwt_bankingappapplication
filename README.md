# Banking App

**Banking App** — это простое банковское приложение, созданное с использованием Java и Spring Boot. Оно предоставляет базовую функциональность для управления банковскими операциями, такими как регистрация пользователей, создание и управление банковскими счетами, аутентификация через JWT токены, а также операции пополнения и снятия средств. Приложение легко интегрируется с различными клиентскими интерфейсами и поддерживает современную аутентификацию для безопасного доступа.

## Особенности
- 📋 **Регистрация пользователей**: Простая система регистрации с проверкой уникальности имени пользователя.
- 🔐 **JWT аутентификация**: Защита API через токены безопасности (JWT) для безопасной работы с ресурсами.
- 🏦 **Создание банковских счетов**: Пользователи могут создавать различные типы банковских счетов, такие как сберегательные (SAVINGS) и расчетные (CHECKING).
- 💸 **Управление банковскими счетами**: Операции пополнения и снятия средств с поддержкой проверок на баланс.
- 🔒 **Защищённые маршруты**: Все критичные операции требуют аутентификации через JWT.
- 📄 **Swagger UI**: Легкий доступ к документации и возможность тестирования запросов через интерфейс Swagger.

## Технологии
Приложение построено с использованием современных технологий для обеспечения безопасности и производительности:

- **Java 17** — современный язык программирования, обеспечивающий высокую производительность и поддержку новых функций.
- **Spring Boot 3.3.4** — фреймворк для создания веб-приложений с минимальной настройкой.
- **Spring Security** — управление безопасностью приложения с поддержкой аутентификации через JWT.
- **Spring Data JPA** — управление базой данных на основе ORM (Object-Relational Mapping).
- **MySQL** — основная база данных для хранения информации о пользователях и счетах.
- **Swagger/OpenAPI** — документация и интерфейс для тестирования API.

## Установка и запуск

### Требования:
- **Java 17** или новее.
- **Maven** для управления зависимостями.
- **MySQL** как база данных.

### Шаги по установке:

1. **Клонирование репозитория**:
   
   Сначала клонируйте репозиторий с GitHub на ваш локальный компьютер:
   ```bash
   git clone https://github.com/your-username/bankingapp.git
   cd bankingapp
   
2. **Настройка базы данных (MySQL или H2)**:

   - **MySQL**:
     1. Создайте базу данных `banking_app`, выполнив следующий SQL-запрос в вашей MySQL базе данных:
        ```sql
        CREATE DATABASE banking_app;
        ```
     2. Откройте файл `src/main/resources/application.properties` и настройте параметры подключения для MySQL:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/banking_app?useSSL=false&serverTimezone=UTC
        spring.datasource.username=your-username
        spring.datasource.password=your-password
        spring.jpa.hibernate.ddl-auto=update
        ```
3. **Установите зависимости Maven**:

   Для установки всех необходимых зависимостей выполните следующую команду в корневой директории проекта:
   ```bash
   mvn clean install
   ```
4. **Запустите приложение:**:

Для запуска приложения выполните следующую команду:
   ```bash
  mvn spring-boot:run
```
**Cкриншоты:**
1. Пример интерфейса Swagger:
![2](https://github.com/user-attachments/assets/fc25465a-1be2-43c8-8edc-e2094a0aed1d)

2. Пример получения JWT ключа:
![1](https://github.com/user-attachments/assets/7cced3d7-ce5b-4fdb-b1ac-952138b30ab1)


### Swagger UI

Документация API доступна через Swagger UI. Вы можете получить доступ по следующему URL:
```bash
http://localhost:8080/swagger-ui.html
```
Swagger UI позволяет вам просматривать доступные API и выполнять запросы прямо из браузера.


