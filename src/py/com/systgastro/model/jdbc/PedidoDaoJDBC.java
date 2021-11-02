/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.systgastro.model.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import py.com.systgastro.bean.dto.PedidoDTO;

/**
 *
 * Esta clase almacena las consultas que vamos a usar (los query INSERT, UPDATE, 
 * DELETE y SELECT) 
 * Esta clase implementa la interface PedidoDAO es una implementación con 
 * la tecnología JDBC podría haber otro tipo de implementaciones con tecnologías 
 * como EclipseLink, Hibernate, JPA; TopLink, etc. La interface es independiente 
 * de la tecnología, pero en su implementación se puede aplicar JDBC. 
 * Definimos la clase PedidoDaoJDBC e implementamos la interface PedidoDAO 
 * Al utilizar la implementación de una interface estamos obligados a agregar 
 * una implementación de cada uno de los métodos definidos en la interface 
 * Es en este punto donde estamos usando patrones de diseño y utilizando el API 
 * JDBC para nuestra capa de datos. 
 * La variable userConn almacena la dirección de la BD que le proporcionemos
 * 
 * @author Alumnos de IS II
 * @version 1.0
 */
public class PedidoDaoJDBC implements PedidoDAO {

    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO pedido(nombre_pedido, fecha_pedido, numero, estado, cantidad, total, cod_cliente, cod_producto) VALUES(?,?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE pedido SET nombre_pedido = ?, fecha_pedido = ?, numero = ?, estado = ?, cantidad = ?, total = ?, cod_cliente = ?, cod_producto = ? WHERE cod_pedido  =  ?";
    private final String SQL_DELETE = "DELETE FROM pedido WHERE cod_pedido = ?";
    private final String SQL_SELECT = "SELECT * FROM pedido";
    /**
    * Constructor vacio
    */
 public PedidoDaoJDBC() {
    }

    /**
     * Constructor PedidoDaoJDBC recibe una conexion, este constructor 
     * Es útil para manejo de transacciones ya que nos asegura una única conexion
     * durante el tiempo de vida de este objeto y una vez que asignamos una
     * conexion no la vamos a cerrar durante el tiempo de vida del objeto y cada
     * vez que utilicemos una operación CRUD vamos a utilizar la misma conexion
     * Esto es para que al final se pueda hacer un commit o rollback de toda la
     * transacción
     *
     * @param conn Representa la coneccion a la BD
     */
    public PedidoDaoJDBC(Connection conn) {
        this.userConn = conn;
    }

    /**
     * El método insert recibe como argumento un objeto DTO el cual viene de
     * otra capa, y se extraen sus valores para crear un nuevo registro, por eso
     * este método es del tipo Data Transfer Object (DTO) El Overrride aparece
     * porque estamos sobrescribiendo un método definido en la interface
     * @param pedido Representa al objeto pedido que se requiere para realizar la insercion en la BD
     * @return Retorna el número de filas insertadas
     * @throws java.sql.SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    @Override
    public int insert(PedidoDTO pedido) throws SQLException {
        //Declaración de las variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //para saber el número de registros que han sido modificados
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, pedido.getNombre_pedido());
            stmt.setString(index++, pedido.getFecha_pedido());
            stmt.setInt(index++, pedido.getNumero());
            stmt.setString(index++, pedido.getEstado());
            stmt.setInt(index++, pedido.getCantidad());
            stmt.setInt(index++, pedido.getTotal());            
            stmt.setInt(index++, pedido.getCod_cliente());       
            stmt.setInt(index++, pedido.getCod_producto());       
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    /**
     * El método update recibe un objeto pedidoDTO el cual encapsula la
     * información en un solo objeto y evitamos pasar los 3 parámetros de manera
     * aislada, Después extraemos la información del objeto y actualizamos el
     * registro seleccionado 
     * En un DTO transferimos todo el objeto y no solo parte del objeto 
     * Este objeto PedidoDTO tiene los datos que necesitamos para manejar el metodo update
     * @param pedido Representa al objeto pedido que se requiere para realizar el update en la BD
     * @return Retorna el número de filas actualizadas
     * @throws java.sql.SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    @Override
    public int update(PedidoDTO pedido)
            throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;//definimos nuestro parámetro con índice 1
            stmt.setString(index++, pedido.getNombre_pedido());
            stmt.setString(index++, pedido.getFecha_pedido());
            stmt.setInt(index++, pedido.getNumero());
            stmt.setString(index++, pedido.getEstado());
            stmt.setInt(index++, pedido.getCantidad());
            stmt.setInt(index++, pedido.getTotal());            
            stmt.setInt(index++, pedido.getCod_cliente());       
            stmt.setInt(index++, pedido.getCod_producto());
            stmt.setInt(index, pedido.getCod_pedido());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    /**
     * Recibimos un objeto PedidoDTO no necesariamente debe venir lleno,
     * sino solo nos importa el atributo cod_pedido
     * @param pedido Representa al objeto pedido que se requiere para realizar el delete en la BD
     * @return Retorna el número de filas eliminadas
     * @throws java.sql.SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    @Override
    public int delete(PedidoDTO pedido) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pedido.getCod_pedido());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
        return rows;
    }

    /**
     * Por último definimos el método select el cual regresa una lista de
     * objetos de tipo DTO En este método utilizamos el objeto PedidoDTO
     * para llenar una lista y regresarla
     *
     * @return Retorna las filas de la tabla Pedido en una lista
     * @throws java.sql.SQLException Utiliza esta clase para proporcionar informacion acerca de los errores ocurridos a la hora de conectarse a la BD
     */
    @Override
    public List<PedidoDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PedidoDTO pedidoDTO = null;
        List<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Por cada registro se recuperan los valores
                //de las columnas y se crea un objeto DTO
                //En este punto hay que pasar los valores en el mismo orden que se le paso al select
                int cod_pedidoTemp = rs.getInt(1);
                String nombre_pedidoTemp = rs.getString(2);
                String fecha_pedidoTemp = rs.getString(3);
                int numeroTemp = rs.getInt(4);
                String estadoTemp = rs.getString(5);
                int cantidadTemp = rs.getInt(6);
                int totalTemp = rs.getInt(7);
                int cod_clienteTemp = rs.getInt(8);
                int cod_productoTemp = rs.getInt(9);
                
                //Llenamos el DTO y lo agregamos a la lista
                pedidoDTO = new PedidoDTO();
                pedidoDTO.setCod_pedido(cod_pedidoTemp);
                pedidoDTO.setNombre_pedido(nombre_pedidoTemp);
                pedidoDTO.setFecha_pedido(fecha_pedidoTemp);
                pedidoDTO.setNumero(numeroTemp);
                pedidoDTO.setEstado(estadoTemp);
                pedidoDTO.setCantidad(cantidadTemp);
                pedidoDTO.setTotal(totalTemp);
                pedidoDTO.setCod_cliente(cod_clienteTemp);
                pedidoDTO.setCod_producto(cod_productoTemp);
                pedidos.add(pedidoDTO);//agregamos a lista DTO
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return pedidos;
    }

}
