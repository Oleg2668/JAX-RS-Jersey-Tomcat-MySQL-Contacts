
ТЕХСТЕК

JDK
https://www.oracle.com/java/technologies/downloads/
(якщо встановлена, то Apache Tomcat має підтягнути встановлену)

Apache Tomcat
https://tomcat.apache.org/
(якщо Apache Tomcat вже встановлено, то можна скористатися встановленим)

JAX-RS
https://projects.eclipse.org/projects/ee4j.rest

Jersey
https://projects.eclipse.org/projects/ee4j.jersey/

Jackson
https://github.com/FasterXML/jackson

HK2
https://javaee.github.io/hk2/

MySQL
https://www.mysql.com/

Hibernate
https://hibernate.org/

Apache Maven WAR Plugin
https://maven.apache.org/plugins/maven-war-plugin/index.html

Project Lombok
https://projectlombok.org/


------------------------

JAVA ПРОЕКТ

(1) Налаштовуємо БД ( INFO/SQLs.sql ).
(2) Створюємо Maven-проект.
(3) Додаємо залежності ( pom.xml ).
(4) Формуємо та відповідно структуруємо
необхідний контент ( src/main ).

------------------------

РОЗГОРТАННЯ (ДЕПЛОЙ) ПРОЕКТУ

Запустимо Apache Tomcat.
Створемо секцію Git Bash.
В IDE внизу

Terminal >  <іконка-випадаючого-списку>  > Git Bash

В секції буде шлях до директорії поточного проекту

<your-base-path>/<your-project-name>

Переходимо до директорії, де розташовані файли запуску
та припинення роботи Apache Tomcat

$ cd <your-base-path>/apache-tomcat-<version>/bin

Виконуємо команду

$ ./startup.bat

УВАГА.
Запуск Apache Tomcat: startup.bat - для Windows,
startup.sh - для MacOS/Linux.
Припинення Apache Tomcat: shutdown.bat - для Windows,
shutdown.sh - для MacOS/Linux.

Окремо з'явиться інформаційне вікно,
де відображається інформація про роботу
Apache Tomcat та програми.

Можемо згорнути його.

В IDE відкриваємо бокову праворуч вкладку Maven.
Через меню вкладки відкриваємо вікно команд,
де послідовно знаходимо та подвійним кліком
запускаємо відповідні Maven-команди

mvn clean

mvn install

mvn war:war

В директорії проекту target , знаходимо файл
Your-Project-Name-1.0-SNAPSHOT.war та копіюємо його
у відповідну директорію Apache Tomcat, в файловій системі
комп'ютера

<your-base-path>/apache-tomcat-<version>/webapps

Через декілька секунд, в цій директорії, має з'явитися
папка Your-Project-Name-1.0-SNAPSHOT.
Apache Tomcat розархівував архівний файл проекту.

Тестуємо функціонал програми.

------------------------

РЕСУРСИ

https://github.com/eclipse-ee4j/jaxrs-api
https://jakarta.ee/specifications/platform/10/apidocs/jakarta/ws/rs/package-summary
https://jakarta.ee/specifications/platform/10/apidocs/jakarta/ws/rs/core/package-summary
https://maven.apache.org/plugins/maven-war-plugin/plugin-info.html
https://maven.apache.org/plugins/maven-war-plugin/usage.html
https://projectlombok.org/features/
https://www.baeldung.com/jax-rs-response
https://www.baeldung.com/jax-rs-spec-and-implementations
https://www.baeldung.com/jersey-request-parameters
https://www.baeldung.com/rest-api-jax-rs-vs-spring
https://www.baeldung.com/rest-versioning
https://www.baeldung.com/java-postman
https://www.baeldung.com/rest-http-put-vs-post
https://stackoverflow.com/questions/11552248/when-to-use-queryparam-vs-pathparam
https://www.baeldung.com/java-dto-pattern

