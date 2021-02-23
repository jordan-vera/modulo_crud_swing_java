
package restaurante;


public class ProductoReport {
    
    private String idalimento;
    private String alimento;
    private String precio;
    private String cantidad;
    private String importe;

    public ProductoReport(String idalimento, String alimento, String precio, String cantidad, String importe) {
        this.idalimento = idalimento;
        this.alimento = alimento;
        this.precio = precio;
        this.cantidad = cantidad;
        this.importe = importe;
    }
    
    public ProductoReport() {
    }
    
    public String getIdalimento() {
        return idalimento;
    }

    public void setIdalimento(String idalimento) {
        this.idalimento = idalimento;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }
    

    
    
    
}
