package com.example.ott;

import java.math.BigDecimal;

public class BundlePlan extends Plan {

    //자식만 접근 가능하게 protected
    protected Ott[] otts;

    // 결합 할인율 저장
    protected BigDecimal discountRate;

    // 선택한 OTT 목록과 할인율 초기화
    public BundlePlan(Ott[] otts, BigDecimal discountRate) {
        this.otts = otts;
        this.discountRate = discountRate;

        // 객체 생성 시 올바른 선택인지 검증
        validateSelection();
    }

    // OTT 선택 여부 검증
    public void validateSelection(){

        // OTT가 선택되지 않은 경우 예외 메시지 출력
        if(otts == null) {
            System.out.println("OTT를 선택해주세요.");
        }
    };

    @Override
    public BigDecimal calculatePrice() {

        // 총 금액 계산을 위한 변수 초기화
        BigDecimal total = BigDecimal.ZERO;

        // 선택한 OTT들의 가격 합산
        for (int i = 0; i < otts.length; i++) {
            total = total.add(otts[i].getPrice());
        }

        // 총 금액에 할인율 적용
        BigDecimal discount = total.multiply(discountRate);

        // 할인 금액을 제외한 최종 금액 반환
        return total.subtract(discount);
    }
}
