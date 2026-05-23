package com.example.ott;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;

public class Payment {

    // 5만원 이상이면 전자서명 진행
    private static final BigDecimal SIGN_LIMIT =
            new BigDecimal("50000");

    public void pay(Plan plan) {

        Scanner sc = new Scanner(System.in);

        // 최종 결제 금액 계산
        BigDecimal price = plan.calculatePrice();

        System.out.println();
        System.out.println("========================================");
        System.out.println("              결제 정보");
        System.out.println("========================================");

        System.out.println("최종 결제 금액 : "
                + price.intValue()
                + "원");

        // 고액 결제 시 전자서명 입력
        if (price.compareTo(SIGN_LIMIT) >= 0) {

            System.out.println();
            System.out.println("[ 고액 결제 안내 ]");
            System.out.println("5만원 이상 결제는");
            System.out.println("전자 서명이 필요합니다.");
            System.out.println();

            System.out.print("전자 서명 입력 >> ");
            String signature = sc.nextLine();
        }

        // 결제 상황 랜덤 생성
        Random random = new Random();

        // 0 : 바로 승인
        // 1 : 은행 점검
        // 2 : 승인 실패 후 재시도
        int scenario = random.nextInt(3);

        // 바로 결제 성공
        if (scenario == 0) {

            paymentLoading();

            System.out.println();
            System.out.println("[ 결제 승인 완료 ]");
            System.out.println("결제가 정상적으로 완료되었습니다.");
            System.out.println();
            System.out.println("이용해주셔서 감사합니다.");
        }

        // 은행 서버 점검 상황
        else if (scenario == 1) {

            System.out.println();
            System.out.println("[ BANK SERVER ]");
            System.out.println("점검 종료 후 자동 재시도됩니다.");
            System.out.println();

            // 은행 점검 로딩 출력
            bankLoading();

            System.out.println();
            System.out.println("[ BANK SERVER ]");
            System.out.println("은행 서버 점검 종료");
            System.out.println();

            // 점검 종료 후 자동 재결제
            paymentLoading();

            System.out.println();
            System.out.println("[ 결제 승인 완료 ]");
            System.out.println("결제가 정상적으로 완료되었습니다.");
            System.out.println();
            System.out.println("이용해주셔서 감사합니다.");
        }

        // 승인 실패 후 재시도
        else {

            paymentLoading();

            // 실패 사유 랜덤 선택
            String[] failReasons = {
                    "카드사 인증 실패",
                    "일시적 오류",
                    "통신 오류"
            };

            String reason =
                    failReasons[random.nextInt(failReasons.length)];

            System.out.println();
            System.out.println("[ 승인 실패 ]");
            System.out.println("사유 : " + reason);
            System.out.println("다시 시도합니다...");
            System.out.println();

            try {

                // 재시도 전 잠시 대기
                Thread.sleep(2000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            // 재결제 진행
            paymentLoading();

            System.out.println();
            System.out.println("[ 재시도 성공 ]");
            System.out.println("결제가 정상적으로 완료되었습니다.");
            System.out.println();
            System.out.println("이용해주셔서 감사합니다.");
        }
    }

    // 결제 승인 로딩 메서드
    public void paymentLoading() {

        // 승인 완료 여부 저장
        final boolean[] isFinished = {false};

        // 실제 승인 처리 담당 스레드
        Thread approveThread = new Thread(() -> {

            try {

                // 승인 처리 시간
                Thread.sleep(3000);

                // 승인 완료 상태 변경
                isFinished[0] = true;

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });

        // 로딩 출력 스레드
        Thread loadingThread = new Thread(() -> {

            try {

                // 승인 완료 전까지 반복 출력
                while (!isFinished[0]) {

                    System.out.print("\r결제 승인 요청중");
                    Thread.sleep(300);

                    System.out.print("\r결제 승인 요청중.");
                    Thread.sleep(300);

                    System.out.print("\r결제 승인 요청중..");
                    Thread.sleep(300);

                    System.out.print("\r결제 승인 요청중...");
                    Thread.sleep(300);
                }

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });

        // 두 스레드 동시에 실행
        approveThread.start();
        loadingThread.start();

        try {

            // 두 스레드 종료까지 대기
            approveThread.join();
            loadingThread.join();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println();
    }

    // 은행 점검 로딩 메서드
    public void bankLoading() {

        // 점검 종료 여부 저장
        final boolean[] isFinished = {false};

        // 점검 시간 담당 스레드
        Thread bankThread = new Thread(() -> {

            try {

                // 점검 시간
                Thread.sleep(5000);

                // 점검 종료 상태 변경
                isFinished[0] = true;

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });

        // 점검중 출력 스레드
        Thread loadingThread = new Thread(() -> {

            try {

                // 점검 종료 전까지 반복 출력
                while (!isFinished[0]) {

                    System.out.print("\r현재 은행 서버 점검중");
                    Thread.sleep(300);

                    System.out.print("\r현재 은행 서버 점검중.");
                    Thread.sleep(300);

                    System.out.print("\r현재 은행 서버 점검중..");
                    Thread.sleep(300);

                    System.out.print("\r현재 은행 서버 점검중...");
                    Thread.sleep(300);
                }

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });

        // 두 스레드 동시에 실행
        bankThread.start();
        loadingThread.start();

        try {

            // 두 스레드 종료까지 대기
            bankThread.join();
            loadingThread.join();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println();
    }
}