___
# MySite04

- **_XML-Config To Java-Config_**

```
📦mysite04
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂poscoict
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DBConfig.java           ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MyBatisConfig.java      ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂web
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FileUploadConfig.java   ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MessageConfig.java      ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MvcConfig.java          ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java     ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mysite
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂aspect
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AppConfig.java          ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java          ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂vo
 ┃ ┃ ┣ 📂resources
 ┃ ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂poscoict
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mysite
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂app
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mybatis
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mappers
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜configuration.xml
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜jdbc.properties       ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂web
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂messages
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜fileupload.properties ┄┄┄┄┄┄┄┄ 👶 new !
 ┃ ┃ ┃ ┗ 📜logback.xml
 ┃ ┃ ┗ 📂webapp
 ┃ ┃ ┃ ┣ 📂assets
 ┃ ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┣ 📂images
 ┃ ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┗ 📂WEB-INF
 ┃ ┃ ┃ ┃ ┣ 📂views
 ┃ ┃ ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂include
 ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┃ ┣ 📂guestbook
 ┃ ┃ ┃ ┃ ┃ ┣ 📂includes
 ┃ ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┗ 📜web.xml                       ┄┄┄┄┄┄┄┄ 🔄 update !
 ┣ 📜pom.xml
 ┗ 📜readme.md
 ```