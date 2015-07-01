/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larimaia;

import Dao.ClienteDAO;
import Dao.ProdutoDAO;
import Model.Cliente;
import Model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TelaProdutoController implements Initializable {
    
    @FXML
    private TextField descricaoT, valorT, idProdutoT, idExcluir; 
    
    @FXML
    private TableView tabela;
     
    @FXML
    private TableColumn colunaId, colunaDescricao, colunaValor;
    
    
    @FXML
    public void salvar(ActionEvent event){
        Produto produto = new Produto();
        
        produto.setDescricao(descricaoT.getText());
        produto.setValor(valorT.getText());
        
               
        ProdutoDAO pro = new ProdutoDAO();
        
        pro.salvar(produto);
        
        ObservableList<Produto> produtos = FXCollections.observableArrayList(pro.buscarTodos());
    
        tabela.setItems(produtos);
        
        descricaoT.setText(null);
        valorT.setText(null);
        
        
    }
    
    public void busca(ActionEvent event){
        Produto produto = new Produto();
         
         ProdutoDAO pro = new ProdutoDAO();
         
         produto = pro.buscarPorId(Integer.parseInt(idProdutoT.getText()));
         
         descricaoT.setText(produto.getDescricao());
         valorT.setText(produto.getValor());
        
         
         
    }
    
    @FXML
    public void alterar(ActionEvent event){
        Produto produto = new Produto();
        
        produto.setId(Integer.parseInt(idProdutoT.getText()));
        produto.setDescricao(descricaoT.getText());
        produto.setValor(valorT.getText());
        
               
        ProdutoDAO pro = new ProdutoDAO();
        
        pro.alterar(produto);
        
        ObservableList<Produto> produtos = FXCollections.observableArrayList(pro.buscarTodos());
    
        tabela.setItems(produtos);
        
        descricaoT.setText(null);
        valorT.setText(null);
    }
    
    @FXML
    public void excluir(ActionEvent event){
         ProdutoDAO pro = new ProdutoDAO();
         
         pro.excluir(Integer.parseInt(idExcluir.getText()));
         
         ObservableList<Produto> produtos = FXCollections.observableArrayList(pro.buscarTodos());
    
        tabela.setItems(produtos);
        
        idExcluir.setText(null);
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProdutoDAO pro = new ProdutoDAO();
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
       
       
        ObservableList<Produto> produtos = FXCollections.observableArrayList(pro.buscarTodos());
    
        tabela.setItems(produtos);
        
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
    }    
    
}
