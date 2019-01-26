1) переходим в папку проекта

2) Создаем Docker образ с помощью Gradle:
./gradlew build docker

4) Запускаем контейнер:
docker run -p 80:80 -t app

4) Отправляем POST запрос по ссылке localhost/validate:
Пример запроса /pics/post.png
