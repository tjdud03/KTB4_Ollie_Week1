package com.example.ott;

import java.math.BigDecimal;

public class Plan {

    // 요금제 금액 계산 기능을 자식 클래스에서 재정의할 수 있도록 구성
    public BigDecimal calculatePrice(){

        // 기본값 반환
        return BigDecimal.ZERO;
    }
}
