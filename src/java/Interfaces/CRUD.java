/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author cetecom
 * @param <Generic>
 */
public interface CRUD<Generic> {
    
    /**
     *
     * @param o
     * @return
     */
    public boolean crear(Generic o);

    /**
     *
     * @param o
     * @return
     */
    public boolean eliminar(Generic o);

    /**
     *
     * @param o
     * @return
     */
    public boolean eliminar(int o);

    /**
     *
     * @param o
     * @return
     */
    public boolean modificar(Generic o);

    /**
     *
     * @return
     */
    public ArrayList<Generic> listarTodo();
    
}
