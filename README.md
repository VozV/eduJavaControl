# eduJavaControl

Входное задание:
1) В реляционной БД (взять любую) создать таблицу
    Students – студенты
    ID | NAME | passport
2) Создать CRUD-API. Предусмотреть проверку на уникальность паспорта.
## Сборка
App:
```
mnv compile
```
DB Script:
```
db/db_dev.sql
```

## API
Student list:
```
Request:
GET api/v1/student
```
```
Response:
[
    {
        "id": {id1},
        "name": "studentName1",
        "passport": "studentPassport2"
    },
    {
        "id": {id2},
        "name": "studentName2",
        "passport": "studentPassport2"
    }
    ...
]
```
Get by ID:
```
Request:
GET api/v1/student/{id}
```
```
Response:
{
    "id": {id},
    "name": "studentName",
    "passport": "studentPassport"
}
```
Create:
```
Request:
POST api/v1/student
{
	"name": "studentName",
	"passport": "studentPassport"
}
```
```
Response:
{
    "id": {id},
    "name": "studentName",
    "passport": "studentPassport"
}
```
Update:
```
Request:
PUT api/v1/student
{
    "id": {id},
    "name": "newStudentName",
    "passport": "newStudentPassport"
}
```
```
Response:
{
    "id": {id},
    "name": "newStudentName",
    "passport": "newStudentPassport"
}
```
Delete:
```
Request:
DELETE api/v1/student/{id}
```
```
Response:
HTTP Status 204 No Content
```
