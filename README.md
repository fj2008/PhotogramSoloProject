# 포토그램 - 인스타그램 클론 코딩

### STS 툴에 세팅하기 - 플러그인 설정
- https://blog.naver.com/getinthere/222322821611

### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- oauth2-client


## 이미지 업로드 유효성검사하기
```
이미지 업로드시에 이미지는 꼭 들어와야하는 데이터이기때문에 유효성검사를 해야한다.
이때 @NOTBLANK가 MultipartFile타입에는 적용이 되지 않기때문에
```

## yml에 키값 정의해서 벨류값 가져오기
```
imageService에서 이미지경로를 입력하는 부분에 전역번수로 yml에 정의돼있는 키값을 가져왔다.
```
##UUID란?

```
UUID란 
네트워크 상에서 고유성이 보장되는 ID를 만들기위한 표준 규약
범용 고유 식별자. 유일성이 보장이 된다.
```


### 현제 프로젝트에서는 요청하는 dto는 Req를 이름에 넣지않았습니다.

### 공통응답 DTO(CMResDto),공통응답Script
```
유효성검사 때에 응답을 하는 방법을 두가지 방식으로 분기시켰다.
공통응답 DTO는 개발자가 받기좋은 데이터를 리턴해주고
공통응답 Script는 사용자가 사용하기 편한 방식을 제공해준다.
CMRespDto, Script 비교
1. 클라이언트에게 응답할 때는 Script가 좋다.
2. Ajax통신 - CMRespDto가 좋다
3. Android 통신 - CMRespDto
```

###로그인 SpringSecurity동작원리
```
Security에서는 로그인시에 컨트롤러를 따로 만드는것이 아닌 Security에서 관리하게 됩니다..

시큐리티 설정파일
/auth/siginin이라는 주소가 Post방식으로 요청이될때 httpbody에 username,password를 들고온다.
그럼 원래라면은 이 body데이터를 낚아체서 IOC컨테이너에 등록돼 있는 userDetailsService를 사용해서 로그인을 진행합니다.
하지만 현제 프로젝트에 auth패키지에있는 PrincipalDetailsService클래스가userDetailsService를 implment가
돼있기때문에 IOC입장에서는 PrincipalDetailsService가 userDetailsService와 타입이 똑같기 때문에 IOC에 등록할때
PrincipalDetailsService를 등록해버립니다.

- Spring Security는 기본적으로 /login,/logout 을 가지고있습니다.


```

## AuthController @Service사용하기
```
AuthController에서 AuthService클래스파일을 di(의존성주입)을 시킨다.
스프링이 IOC컨테이너에 AuthController객체를 생성해서 메모리로드를 할때 조건이 생성자 실행이다.
스프링입장에서 생성자를 실행하려하는데 생성자 매개변수로AuthService가 들어가 있으면 AuthService를 
주입해 주기 위해 IOC컨테이너를 찾아다닌다 (없으면 생성자 실행이 안돼서 오류남).
이때 @Service를 사용해서 AuthService를 ioc에 등록해주고 의존성 주입을 해주는 방식으로 설계했습니다.
 

```
```xml
<!-- 시큐리티 태그 라이브러리 -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>9.0.43</version>
</dependency>

<!-- JSTL -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>
```

### 데이터베이스

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database photogram;
```

### yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/costa?serverTimezone=Asia/Seoul
    username: costa
    password: costa1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

file:
  path: C:/src/springbootwork-sts/upload/
```

### 태그라이브러리

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
```
