___
## Spring Setting

1. maven project(module) 생성(war)
2. web.xml 추가 (Java EE Tools > Generate Deployment Descriptor Stub)
3. Target Runtime 설정
4. pom.xml 설정
5. web.xml 설정
	1) /webapp/WEB-INF/applicationContext.xml (Root Application Context)
	2) /webapp/WEB-INF/spring-servlet.xml (Web Application Context)
6. package
	com.poscoict.mysite.controller
	com.poscoict.mysite.service
	com.poscoict.mysite.repository
	com.poscoict.mysite.vo
7.  /webapp/assets/* 정리
	/webapp/WEB-INF/views/*/*.jsp 정리
8. Controller, Service, Repository 구현