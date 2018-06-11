package pl.webd.dawid124.simpleratingservice.file.model;

public class Picture {

    private long id;
    private String label;
    private String src;
    private long productId;

    public Picture() {
    }

    public Picture(String src, long productId) {
        this.src = src;
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
