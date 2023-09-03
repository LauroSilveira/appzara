package com.appzara.enuns;

public enum Brand {
    ZARA(1);

    private int brandId;

    Brand(int brandId) {
        this.brandId = brandId;
    }

    public int getBrandID() {
        return brandId;
    }
}
