/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.TipoEvento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoEventoDAO {

    Connection con;

    //construtor
    public TipoEventoDAO() {
        con = ConexaoUtil.getConnection();
    }

    public void salvar(TipoEvento tipoevento) {

        String sql = "INSERT INTO tipoevento(descricao)values(?) ";
        try {
            PreparedStatement preparador = con.prepareStatement(sql);

            preparador.setString(1, tipoevento.getDescricao());
            preparador.execute();
            preparador.close();

            System.out.println("Cadastrado com sucesso ! ");
        } catch (SQLException e) {
            //Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);

            e.printStackTrace();
        }
    }

    //traz todos os eventos cadastrados
    public List<TipoEvento> buscarTodos() {

        String sql = "SELECT * FROM tipoevento ORDER BY idtipoevento";
        List<TipoEvento> lista = new ArrayList<>();
        try {
            PreparedStatement preparadorSQL = con.prepareStatement(sql);

            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();

            while (resultado.next()) {
                //Instancia de cliente
                TipoEvento event = new TipoEvento();

                //Atribuindo dados do resultado no objeto TipoEvento
                event.setId(resultado.getInt("idtipoevento"));
                event.setDescricao(resultado.getString("descricao"));

                //Adicionando Eventos na lista
                lista.add(event);
            }

            preparadorSQL.close();
            return lista;
        } catch (SQLException ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void excluir(TipoEvento tipoevento) {
        String sql = "DELETE FROM tipoevento WHERE idtipoevento = ?";

        try {
            PreparedStatement preparadorSQL = con.prepareStatement(sql);
            preparadorSQL.setInt(1, tipoevento.getId());

            preparadorSQL.execute();
            preparadorSQL.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoEvento buscarPorId(Integer id) {

        String sql = "SELECT * FROM tipoevento WHERE idtipoevento=?";
        try {

            //prepara e executa o sql
            PreparedStatement preparadorSQL = con.prepareStatement(sql);
            preparadorSQL.setInt(1, id);

            //Armazenando Resultado da consulta
            ResultSet resultado = preparadorSQL.executeQuery();
            if (resultado.next()) {

                TipoEvento event = new TipoEvento();

                //Atribuindo dados do resultado no objeto cliente
                event.setId(id);
                event.setDescricao(resultado.getString("descricao"));

                preparadorSQL.close();
                return event;
            } else {
                return null;
            }
        } catch (SQLException ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void alterar(TipoEvento tipoevento) {

        String sql = "UPDATE tipoevento SET descricao = ? WHERE idtipoevento = ? ";

        // constroi preparedstatement com o sql
        try {
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, tipoevento.getDescricao());
            preparador.setInt(2, tipoevento.getId());

            preparador.execute();
            preparador.close();

            System.out.println("Alterado com sucesso ! ");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
