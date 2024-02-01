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