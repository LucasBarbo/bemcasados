
package larimaia;

import Dao.ProdutoDAO;
import Dao.TipoEventoDAO;
import Model.Produto;
import Model.TipoEvento;
import java.net.URL;
import java.sql.SQLException;
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


public class TelaTipoController implements Initializable {

    @FXML
    private TextField descricaoT, idTipoT, idExcluir; 
    
    @FXML
    private TableView tabela;
     
    @FXML
    private TableColumn colunaId, colunaDescricao;
    
    
    @FXML
    public void salvar(ActionEvent event){
        TipoEvento tipoE = new TipoEvento();
        
        tipoE.setDescricao(descricaoT.getText());
               
               
        TipoEventoDAO tipo = new TipoEventoDAO();
        
        tipo.salvar(tipoE);
        
        ObservableList<TipoEvento> tipos = FXCollections.observableArrayList(tipo.buscarTodos());
    
        tabela.setItems(tipos);
        
        descricaoT.setText(null);
       
        
        
    }
    
    public void busca(ActionEvent event){
        TipoEvento tipoE = new TipoEvento();
         
         TipoEventoDAO tipo = new TipoEventoDAO();
         
         tipoE = tipo.buscarPorId(Integer.parseInt(idTipoT.getText()));
         
         descricaoT.setText(tipoE.getDescricao());
         
        
         
         
    }
    
    @FXML
    public void alterar(ActionEvent event){
        TipoEvento tipoE = new TipoEvento();
        
        tipoE.setId(Integer.parseInt(idTipoT.getText()));
        tipoE.setDescricao(descricaoT.getText());
              
        TipoEventoDAO tipo = new TipoEventoDAO();
        
        tipo.alterar(tipoE);
        
        ObservableList<TipoEvento> tipos = FXCollections.observableArrayList(tipo.buscarTodos());
    
        tabela.setItems(tipos);
        
        descricaoT.setText(null);
       
    }
    
    @FXML
    public void excluir(ActionEvent event) throws SQLException{
         TipoEventoDAO tipo = new TipoEventoDAO();
         TipoEvento tipoE = new TipoEvento();
                  
         tipoE.setId(Integer.parseInt(idExcluir.getText()));
         
         tipo.excluir(tipoE);
         
         ObservableList<TipoEvento> tipos = FXCollections.observableArrayList(tipo.buscarTodos());
    
        tabela.setItems(tipos);
        
        idExcluir.setText(null);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     TipoEventoDAO tipo = new TipoEventoDAO();
        
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
       
       
        ObservableList<TipoEvento> tipos = FXCollections.observableArrayList(tipo.buscarTodos());
    
        tabela.setItems(tipos);
        
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
    }    
    
}
