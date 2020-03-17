package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// remember to add the bouncy castle library file
import javax.crypto.spec.SecretKeySpec;

import static java.security.Security.addProvider;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // add bouncy castle as security provider
        addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // setup variables and make filesafe directory of none exists
        String dir = System.getProperty("user.home") + "/" + "filesafe" + "/";
        FileUtils.makeDir(dir);
        char action = 's';

        // instruct user to put files in filesafe directory and prompt for strong password
        System.out.println("Place the files you want to encrypt inside " + dir);
        char[] pw = InputUtils.requireStrongPassword();

        // generate secret key with PBKDF
        SecretKeySpec secretKey = CryptoUtils.generateSecretKey(pw);

        // listen for user action
        while(action != 'f') {
            action = InputUtils.prompt("Select action e or d:")[0];
            // decrypt all
            if(action == 'd')
            for (String s : FileUtils.getAllFileNames(dir, "aes"))
                CryptoUtils.decrypt(s, secretKey);

            // encrypt all
            if(action == 'e')
            for (String s : FileUtils.getAllFileNamesWOExt(dir, "aes"))
                CryptoUtils.encrypt(s, secretKey);
        }

        // standard dummy code
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
