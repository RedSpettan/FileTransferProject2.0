import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInformation {


    public static String[] AnalyzeDirectory(Path directory){

        //Check if the given method parameter exist
        if(!Files.isDirectory(directory) ){
            System.exit(0);
        }

        //Store the directory path
        Path pathToDirectory = directory;

        //Array list used to store file results
        List<Path> result = new ArrayList<>();

        //Try to open a directory stream to the given directory
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(pathToDirectory)) {

            //Store all entries found in the directory stream
            for(Path entry: stream){
                result.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] paths = new String[result.size()];

        for(Path entry: result){

            paths[result.indexOf(entry)] = entry.toString();

            System.out.println(entry.toString());
            //System.out.println(Files.isDirectory(entry));
            /*try {
                System.out.println(Files.probeContentType(entry));
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }

        return paths;






    }


    public static String DisplayFileExtension(Path filePath){


        if(Files.notExists(filePath) || Files.isDirectory(filePath)){
            return null;
        }

        String localFilePath = filePath.toString();

        int lastDot = localFilePath.lastIndexOf(".");

        String fileExtension = localFilePath.substring(lastDot + 1);

        return fileExtension;

        /*for(String text: paths){

            //System.out.println(Files.isDirectory(Paths.get(text)));

            if(!Files.isDirectory(Paths.get(text))){
                System.out.println(text);

                int lastSlashIndex = text.lastIndexOf('\\');
                //System.out.println(lastSlashIndex);

                String fileName = text.substring(lastSlashIndex + 1);
                System.out.println(fileName);


                int lastDot = text.lastIndexOf(".");

                String fileExtension = text.substring(lastDot + 1);
                System.out.println(fileExtension);

            }

        }*/

    }

}
