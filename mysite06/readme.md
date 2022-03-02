___
# MySite06

- **_SpringBoot Config_**

```
📦mysite06
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂poscoict
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mysite
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂aspect
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MeasuerExecutionTimeAspect.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GuestbookController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MainController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜JsonResult.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FileUploadException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepositoryException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SiteInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GuestbookRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SiteRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Auth.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthUserHandlerMethodArgumentResolver.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LogoutInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FileUploadService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GuestbookService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SiteService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardVo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GuestbookVo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SiteVo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserVo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BootInitializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MySiteApplication.java
 ┃ ┃ ┣ 📂resources
 ┃ ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂poscoict
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mysite
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂assets
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜admin.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜main.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜board.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜guestbook.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜main.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜mysite.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜user.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂images
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜check.png
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜recycle.png
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜reply.png
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜test3.png
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂jquery
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜jquery-1.9.0.js
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.properties
 ┃ ┃ ┃ ┣ 📂messages
 ┃ ┃ ┃ ┃ ┗ 📜messages_ko.properties
 ┃ ┃ ┃ ┣ 📂mybatis
 ┃ ┃ ┃ ┃ ┣ 📂mappers
 ┃ ┃ ┃ ┃ ┃ ┣ 📜board.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜guestbook.xml
 ┃ ┃ ┃ ┃ ┃ ┣ 📜site.xml
 ┃ ┃ ┃ ┃ ┃ ┗ 📜user.xml
 ┃ ┃ ┃ ┃ ┗ 📜configuration.xml
 ┃ ┃ ┃ ┣ 📜application.yml
 ┃ ┃ ┃ ┣ 📜logback.xml
 ┃ ┃ ┃ ┗ 📜__application.properties
 ┃ ┃ ┗ 📂webapp
 ┃ ┃ ┃ ┗ 📂WEB-INF
 ┃ ┃ ┃ ┃ ┗ 📂views
 ┃ ┃ ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂include
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜header.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜navigation.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜board.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜guestbook.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜main.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜user.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜list.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜modify.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜view.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜write.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜404.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜500.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜exception.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂guestbook
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜delete.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜index.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂includes
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜footer.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜header.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜navigation.jsp
 ┃ ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜index.jsp
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜join.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜joinsuccess.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜login.jsp
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜update.jsp
 ┣ 📜pom.xml
 ┗ 📜readme.md
```
 
1. pom.xml
	- jackson dependency 추가
2. JsonResult Class 추가
3. GlobalExceptionHandler 수정
4. UserRepository 수정
	- Mybatis Mapper user.xml 수정
5. UserService 수정
6. UserController(API) 추가
7. views/user/join.jsp 수정
8. assets/images/check.png 추가
9. MessageConverter 설정 (Java Config, ..config.WebConfig.java)