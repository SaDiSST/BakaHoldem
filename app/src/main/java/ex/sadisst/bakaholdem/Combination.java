package ex.sadisst.bakaholdem;

public class Combination {
    private String title;
    private String info;
    private String detailInfo;
    private String combinationExample;
//    private final int imageResource;

//    public Combination(String title, String info, int imageResource) {
//        this.title = title;
//        this.info = info;
//        this.imageResource = imageResource;
//    }

    public Combination(String title, String info, String detailInfo, String combinationExample) {
        this.title = title;
        this.info = info;
        this.detailInfo = detailInfo;
        this.combinationExample = combinationExample;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    String getDetailInfo() {
        return detailInfo;
    }

    String getCombinationExample() {
        return combinationExample;
    }

//    public int getImageResource() {
//        return imageResource;
//    }
}
