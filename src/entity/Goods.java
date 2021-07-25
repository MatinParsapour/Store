package entity;

public class Goods {
    private static int goodsId;

    public static int getGoodsId() {
        return goodsId;
    }

    public static void setGoodsId(int goodsId) {
        Goods.goodsId = goodsId;
    }
}
