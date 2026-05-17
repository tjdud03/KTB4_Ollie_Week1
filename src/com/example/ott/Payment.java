package com.example.ott;

import java.math.BigDecimal;
import java.util.Scanner;

public class Payment {

    // 전자 서명이 필요한 기준 금액 설정
    private static final BigDecimal SIGN_LIMIT =
            new BigDecimal("50000");

    // 요금제 결제 진행 메서드
    public void pay(Plan plan) {

        // 선택한 요금제의 최종 금액 계산
        BigDecimal price = plan.calculatePrice();

        System.out.println();
        System.out.println("========================================");
        System.out.println("              결제 정보");
        System.out.println("========================================");

        // 최종 결제 금액 출력
        System.out.println("최종 결제 금액 : "
                + price.intValue()
                + "원");

        // 5만원 이상 결제 시 전자 서명 진행
        if (price.compareTo(SIGN_LIMIT) >= 0) {

            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.println("[ 고액 결제 안내 ]");
            System.out.println("5만원 이상 결제는");
            System.out.println("카드 소유자 확인을 위한");
            System.out.println("전자 서명이 필요합니다.");
            System.out.println();

            System.out.print("전자 서명 입력 >> ");

            // 사용자 전자 서명 입력
            String signature = sc.next();

            System.out.println();
            System.out.println("전자 서명이 정상적으로 확인되었습니다.");
            System.out.println("결제가 승인되었습니다.");

        } else {

            System.out.println();
            System.out.println("5만원 미만 결제는");
            System.out.println("전자 서명 없이 바로 결제가 가능합니다.");
            System.out.println();

            System.out.println("결제가 승인되었습니다.");
        }

        System.out.println();
        System.out.println("이용해주셔서 감사합니다.");
        System.out.println("========================================");
    }
}