package pl.webd.dawid124.simpleratingservice.products.model;

public class FetchData {

    private int limit;
    private int offset;

    public FetchData() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
