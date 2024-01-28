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
스프링이 EntituManager 만들어서 주입해줌