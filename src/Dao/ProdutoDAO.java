
package Dao;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutoDAO {
    Connection con;

    public ProdutoDAO() {
        con = ConexaoUtil.getConnection();
    }

    public void salvar(Produto produto) {

        String sql = "INSERT INTO PRODUTO(descricao,valor) values(?,?) ";

        // constroi preparedstatement com o sql
        try {
            PreparedStatement preparador = con.prepareStatement(sql);

            preparador.setString(1, produto.getDescricao());
            preparador.setString(2, produto.getValor());

            preparador.execute();
            preparador.close();

            System.out.println("Cadastrado com sucesso ! ");
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public Produto buscarPorId(Integer id) {

        String sql = "SELECT * FROM PRODUTO WHERE idproduto=?";
        try {

            //prepara e executa o sql
            PreparedStatement preparadorSQL = con.prepareStatement(sql);
            preparadorSQL.setInt(1, id);

            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            if (resultado.next()) {

                Produto pro = new Produto();

                //Atribuindo dados do resultado no objeto cliente
                pro.setId(id);
                pro.setDescricao(resultado.getString("descricao"));
                pro.setValor(resultado.getString("valor"));
                
                preparadorSQL.close();
                return pro;
            } else {
                return null;
            }
        } catch (SQLException ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void excluir(int id) {

        String sql = "DELETE FROM produto WHERE idproduto = ?";

        try {
            PreparedStatement preparadorSQL = con.prepareStatement(sql);
            preparadorSQL.setInt(1,id);

            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Produto> buscarTodos() {
        String sql = "SELECT * FROM PRODUTO ORDER BY idproduto";
        List<Produto> lista = new ArrayList<>();
        try {
            PreparedStatement preparadorSQL = con.prepareStatement(sql);

            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();

            while (resultado.next()) {
                //Instancia de cliente
                Produto pro = new Produto();

                //Atribuindo dados do resultado no objeto cliente
                pro.setId(resultado.getInt("idproduto"));
                pro.setDescricao(resultado.getString("descricao"));
                pro.setValor(resultado.getString("valor"));

                //Adicionando cliente na lista
                lista.add(pro);
            }

            preparadorSQL.close();
            return lista;
        } catch (SQLException ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

//    Método que irá alterar os dados
    public void alterar(Produto produto) {

        String sql = "UPDATE PRODUTO SET descricao = ?, valor = ? WHERE idproduto = ? ";

        // constroi preparedstatement com o sql
        try {
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, produto.getDescricao());
            preparador.setString(2, produto.getValor());
            preparador.setInt(3, produto.getId());

            preparador.execute();
            preparador.close();

            System.out.println("Alterado com sucesso ! ");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
