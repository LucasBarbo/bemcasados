
package larimaia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InicioController implements Initializable {

    
    @FXML
    public void btnPedido(ActionEvent event) throws Exception{
      Parent telaPedido = FXMLLoader.load(getClass().getResource("Pedido.fxml"));
        
        Scene scene = new Scene(telaPedido);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void btnRelatorio(ActionEvent event) throws Exception{
        Parent telaRelatorio = FXMLLoader.load(getClass().getResource("ConsultaPedido.fxml"));
        
        Scene scene = new Scene(telaRelatorio);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void btnCadastro(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("crud.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
