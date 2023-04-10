## Порядок действий для запуска тестов ##
1. Запустить Docker Desktop
2. Запустить IntelliJ IDEA
3. Клонировать репозиторий через меню IDEA file - new - project from version control, используя URL: [https://github.com/zhuravlini/zhu-diploma_1](https://github.com/zhuravlini/zhu-diploma_1)
4. В терминале IDEA набрать команду docker-compose up

5. В соседней вкладке запустить само приложение командой
- для БД Postgresql

        java -Dserver.port=8090 -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar

 - для БД MysSQL
 
        java -Dserver.port=8090 -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
       
6. Запустить тесты командой
- для БД Postgresql

        gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app

 - для БД MysSQL
 
        gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
      
      
7. Открыть отчет о прохождении тестов командой gradlew allureServe




### [Отчет по итогам прохождения автотестов](https://github.com/zhuravlini/zhu-diploma_1/blob/main/Report.md) ###
### [Отчет по итогам автоматизации](https://github.com/zhuravlini/zhu-diploma_1/blob/main/Summary.md) ###
