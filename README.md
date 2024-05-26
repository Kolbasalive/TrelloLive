# TrelloLive Project
## Описание проекта
TrelloLive - это система управления задачами, вдохновленная функциональностью Trello. 
Проект разработан с использованием Java и Spring Boot для серверной части и PostgreSQL в качестве базы данных. 
Он предназначен для создания, управления и отслеживания задач в рамках досок и спринтов, поддерживая назначение пользователей и тегов к задачам.

## Функциональность
* Создание, удаление и обновление задач
* Добавление и удаление пользователей и тегов к задачам
* Управление досками и спринтами
* Валидация входных данных с использованием Jakarta Validation
* Логирование действий пользователей и системы
## Структура проекта
Проект состоит из нескольких основных компонентов:

1. Models
   * Task
   * Users
   * Tag
   * Board
   * Перечисления: Status, Role

3. Repositories
   * TaskRepository
   * UserRepository
   * TagRepository
   * BoardRepository

4. Services
   * TaskService
   * UserService
   * TagService
   * BoardService

5. Controllers
   * TaskController
   * UserController
   * TagController
   * BoardController

6. Mappers
   * Ручные мапперы для преобразования DTO в модели и обратно.
## Миграции базы данных
Проект использует Flyway для управления миграциями базы данных. Чтобы запустить миграции, выполните следующую команду:
`./gradlew flywayMigrate`
## Примеры использования API
### Создание пользователя
URL: /trello/user/post

Метод: POST

Тело запроса:
```
{
    "name": "Maksim Karp",
    "email": "karp@yandex.ru",
    "password": "password123",
    "role": "MEMBER"
}
```
### Получение всех пользователей
URL: /trello/user/get

Метод: GET

Ответ:
```
[
    {
        "userId": "6bdfc589-3bb3-41ed-a87d-3d95ba684d7e",
        "name": "Maksim Karp",
        "email": "karp@yandex.ru",
        "password": "password123",
        "role": "MEMBER"
    }
]
```
