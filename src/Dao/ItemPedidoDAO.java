package Dao;

import Model.ItemPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemPedidoDAO {

    Connection con;

    public ItemPedidoDAO() {
        con = ConexaoUtil.getConnection();
    }

    public void salvar(ItemPedido intem, Integer id) throws SQLException {
        String sql = "INSERT INTO itempedido(\n"
                + "            iditempedido, idproduto, quantidade, valor)\n"
                + "    VALUES (?, ?, ?, ?);";
        PreparedStatement preparador = con.prepareStatement(sql);
        
        preparador.setInt(1, id);
        preparador.setInt(2, intem.getProduto().getId());
        preparador.setInt(3, intem.getQuantidade());
        preparador.setDouble(4, intem.getValor());
        
        preparador.execute();
        preparador.close();
      
    }
}
