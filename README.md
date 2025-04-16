### [EN](#EN) | [RU](#RU)

# <a name="RU"></a>Требования к запуску проекта
 - Создать базу данных.
 - Необходимо заполнить файл hibernate.properties в src.main.resources для доступа к БД:
   - **Адрес БД** hibernate.connection.url: \
           Пример: hibernate.connection.url = jdbc:postgresql://localhost:5432/Users
   - **Имя пользователя БД** hibernate.connection.username
   - **Пароль данного пользователя** hibernate.connection.password 
# Требования к проекту
Разработать консольное приложение(user-service) на Java, использующее Hibernate для взаимодействия с PostgreSQL, 
без использования Spring. Приложение должно поддерживать базовые операции CRUD (Create, Read, Update, Delete) 
над сущностью User.

Требования

- Использовать Hibernate в качестве ORM.
- База данных — PostgreSQL.
- Настроить Hibernate без Spring, используя hibernate.cfg.xml или properties-файл.
- Реализовать CRUD-операции для сущности User (создание, чтение, обновление, удаление), которая состоит из полей: id, name, email, age, created_at.
- Использовать консольный интерфейс для взаимодействия с пользователем.
- Использовать Maven для управления зависимостями.
- Настроить логирование.
- Настроить транзакционность для операций с базой данных.
- Использовать DAO-паттерн для отделения логики работы с БД.
- Обработать возможные исключения, связанные с Hibernate и PostgreSQL

# <a name="EN"></a>Requirements for project to run
 - Create DB.
 - Fill out missing parts in hibernate.properties located in src.main.resources to access your DB:
   - Location of your created DB hibernate.connection.url\
   Example: hibernate.connection.url = jdbc:postgresql://localhost:5432/Users
   - DB User's name hibernate.connection.username
   - DB User's password hibernate.connection.password 

