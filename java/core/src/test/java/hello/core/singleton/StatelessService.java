package hello.core.singleton;

public class StatelessService {
    //공유되는 필드를 제거하고 들어온 값을 돌려주어 변수로 저장하여 사용해야 함
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
