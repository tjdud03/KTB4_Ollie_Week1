package com.example.ott;

import java.math.BigDecimal;

public class SinglePlan extends Plan {

    // 사용자가 선택한 OTT 저장
    private Ott ott;

    // 선택한 OTT로 단일 요금제 생성
    public SinglePlan(Ott ott) {
        this.ott = ott;

        // 객체 생성 시 OTT 선택 여부 검증
        validateSelection();
    }

    // OTT 선택 여부 검증
    public void validateSelection(){

        // OTT가 선택되지 않은 경우 예외 메시지 출력
        if(ott == null) {
            System.out.println("OTT가 선택되지 않았습니다.");
        }
    };

    @Override
    public BigDecimal calculatePrice() {

        // 선택한 OTT의 가격 반환
        return ott.getPrice();
    }
}
