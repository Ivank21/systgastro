/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.systgastro.model.jdbc;

import java.sql.SQLException;
import py.com.systgastro.bean.dto.PedidoDTO;
import java.util.List;

/**
 * Esta interfaz contiene los métodos abstractos con las operaciones básicas
 * sobre la tabla de Pedido CRUD (Create, Read, Update y Delete) 
 * Se debe crear una clase concreta para implementar el código asociado a cada 
 * método
 * 
 * @author Alumnos de IS II
 * @version 1.0
 * @see PedidoDaoJDBC
 */
public interface PedidoDAO {

    /**
     *
     * @param pedido Representa al objeto pedido que se requiere para realizar la insercion en la BD
     * @return Retorna el número de filas insertadas
     * @throws SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    public int insert(PedidoDTO pedido)
            throws SQLException;

    /**
     *
     * @param pedido Representa al objeto pedido que se requiere para realizar el update en la BD
     * @return Retorna el número de filas actualizadas
     * @throws SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    public int update(PedidoDTO pedido)
            throws SQLException;

    /**
     *
     * @param pedido Representa al objeto pedido que se requiere para realizar el delete en la BD
     * @return Retorna el número de filas eliminadas
     * @throws SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    public int delete(PedidoDTO pedido)
            throws SQLException;

    /**
     *
     * @return Retorna las filas de la tabla Pedido en una lista
     * @throws SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    public List<PedidoDTO> select() throws SQLException;
}
