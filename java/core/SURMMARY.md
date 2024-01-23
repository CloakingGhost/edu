## 3. Spring

### 3.1 Dependency Injection(DI) Container

> AppConfig

* 수동으로 스프링 빈을 등록
    * @Configuration, @Bean
* 객체를 생성하고 관리하면서 의존관계를 연결해준다
* DIP, OCP 위반을 해결하며 코드 수정을 하지 않아도 된다

## 4. Spring Container ona Spring Bean

* beanfind package

### 4.1 빈 조회

* 스프링 컨테이너에 담긴 빈을 조회 할 수 있다, 예제 참조
* ApplicationContext 클래스 조회, 환경변수, 등 확인 할 수 있는 기능이 많다
* AnnotationConfigApplicationContext 구현체로 활용된다

### 4.2 XML

> resources > appConfig.xml

* 스프링 부트의 사용으로 XML 사용 빈도가 줄긴 하였으나 레거시 프로젝트들은 XML 설정 파일을 사용한다.
* GenericXmlApplicationContext 구현체가 있다.

### 4.2

> beandefinition.BeanDefinition

* Annotation 혹의 XML을 사용해 등록한 빈의 메타 정보를 확인 할 수 있다.

## 5. Singleton

> singleton package

### 5.1 Spring Container and Singleton

* 단 하나의 인스턴스를 생성하는 디자인 패턴이다
* 스프링 컨테이너는 기본적으로 스프링 빈을 싱글톤으로 관리한다
* 다중 쓰레드 환경을 주의
    * 인스턴스의 공유필드가 수시로 바뀌기 때문에 리턴하여 변수로 받을 수 있게 설계해야한다
  > StatefulService, statelessService
* CGLIB(Code Generator Library) 사용으로 바이트코드를 조작하여 생성자 호출 시에도 싱글톤을 보장하도록 한다.

### 5.2 @Configuration

* @Configuration 없이 빈 등록시 싱글톤이 보장하지 않는다.

## 6. Component Scan

> AutoAppConfig

### 6.1 @ComponentScan

* 스프링 빈을 자동으로 등록시켜준다
* @Component가 붙어있는 클래스를 조회하여 등록한다
* 의존 주입 시 생성자 @Autowired 추가하면 된다
    * 생성자가 1개면 생략가능

### 6.2 탐색 위치

* @SpringBootApplication이 위치한 곳에 설정파일을 두는 것을 권장한다

### 6.3 컴포넌트 스캔 기본 대상

* @Controller: recognize as spring MVC controller
* @Service: not any working but for programmer
* @Repository: recognize as spring data access layer, turn Exception to data layer into Exception to spring
* @Configuration: recognize as spring setting information

### 6.4 Filter

__@ComponentScan의 옵션__

* includeFilters: 등록할 빈
    * includeFilters = @Filter(type = FilterType.ANNOTATION, classes =
      MyIncludeComponent.class
* excludeFilters: 등록에서 제외 할 빈
    * excludeFilters = @Filter(type = FilterType.ANNOTATION, classes =
      MyExcludeComponent.class

__Filter Type__

* ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
    * ex) org.example.SomeAnnotation
* ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
    * ex) org.example.SomeClass
* ASPECTJ: AspectJ 패턴 사용
    * ex) org.example..*Service+
* REGEX: 정규 표현식
    * ex) org\.example\.Default.*
* CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
    * ex) org.example.MyTypeFilter

### 6.5 수동 빈 등록 VS 자동 빈 등록
* 수동 빈 등록이 우선권을 갖는다
* 수동 빈이 자동 빈을 오버라이딩 한다.
* 스프링 부트는 에러를 유발하여 프로그램을 실행 시키지 않는다
  * 만약 의도한 것이라면 application.properties에 설정이 필요하다
  * spring.main.allow-bean-definition-overriding=true  
## 7. Autowired Dependency Injection

> OrderServiceImpl

### 7.1 다양한 의존 관계 주입 방법

@Autowired 사용 가능 위치

* 생성자
    * 가장 많이 사용된다
    * 생성자가 1개면 생략가능, 많이 알려져서 생략한다
    * 불변, 필수 의존관계에서 사용
* 수정자(setter)
    * 선택, 변경 가능성이 있을 경우 사용
* 필드
    * 테스트코드에서 사용 된다
* 일반 메서드
    * 잘 사용되지 않는다

스프링 컨테이너에 담겨 관리가 되고 있는 빈에 적용할 수 있다.

### 7.2 옵션 처리

스프링 빈이 없어도 동작해야 하는 경우

* @Autowired(required=false)
    * 호출 안됨
* org.springframework.lang.@Nullable T t
    * 빈이 없으면 null 세팅 됨
* Optional<T> t
    * 빈이 없으면 Optional.empty

### 7.3 생성자 주입을 택하라

* final 사용으로 코드 누락 방지
* 생성자 주입을 기본으로 하고 옵션이 필요할 경우 setter 사용

### 7.4, 7.5, 7.6 lombok

> HelloLombok
> HelloLombokTest

1. 의존성 주입
2. plugin lombok 설치
3. setting - Annotation Processors - enable annotation processing 활성화

* @RequiredArgsConstructor
    * final 키워드가 있는 필드로 생성자를 만들어 준다
* 우선순위: @Qualifier > @Primary
    * @Qualifier: 상세하게 지정하기 때문
