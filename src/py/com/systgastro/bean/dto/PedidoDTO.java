/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.systgastro.bean.dto;

/**
 * La clase tiene una clase vacía que funciona como un constructor y otra con un
 * argumento Este argumento debe coincidir con el atributo que se encuentra en
 * la clase Pedido por eso proporcionamos cod_pedido
 *
 * @author Alumnos de IS II
 * @version 1.0
 */
public class PedidoDTO {

    /**
     *
     */
    public PedidoDTO() {
    }

    /**
     *
     * @param cod_pedido Representa el código del pedido
     */
    public PedidoDTO(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }
    /**
     * Definir los atributos de la clase Pedido Estos atributos deben
     * corresponder a cada una de las columnas de nuestra tabla pedido
     */
    private int cod_pedido;
    private String nombre_pedido;
    private String fecha_pedido;
    private int numero;
    private String estado;
    private int cantidad;
    private int total;
    private int cod_cliente;
    private int cod_producto;

    /**
     * Agregamos los métodos get y set para cada uno de los atributos Por ultimo
     * sobrescribimos el método toString() para imprimir el estado de este
     * objeto concatenando el valor de cada atributo del objeto
     *
     * @return Retorna el código del pedido
     */
    public int getCod_pedido() {
        return cod_pedido;
    }

    /**
     *
     * @param cod_pedido Representa el código del pedido
     */
    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    /**
     *
     * @return Retorna el nombre del pedido
     */
    public String getNombre_pedido() {
        return nombre_pedido;
    }

    /**
     *
     * @param nombre_pedido Representa el nombre del pedido
     */
    public void setNombre_pedido(String nombre_pedido) {
        this.nombre_pedido = nombre_pedido;
    }

    /**
     *
     * @return Retorna la fecha en la que se ha hecho el pedido
     */
    public String getFecha_pedido() {
        return fecha_pedido;
    }

    /**
     *
     * @param fecha_pedido Representa la fecha en la que se ha hecho el pedido
     */
    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    /**
     *
     * @return Retorna el numero de pedido
     */
    public int getNumero() {
        return numero;
    }

    /**
     *
     * @param numero Representa el numero del pedido
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     *
     * @return Retorna el estado del pedido
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @param estado Representa el estado del pedido, que puede ser Pendiente o Entregado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * @return Retorna la cantidad del plato que el cliente desea
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param cantidad Representa la cantidad del plato que desea el cliente
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     *
     * @return Retorna el costo total del pedido del cliente
     */
    public int getTotal() {
        return total;
    }

    /**
     *
     * @param total Representa el costo total del pedido del cliente
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     *
     * @return Retorna el código del cliente
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     *
     * @param cod_cliente Representa el código del cliente
     */
    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    /**
     *
     * @return Retorna el código del producto o plato que desea el cliente
     */
    public int getCod_producto() {
        return cod_producto;
    }
    
    /**
     *
     * @param cod_producto Representa el código del producto o plato que desea el cliente
     */
    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    /**
     *
     * @return Retorna todos los datos del pedido del cliente en una lista
     *
     */
    @Override
    public String toString() {
        return "Pedido{" + "cod_pedido=" + cod_pedido + ", nombre_pedido=" + nombre_pedido + ", fecha_pedido=" + fecha_pedido + ", numero=" + numero + ", estado=" + estado + ", cantidad=" + cantidad +", total=" + total +", cod_cliente=" + cod_cliente +", cod_producto=" + cod_producto +"}";
    }
}
