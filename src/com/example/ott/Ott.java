package com.example.ott;

import java.math.BigDecimal;

public class Ott {
    private String name;
    private BigDecimal price;

    // 프로그램에서 공통으로 사용할 OTT 목록을 미리 생성
    private static final Ott[] otts = new Ott[]{
            new Ott("넷플릭스", new BigDecimal("30000")),
            new Ott("디즈니플러스", new BigDecimal("29000")),
            new Ott("티빙", new BigDecimal("28000")),
            new Ott("웨이브", new BigDecimal("21000"))
    };

    // 외부에서 OTT 목록을 조회할 수 있도록 반환
    public static Ott[] getOtts() {
        return otts;
    }

    // OTT 객체 생성 시 이름과 가격 초기화
    public Ott(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    // OTT 이름 반환
    public String getName() {
        return name;
    }

    // OTT 가격 반환
    public BigDecimal getPrice() {
        return price;
    }
}
