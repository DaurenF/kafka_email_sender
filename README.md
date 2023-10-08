# kafka_email_sender

## Description

Как поднять проект:
1. С помощью docker-compose поднять контейнеры с kafka и zookeeper, postgres (docker-compose up -d)
2. В application.yaml в значения [GOOGLE_USERNAME, GOOGLE_APP_PASSWORD, TARGET_EMAIL] вставить свои значения

Методы:
1. POST /message - в теле запроса передать xml вида:

```xml
<message>
    <message>Hello, World!</message>
    <sender>dauren@gmail.com</sender>
</message> 
```

Отравит сообщение на указанный email в TARGET_EMAIL


2. GET /message:
    1) При пустом параметре sender пришлет последние 10 сообщений
    2) При указанном параметре sender пришлет все сообщения от указанного отправителя 


Внутри проекта приложил постман коллекцию для вашего удобства :)