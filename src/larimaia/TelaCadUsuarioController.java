/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larimaia;

import Dao.UsuarioDAO;
import Model.Usuario;
import Service.ServiceException;
import Service.UsuarioService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class TelaCadUsuarioController implements Initializable {

    @FXML
    private TextField loguinT, idBuscaT, idExcluir; 
    
    @FXML
    private PasswordField senhaT;
    
    @FXML
    private TableView tabela;
     
    @FXML
    private TableColumn colunaId, colunaNome;
    
    
    @FXML
    public void salvar(ActionEvent event) throws ServiceException, SQLException{
        Usuario usu = new Usuario();
        
       usu.setNome(loguinT.getText());
       usu.setSenha(senhaT.getText());
                        
        
        UsuarioService usuarioS = new UsuarioService();
               
        usuarioS.salvar(usu);
        
        UsuarioDAO usuD = new UsuarioDAO();
        
        ObservableList<Usuario> usus = FXCollections.observableArrayList(usuD.buscarTodos());
    
        tabela.setItems(usus);
        
        loguinT.setText(null);
        senhaT.setText(null);
        
        
    }
    
    public void busca(ActionEvent event) throws SQLException{
        Usuario usu = new Usuario();
        UsuarioDAO usuD = new UsuarioDAO();
        
        usu = usuD.buscarPorId(Integer.parseInt(idBuscaT.getText()));
        
        loguinT.setText(usu.getNome());
        senhaT.setText(usu.getSenha());
        
         
    }
    
    @FXML
    public void alterar(ActionEvent event) throws ServiceException, SQLException{
        Usuario usu = new Usuario();
        
        
        usu.setId(Integer.parseInt(idBuscaT.getText()));
        usu.setNome(loguinT.getText());
        usu.setSenha(senhaT.getText());
        
               
        UsuarioService usuS = new UsuarioService();
        
        usuS.alterar(usu);
        UsuarioDAO usuD = new UsuarioDAO();
        
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(usuD.buscarTodos());
    
        tabela.setItems(usuarios);
        
        loguinT.setText(null);
        senhaT.setText(null);
    }
    
    @FXML
    public void excluir(ActionEvent event) throws SQLException{
         UsuarioDAO usuD = new UsuarioDAO();
         
         usuD.excluir(Integer.parseInt(idExcluir.getText()));
         
         ObservableList<Usuario> usuarios = FXCollections.observableArrayList(usuD.buscarTodos());
    
        tabela.setItems(usuarios);
        
        idExcluir.setText(null);
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuD = new UsuarioDAO();
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
       
       
       
        ObservableList<Usuario> usuarios;
        try {
            usuarios = FXCollections.observableArrayList(usuD.buscarTodos());
            tabela.setItems(usuarios);
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
    }    
    
    
}
