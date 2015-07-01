/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larimaia;

import Dao.ClienteDAO;
import Model.Cliente;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class AlterarClienteController implements Initializable {
    @FXML
    private TextField nomeT, telefoneT, emailT, idClienteT, idExcluir; 
    
    @FXML
    private TableView tabela;
     
    @FXML
    private TableColumn colunaId, colunaNome, colunaTelefone, colunaEmail;
    
    
    @FXML
    public void salvar(ActionEvent event){
        Cliente cli = new Cliente();
        ClienteDAO cliDAO = new ClienteDAO();
        
        cli.setNome(nomeT.getText());
        cli.setTelefone(telefoneT.getText());
        cli.setEmail(emailT.getText());
        
        cliDAO.salvar(cli);
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliDAO.buscarTodos());
    
        tabela.setItems(clientes);
        
        nomeT.setText(null);
        telefoneT.setText(null);
        emailT.setText(null);
        
    }
    
    public void busca(ActionEvent event){
         Cliente cli = new Cliente();
         ClienteDAO cliDAO = new ClienteDAO();
         
         cli = cliDAO.buscarPorId(Integer.parseInt(idClienteT.getText()));
         
         nomeT.setText(cli.getNome());
         telefoneT.setText(cli.getTelefone());
         emailT.setText(cli.getEmail());
         
         
    }
    
    @FXML
    public void alterar(ActionEvent event){
        Cliente cli = new Cliente();
        ClienteDAO cliDAO = new ClienteDAO();
        
        cli.setNome(nomeT.getText());
        cli.setTelefone(telefoneT.getText());
        cli.setEmail(emailT.getText());
        cli.setId(Integer.parseInt(idClienteT.getText()));
        cliDAO.alterar(cli);
    
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliDAO.buscarTodos());
    
        tabela.setItems(clientes);
        
        nomeT.setText(null);
        telefoneT.setText(null);
        emailT.setText(null);
        idClienteT.setText(null);
    }
    
    @FXML
    public void excluir(ActionEvent event){
         ClienteDAO cliDAO = new ClienteDAO();
         
         cliDAO.excluir(Integer.parseInt(idExcluir.getText()));
         
         ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliDAO.buscarTodos());
    
        tabela.setItems(clientes);
        
        idExcluir.setText(null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ClienteDAO clidao = new ClienteDAO();
       
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
       
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clidao.buscarTodos());
    
        tabela.setItems(clientes);
        
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
    }    
    
}
