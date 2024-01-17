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

### 7.4 lombok