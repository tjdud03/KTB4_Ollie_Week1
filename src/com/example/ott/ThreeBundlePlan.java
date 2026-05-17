package com.example.ott;

import java.math.BigDecimal;
import java.util.Scanner;

public class ThreeBundlePlan extends BundlePlan {

    // 30% 할인율을 적용한 3개 결합 요금제 생성
    public ThreeBundlePlan(Ott[] otts) {
        super(otts, new BigDecimal("0.3"));

        // 생성 시 선택 값 검증 진행
        validateSelection();
    }

    @Override
    public void validateSelection() {

        // 부모 클래스의 기본 검증 로직 실행
        super.validateSelection();

        // 3개가 아닌 경우 예외 처리
        if (otts.length != 3) {

            System.out.println();
            System.out.println("3개 결합 요금제는 3개 OTT 선택이 필요합니다.");
            return;
        }

        // 사용자 재입력을 받기 위한 Scanner 생성
        Scanner sc = new Scanner(System.in);

        // 전체 OTT 목록 조회
        Ott[] allOtts = Ott.getOtts();

        // 동일한 OTT가 선택된 경우 다시 입력받도록 반복 처리
        while (otts[0] == otts[1]
                || otts[0] == otts[2]
                || otts[1] == otts[2]) {

            System.out.println();
            System.out.println("동일한 OTT는 중복 선택할 수 없습니다.");
            System.out.println("다시 선택해주세요.");
            System.out.println();

            // 사용자에게 OTT 목록 출력
            for (int i = 0; i < allOtts.length; i++) {
                System.out.println((i + 1) + ". " + allOtts[i].getName());
            }

            int newChoice;

            // 중복된 위치에 따라 다시 선택할 OTT 지정
            if (otts[0] == otts[1]) {
                System.out.print("두 번째 OTT 다시 선택 >> ");
                newChoice = sc.nextInt();
            } else if (otts[0] == otts[2]) {
                System.out.print("세 번째 OTT 다시 선택 >> ");
                newChoice = sc.nextInt();
            } else {
                System.out.print("세 번째 OTT 다시 선택 >> ");
                newChoice = sc.nextInt();
            }

            // 존재하지 않는 번호 입력 시 예외 처리
            if (newChoice < 1 || newChoice > allOtts.length) {
                System.out.println();
                System.out.println("존재하지 않는 OTT 번호입니다.");
                System.out.println("다시 입력해주세요.");
                continue;
            }

            // 사용자 입력값을 배열 인덱스로 변환하여 OTT 재선택
            if (otts[0] == otts[1]) {
                otts[1] = allOtts[newChoice - 1];
            } else if (otts[0] == otts[2]) {
                otts[2] = allOtts[newChoice - 1];
            } else {
                otts[2] = allOtts[newChoice - 1];
            }
        }
    }
}