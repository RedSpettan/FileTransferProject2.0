import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

    public void startServer(){

        ServerConfigurations serverConfig = configureServer();

        System.out.println("Here!");


    }

    private ServerConfigurations configureServer(){

        JOptionPane.showMessageDialog(
                null,
                "Welcome to the File Transfer Server configurations set-up! \n\n Press enter or hit the ''OK'' button.",
                "Welcome!",
                JOptionPane.INFORMATION_MESSAGE);


        ServerConfigurations config = new ServerConfigurations();

        config.fileDirectory = getRootDirectory();

        config.remotePort = getRemotePort();

        System.out.println("Remote port chosen: " + config.remotePort);

        System.out.println("The root of all directories: " + config.fileDirectory.toString());

        return config;

    }

    private Path getRootDirectory(){
        String directory;

        while (true){
            directory = JOptionPane.showInputDialog("Enter a root directory:");

            //System.out.println(directory);
            if(directory == null){
                System.exit(0);
            }

            if(directory.isEmpty()){
                JOptionPane.showMessageDialog(null,"ERROR: Enter something into the text field", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }else{
                Path rootDirectory = Paths.get(directory);

                if(!Files.isDirectory(rootDirectory)){
                    JOptionPane.showMessageDialog(null, "Please enter a valid root directory!", "ERROR", 0);

                    System.err.println("Please enter a valid root directory");

                }else{break;}

            }
        }

        return Paths.get(directory);
    }
    private int getRemotePort(){

        int remotePort;

        while(true){
            String port = JOptionPane.showInputDialog("Enter a non-occupied ");

            int parsedPort = Integer.parseInt(port);

            if(isThisPortAvailable(parsedPort)){
                remotePort = parsedPort;
                break;
            }
        }

        return remotePort;
    }


    private boolean isThisPortAvailable(int portToCheck){


        try{
            Socket socket = new Socket(InetAddress.getLocalHost(),portToCheck);

            socket.close();

            return false;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            //e.printStackTrace();
            return true;
        }

    }

    private class ServerConfigurations{

        private int remotePort;
        private Path fileDirectory;

        public ServerConfigurations() {
        }
    }

}



