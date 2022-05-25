/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Silvana
 */
public class TipoDocumento {
    private int codTipoDocto;
    private String tipoDocto;

    public TipoDocumento() {
    }
    

    public TipoDocumento(int codTipoDocto, String tipoDocto) {
        this.codTipoDocto = codTipoDocto;
        this.tipoDocto = tipoDocto;
    }

    public int getCodTipoDocto() {
        return codTipoDocto;
    }

    public void setCodTipoDocto(int codTipoDocto) {
        this.codTipoDocto = codTipoDocto;
    }

    public String getTipoDocto() {
        return tipoDocto;
    }

    public void setTipoDocto(String tipoDocto) {
        this.tipoDocto = tipoDocto;
    }
    
    
}
