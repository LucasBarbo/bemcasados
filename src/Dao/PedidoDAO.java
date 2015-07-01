package Dao;

import Model.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    Connection con;

    public PedidoDAO() {
        con = ConexaoUtil.getConnection();
    }

    public void salvar(Pedido pedido) throws SQLException, ParseException {
        String sql = "INSERT INTO pedido(\n"
                + "            localcontratado, datapedido, cerimonial, dataevento, \n"
                + "            horario, idtipoevento, idcliente, valortotalPedido)\n"
                + "    VALUES (?, ?, ?, ?, \n"
                + "            ?, ?, ?, ?)";

        PreparedStatement preparador = con.prepareStatement(sql);

        DateFormat formatoData = new SimpleDateFormat("dd-mm-yyyy");

        java.sql.Date dataPedido = java.sql.Date.valueOf(pedido.getDataPedido());

        java.sql.Date dataEvento = java.sql.Date.valueOf(pedido.getDataEvento());

        System.out.println(dataPedido);

        preparador.setString(1, pedido.getLocalContratado());
        preparador.setDate(2, dataPedido);
        preparador.setString(3, pedido.getCerimonial());
        preparador.setDate(4, dataEvento);
        preparador.setString(5, pedido.getHorario());
        preparador.setInt(6, pedido.getTipoEvento().getId());
        preparador.setInt(7, pedido.getCliente().getId());
        preparador.setDouble(8, pedido.getValorTotalPedido());

        preparador.execute();
        preparador.close();

    }

    public Integer buscarId() throws SQLException {
        String sql = "SELECT MAX(idpedido) FROM pedido";

        PreparedStatement preparador = con.prepareStatement(sql);

        ResultSet consulta = preparador.executeQuery();

        Pedido pedido = new Pedido();
        consulta.next();
        pedido.setIdpedido(consulta.getInt("max"));

        return pedido.getIdpedido();

    }

    public List<Pedido> buscaPorCliente(Integer id) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "select pedido.idpedido, cliente.nome, tipoevento.descricao, pedido.datapedido, pedido.dataevento, pedido.valortotalpedido \n"
                + "from pedido, cliente, tipoevento \n"
                + "where pedido.idcliente = ?"
                + "and pedido.idcliente = cliente.idcliente \n"
                + "and tipoevento.idtipoevento = pedido.idtipoevento;";

        PreparedStatement preparador1 = con.prepareStatement(sql);
        preparador1.setInt(1, id);

        ResultSet consulta1 = preparador1.executeQuery();
        
        PreparedStatement preparador2 = con.prepareStatement("select sum(quantidade) quantidade from itempedido where iditempedido = ?");
       
        
        while (consulta1.next()) {

            Pedido pedido = new Pedido();

            pedido.setIdpedido(consulta1.getInt("idpedido"));
            pedido.setNomeCliente(consulta1.getString("nome"));
            pedido.setDescricao(consulta1.getString("descricao"));
            pedido.setDataPedido(consulta1.getString("datapedido"));
            pedido.setDataEvento(consulta1.getString("dataevento"));
            pedido.setValorTotalPedido(consulta1.getDouble("valortotalpedido"));
            
            
             preparador2.setInt(1, pedido.getIdpedido());
             ResultSet consulta2 = preparador2.executeQuery();
             consulta2.next();
             
             pedido.setQuantidadeItens(consulta2.getInt("quantidade"));
            
            
            pedidos.add(pedido);
        }

        return pedidos;
    }

    public List<Pedido> buscaPorDataPedido(String dataInicio, String dataFinal) throws SQLException{
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "select p.idpedido, c.nome, t.descricao, p.datapedido, p.dataevento, p.valortotalpedido\n"
                + "from pedido p, cliente c, tipoevento t  \n"
                + "where (p.datapedido) between  ? and ?\n"
                + "and p.idcliente = c.idcliente\n"
                + "and t.idtipoevento = p.idtipoevento";
         
        PreparedStatement preparador = con.prepareStatement(sql);
        
        DateFormat formatoData = new SimpleDateFormat("dd-mm-yyyy");

        java.sql.Date data1 = java.sql.Date.valueOf(dataInicio);
        java.sql.Date data2 = java.sql.Date.valueOf(dataFinal);
        
        preparador.setDate(1, data1);
        preparador.setDate(2, data2);
        
               
        ResultSet consulta = preparador.executeQuery();
        
        PreparedStatement preparador2 = con.prepareStatement("select sum(quantidade) quantidade from itempedido where iditempedido = ?");
       
        
        while (consulta.next()){

            Pedido pedido = new Pedido();

            pedido.setIdpedido(consulta.getInt("idpedido"));
            pedido.setNomeCliente(consulta.getString("nome"));
            pedido.setDescricao(consulta.getString("descricao"));
            pedido.setDataPedido(consulta.getString("datapedido"));
            pedido.setDataEvento(consulta.getString("dataevento"));
            pedido.setValorTotalPedido(consulta.getDouble("valortotalpedido"));

             preparador2.setInt(1, pedido.getIdpedido());
             ResultSet consulta2 = preparador2.executeQuery();
             consulta2.next();
             
             pedido.setQuantidadeItens(consulta2.getInt("quantidade"));
            
            pedidos.add(pedido);
        }
        
        System.out.println("consulta datas feita");
        return pedidos;
    }
    
    public List<Pedido> buscarPorDataEntrega(String dataInicio, String dataFinal) throws SQLException{
    List<Pedido> pedidos = new ArrayList<>();
        String sql = "select p.idpedido, c.nome, t.descricao, p.datapedido, p.dataevento, p.valortotalpedido\n"
                + "from pedido p, cliente c, tipoevento t  \n"
                + "where (p.dataevento) between  ? and ?\n"
                + "and p.idcliente = c.idcliente\n"
                + "and t.idtipoevento = p.idtipoevento";
         
        PreparedStatement preparador = con.prepareStatement(sql);
        
        DateFormat formatoData = new SimpleDateFormat("dd-mm-yyyy");

        java.sql.Date data1 = java.sql.Date.valueOf(dataInicio);
        java.sql.Date data2 = java.sql.Date.valueOf(dataFinal);
        
        preparador.setDate(1, data1);
        preparador.setDate(2, data2);
        
               
        ResultSet consulta = preparador.executeQuery();
        
        PreparedStatement preparador2 = con.prepareStatement("select sum(quantidade) quantidade from itempedido where iditempedido = ?");
       
        
        while (consulta.next()){

            Pedido pedido = new Pedido();

            pedido.setIdpedido(consulta.getInt("idpedido"));
            pedido.setNomeCliente(consulta.getString("nome"));
            pedido.setDescricao(consulta.getString("descricao"));
            pedido.setDataPedido(consulta.getString("datapedido"));
            pedido.setDataEvento(consulta.getString("dataevento"));
            pedido.setValorTotalPedido(consulta.getDouble("valortotalpedido"));

             preparador2.setInt(1, pedido.getIdpedido());
             ResultSet consulta2 = preparador2.executeQuery();
             consulta2.next();
             
             pedido.setQuantidadeItens(consulta2.getInt("quantidade"));
            
            pedidos.add(pedido);
        }
        
        System.out.println("consulta datas feita");
        return pedidos;
    
    
    }

}
