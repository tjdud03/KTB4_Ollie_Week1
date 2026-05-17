package com.example.ott;

import java.math.BigDecimal;
import java.util.Scanner;

public class TwoBundlePlan extends BundlePlan {

    // 20% 할인율을 적용한 2개 결합 요금제 생성
    public TwoBundlePlan(Ott[] otts) {
        super(otts, new BigDecimal("0.2"));

        // 생성 시 선택 값 검증 진행
        validateSelection();
    }

    @Override
    public void validateSelection() {

        // 부모 클래스의 기본 검증 로직 실행
        super.validateSelection();

        // 2개가 아닌 경우 예외 처리
        if (otts.length != 2) {

            System.out.println();
            System.out.println("2개 결합 요금제는 2개 OTT 선택이 필요합니다.");
            return;
        }

        // 사용자 재입력을 받기 위한 Scanner 생성
        Scanner sc = new Scanner(System.in);

        // 전체 OTT 목록 조회
        Ott[] allOtts = Ott.getOtts();

        // 동일한 OTT 선택 시 다시 입력받도록 반복 처리
        while (otts[0] == otts[1]) {

            System.out.println();
            System.out.println("동일한 OTT는 중복 선택할 수 없습니다.");
            System.out.println("두 번째 OTT를 다시 선택해주세요.");
            System.out.println();

            // 사용자에게 OTT 목록 출력
            for (int i = 0; i < allOtts.length; i++) {
                System.out.println((i + 1) + ". " + allOtts[i].getName());
            }

            System.out.print("두 번째 OTT 선택 >> ");
            int newChoice = sc.nextInt();

            // 존재하지 않는 번호 입력 시 예외 처리
            if (newChoice < 1 || newChoice > allOtts.length) {
                System.out.println();
                System.out.println("존재하지 않는 OTT 번호입니다.");
                System.out.println("다시 입력해주세요.");
                continue;
            }

            // 사용자 입력값을 배열 인덱스로 변환하여 OTT 재선택
            otts[1] = allOtts[newChoice - 1];
        }
    }
}