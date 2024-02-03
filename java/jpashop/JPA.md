FK를 가진 테이블이 연관관계의 주인이다

Getter는 다 열어도 됨
Setter는 필요한 경우만 오픈

코드로 테이블을 만들경우 확인 후에 따로 DDL문서를 만들어야한다

XToOne(fetch = FetchType.LAZY)

컬렉션은 바로 초기화
중간에 변경하면 안됨

cascade
부모테이블에 변경이 나타나면 참조테이블의 데이터 변경 가능

@PersistenceContext
스프링이 EntityManager 만들어서 주입해줌
이를 생성자 주입 혹은 lombok에 있는 @RequiredArgsConstructor 사용해도 동작한다. 스프링 부트에서 알아서 인식하기 때문이다
버전에 따라 꼭 지정된 에노테이션을 사용해야한다.

@Transactional: 수정 삭제 변경 메소드에 사용
@Transactional(readOnly = true): 읽기 전용 테이터를 주는 메소드에 사용

@Autowired는 생성자에 사용, 생성자가 1개면 생략해도 됨
lombok이 있다면 final 키워드가 있는 필드를 추적하여 생성자 주입을 시켜주는
@RequiredArgsConstructor 사용을 권장

test - resources - application.yml
해당 경로에 설정파일을 만들어 놓으면 스프링 부트는 외부 DB 실행 없이도 테스트 가능하도록 자동화 되어있다.
사용하려는 DB 의존성이 준비되어있어야 한다.

@Vaild 메소드 인자에 사용하여 유효성 검사를 시행한다. 입력된 값이 없으면 에러를 뿌려준다
@Vaild가 있는 인자의 필드에 @NotEmpty를 확인한다

BindingResult 에러가 있을 경우 hasErrors()를 사용하여 에러에 대한 대처를 할 수 있다.
@NotEmpty에 있는 메세지를 출력한다. 이는 html에서 타임리프로 사용해 보여준다

JPA에서 권장하는 데이터 수정 방법
변경 감지와 병합(merge)
변경 감지를 사용해야한다
DB에서 찾아온 객체를 setter로 데이터를 수정하면 @Transactional이 있는 메소드가 종료될 때 flush를 진행하여 자동을 값이 변경된다
또한 객체에 값 변경을 위한 메소드를 만들어 변경시점을 확인하도록 해야한다
타인이 추적하기 용이하면 유지보수에 도움을준다
병합은 직접적으로 객체 생성자로 만들어 하나씩 값을 지정한 후 EntityManager.merge()를 사용하는데 문제점은 지정하지 않은 값은 null로 저장되기 때문에 변경 사항이 없는 값에도 영향을 미치기 때문에 치명적이다
> merge(): 사용 시 변경감지의 과정을 거친다 
더욱이 실무에서는 전체 정보를 변경하는 일도 거의 없기 때문에 변경감지를 이용해야 안전핟
정리하자면 컨트롤러에서 엔티티 생성하지 말자
서비스 계층에 명확하게 변경값을 전달하는 메소드를 만들자

