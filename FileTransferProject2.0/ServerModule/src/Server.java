import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Properties;

public class Server {

    public void startServer(){

        ServerConfigurations serverConfig = configureServer();

        DisplayFileTree.AnalyzeDirectory(serverConfig.fileDirectory);


    }

    private ServerConfigurations configureServer(){

        JOptionPane.showMessageDialog(
                null,
                "Welcome to the File Transfer Server configurations set-up! \n\n Press enter or hit the ''OK'' button.",
                "Welcome!",
                JOptionPane.INFORMATION_MESSAGE);


        ServerConfigurations config = new ServerConfigurations();

        config.fileDirectory = getRootDirectory();

        System.out.println(config.fileDirectory);
        config.remotePort = getRemotePort();


        System.out.println("Remote port chosen: " + config.remotePort);

        System.out.println("The root of all directories: " + config.fileDirectory.toString());

        return config;

    }

    private Path getRootDirectory(){

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        while(true){

            int resultCode = fileChooser.showOpenDialog(null);

            if(resultCode == JOptionPane.NO_OPTION){

                int exitResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
                System.out.println(exitResult);

                if(exitResult == 0){
                    System.exit(0);
                }
            }

            //System.out.println(resultCode);

            if(resultCode == JFileChooser.APPROVE_OPTION){
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();

                System.out.println("Is it a directory? " + Files.isDirectory(Paths.get(fileName)));

                break;
            }
        }

        return Paths.get(fileChooser.getSelectedFile().getAbsolutePath());

    }
    private int getRemotePort(){

        int remotePort;

        while(true){
            String port = JOptionPane.showInputDialog("Enter a non-occupied remote port to listen for incoming connections:");

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



