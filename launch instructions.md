### Инструкция по запуску:

1. **Установка и запуск apache kafka:**

   - Установите Java Development Kit (JDK) на ваш компьютер. Убедитесь, что переменная среды JAVA_HOME установлена правильно.
   - Скачайте и распакуйте Apache Kafka с официального сайта: [ссылка](https://kafka.apache.org/downloads).
   - Запустите ZooKeeper, запустив скрипт .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties.
   - Запустите Kafka, запустив скрипт .\bin\windows\kafka-server-start.bat .\config\server.properties.

2. **Запуск проекта**
   - Откройте терминал или командную строку на вашем компьютере и выполните команду git clone [<URL_репозитория>](https://github.com/Belov-Viktor-Ivanovich/Zuzex_Microservices),
   - Каждый микросервис запускается в ручную, начиная с Eureka-Services и заканчивая Gateway
   - Для описания в сервисах будет добавлена зависимость swagger
