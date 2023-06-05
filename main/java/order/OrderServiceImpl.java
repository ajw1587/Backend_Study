package hello.example.core.order;

import hello.example.core.discount.DiscountPolicy;
import hello.example.core.member.Member;
import hello.example.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor    // 자동으로 생성자를 생성해준다
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FIxDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 위와 같은 코드는 의존성 때문에 좋은 코드가 아니다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired  // AutoAppConfig 에는 의존관계가 없으므로 생성자에 @AutoWired 를 선언하여 의존관계를 나타내준다.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
