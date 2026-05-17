package com.example.ott;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 사용자 입력을 받기 위한 Scanner 생성
        Scanner sc = new Scanner(System.in);

        // OTT 목록을 여러 곳에서 재사용하기 위해 공통 배열로 관리
        Ott[] otts = Ott.getOtts();

        System.out.println("========================================");
        System.out.println("        OTT 통합 구독 요금제");
        System.out.println("========================================");
        System.out.println("1. 단일 요금제");
        System.out.println("2. 2개 결합 요금제");
        System.out.println("3. 3개 결합 요금제");
        System.out.println("0. 종료");
        System.out.print("선택 >> ");

        int choice = sc.nextInt();

        // 사용자가 선택한 요금제를 저장할 변수
        // 요금제 종류에 따라 서로 다른 객체가 생성
        Plan plan = null;

        // 단일 요금제 선택
        if (choice == 1) {

            // 올바른 OTT를 선택할 때까지 반복
            while (true) {

                // OTT 목록 출력
                for (int i = 0; i < otts.length; i++) {
                    System.out.println((i + 1) + ". " + otts[i].getName());
                }

                System.out.print("선택 >> ");
                int select = sc.nextInt();

                // 잘못된 번호 입력 예외 처리
                if (select < 1 || select > otts.length) {
                    System.out.println("존재하지 않는 OTT입니다. 다시 선택해주세요.");
                    continue;
                }

                // 선택한 OTT로 단일 요금제 생성
                // 사용자가 입력한 번호를 배열 인덱스에 맞게 변환
                plan = new SinglePlan(otts[select - 1]);
                break;
            }
        }

        // 2개 결합 요금제 선택
        else if (choice == 2) {

            while (true) {

                System.out.println("=== 2개 결합 요금제 ===");

                for (int i = 0; i < otts.length; i++) {
                    System.out.println((i + 1) + ". " + otts[i].getName());
                }

                System.out.print("첫 번째 OTT >> ");
                int first = sc.nextInt();

                System.out.print("두 번째 OTT >> ");
                int second = sc.nextInt();

                // 입력한 OTT 번호 검증
                if (first < 1 || first > otts.length ||
                        second < 1 || second > otts.length) {

                    System.out.println("존재하지 않는 OTT입니다. 다시 선택해주세요.");
                    continue;
                }

                // 선택한 OTT 2개로 결합 요금제 생성
                plan = new TwoBundlePlan(new Ott[]{
                        otts[first - 1],
                        otts[second - 1]
                });

                break;
            }
        }

        // 3개 결합 요금제 선택
        else if (choice == 3) {

            while (true) {

                System.out.println("=== 3개 결합 요금제 ===");

                for (int i = 0; i < otts.length; i++) {
                    System.out.println((i + 1) + ". " + otts[i].getName());
                }

                System.out.print("첫 번째 OTT >> ");
                int first = sc.nextInt();

                System.out.print("두 번째 OTT >> ");
                int second = sc.nextInt();

                System.out.print("세 번째 OTT >> ");
                int third = sc.nextInt();

                if (first < 1 || first > otts.length ||
                        second < 1 || second > otts.length ||
                        third < 1 || third > otts.length) {

                    System.out.println("존재하지 않는 OTT입니다. 다시 선택해주세요.");
                    continue;
                }

                // 선택한 OTT 3개로 결합 요금제 생성
                plan = new ThreeBundlePlan(new Ott[]{
                        otts[first - 1],
                        otts[second - 1],
                        otts[third - 1]
                });

                break;
            }
        }

        else {
            System.out.println("프로그램 종료");
            return;
        }

        // 생성된 요금제 객체를 기반으로 결제 진행
        new Payment().pay(plan);
    }
}