* @Qualifier
    * 빈에 이름을 지정하여 생성자 주입에 사용하게 된다
    * @RequiredArgsConstructor 삭제 해야한다
    * @Qualifier 끼리 매칭해서 사용해야한다
        * 이름을 못찾으면 다음으로 빈을 찾기 때문
* @Primary
    * 등록된 빈이 2개 이상일 경우 우선순위를 주어 충돌을 방지할 수 있다

자주 사용하는 빈은 @Primary, 가끔 사용하는 빈은 @Qualifier

### 7.7 @Qualifier

> MainDiscountPolicy

어노테이션을 생성하여 @Qualifier 지정 후 필요한 곳에 사용할 수 있다<br>
@Qualifier 안에 문자열 오타 시 에러가 나오지 않고 실행시 나타난다<br>
어노테이션 이름은 오타 발생 시 오류를 보여주기에 실수 방지가 가능하다<br>

가능하면 스프링의 기능을 활용하는 것을 권장

### 7.8 List, Map 활용

> AllBeanTest

동적으로 빈을 활용할 때 좋다<br>
컨테이너에 인터페이스로 구현된 모든 구현체를 컬렉션으로 받아 원하는 구현체를 가져온다

### 7.9 수동 등록 VS 자동 등록

1. 자동 기능을 기본
2. AOP: 수동
3. 다형성 활용하는 비지니스 로직: 수동 고려

## 8. 빈 생명주기 콜백

> NetworkClient
> BeanLifeCycleTest

### 8.1 빈 생명주기 콜백 시작

__스프링 빈의 이벤트 라이프사이클__  
스프링 컨테이너 생성 → 스프링 빈 생성 → 의존관계 주입 → 초기화 콜백 사용 → 소멸전 콜백 → 스프링 종료

* 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
* 소멸전 콜백: 빈이 소멸되기 직전에 호출

초기화 작업이란 어플리케이션에 의존관계 주입 후 실제 기능을 하기위한 필요 값들을 설정하는 과정이다

과정 속에서 외부에 값을 시작으로 할 때 생성자에 넣지 말고 객체 생성 후 별도의 작업으로 만들어야 유지보수에 용이하다

### 8.2 인터페이스 InitializingBean, DisposableBean

__스프링 초창기에 나온 방법__

* InitializingBean
    * 의존관계 주입 후 초기화를 진행
    * void afterPropertiesSet()
* DisposableBean
    * 객체 소멸 직전에 사용 됨
    * void destroy()

### 8.3 빈 등록 초기화, 소멸 메소드

1. 초기화 메소드, 소멸 메소드 만들기
2. @Bean(initMethod = "init", destroyMethod = "close")와 같이 옵션으로 함수 이름을 넣어준다

* destroyMethod = "(inferred)"를 기본으로 한다
* close(), shutdown() 메소드를 찾아서 자동으로 실행해 준다
* 외부 라이브러리 사용 시 이 방법을 활용하자

### 8.4 애노테이션 @PostConstruct, @PreDestroy

스프링에서 권장하는 방법이다<br>
현재는 이 방법으로 많이 사용된다<br>
스프링을 의존하는 것이 아니라 자바에서 지원하는 기능이다

1. 초기화 메소드, 소멸 메소드 만들기
2. 애노테이션 사용
    * @PostConstruct: 초기화
    * @PreDestroy: 소멸

## 9. Bean Scope

> scope package

### 9.1 Singleton Scope, Prototype Scope

> SingletonTest, PrototypeTest, SingletonWithPrototypeTest

* Singleton Scope
    * 스프링 컨테이너 생성시 초기화 메소드 호출
    * 하나의 빈이 생성
    * 컨테이너 종료될 때 소멸자 호출 됨

* Prototype Scope
    * 스프링 컨테이너 조회 시 생성되고 초기화 메소드 호출
    * 생성 할 때 마다 다른 인스턴스가 만들어짐
    * 컨테이너는 의존관계 주입까지만 관여 이후는 관리 안한다
    * 소멸자 호출 안됨(클라이언트가 직접 해야함)

### 9.2 Singleton with prototype

싱글톤 빈에 프로토타입 빈을 주입 받으면 싱글톤 빈은 계속 남아 있다<br>
프로토타입을 사용하는 메소드를 호출 시 새로운 프로토타입을 만들지 않는다<br>

프로토타입의 빈을 생성하는 방법이 필요하다

### 9.3 Singleton with Provider

싱글톤 스코프에 프로토타입 스코프를 컨테이너에서 찾아주는 정도의 DL이 필요하다
> DL: Dependency Lookup

* 스프링 의존: ObjectProvider, FactoryProvider
    * ObjectProvider 편의 기능이 많다
* 자바 표준: jakarta.inject.Provider(라이브러리 추가필요)
    * 단순하다

> Provider: 컨테이너에서 조회한 빈을 찾아준다

사실 프로토타입 스코프는 잘 쓰이지 않는다

### 9.4 Web Scope

> 특별한 스코프는 꼭 필요할 때 사용해야한다

__종류__

* request
* session
* application
* websocket

클라이언트의 요청이 있을 때 생성, <br>
싱글톤 스코프에 이 스코프의 의존주입 시 초기 외부요청이 없어 에러 발생<br>

__해결방법__

1. ObjectProvider<T> t;
2. @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    * 인터페이스: ScopedProxyMode.INTERFACES
    * 인터페이스 외: ScopedProxyMode.TARGET_CLASS 


