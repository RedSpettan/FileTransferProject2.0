import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here





        Path pathToDirectory = Paths.get("C:\\Users\\Erik\\Desktop\\TransferTest");

        String[] stringPaths = new String[0];

        List<Path> result = new ArrayList<>();

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(pathToDirectory)) {

            for(Path entry: stream){
                result.add(entry);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        stringPaths = new String[result.size()];

        for(Path entry: result){


            stringPaths[result.indexOf(entry)] = entry.toString();

            //System.out.println(entry.toString());
            //System.out.println(Files.isDirectory(entry));
            try {
                System.out.println(Files.probeContentType(entry));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        for(String text: stringPaths){

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




        }



    }
}
