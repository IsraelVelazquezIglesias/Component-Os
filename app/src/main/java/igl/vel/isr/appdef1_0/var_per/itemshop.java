package igl.vel.isr.appdef1_0.var_per;

public class itemshop {

    private String nomb;
    private String desc;
    private Double prec;

    public itemshop() {
    }

    public itemshop(String nomb, String desc, Double prec) {
        this.nomb = nomb;
        this.desc = desc;
        this.prec = prec;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPrec() {
        return prec;
    }

    public void setPrec(Double prec) {
        this.prec = prec;
    }
}
