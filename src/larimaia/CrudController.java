
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


public class CrudController implements Initializable {
    

    
    @FXML
    public void cliente(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AlterarCliente.fxml"));
        
       Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    public void produto(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("telaProduto.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
    
  
    
    @FXML
    public void tipo(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("telaTipo.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
    
      @FXML
    public void usuario(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TelaCadUsuario.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
