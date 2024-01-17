## 7. 의존관계 자동 주입
* OrderServiceImpl로 Test
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

### 7.4, 7.5 lombok
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

### 7.6 @Qualifier
어노테이션을 생성하여 @Qualifier 지정 후 필요한 곳에 사용할 수 있다
@Qualifier 안에 문자열 오타 시 에러가 나오지 않고 실행시 나타난다
어노테이션 이름은 오타 발생 시 오류를 보여주기에 실수 방지가 가능하다

가능하면 스프링의 기능을 활용하는 것을 권장