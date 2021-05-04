import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadData {
    private FileInputStream images;
    private FileInputStream labels;
    private boolean tracing = false;

    public ReadData(String imageFileName, String labelFileName) throws IOException{
        this.images = new FileInputStream(imageFileName);
        this.labels = new FileInputStream(labelFileName);
    }

    //Mr. Paige's method
    private int readInt(InputStream input) throws IOException{
        int result = 0;
        for (int i = 0; i < 4; i++){
            int value = input.read();
            result = 256 * result + value;
        }
        return result;

    }
    public void getAllImages() throws IOException{
        getImages(60000);
    }

    public void getImages(int numImages) throws IOException{
        for (int i = 0; i < 4; i++){
            readInt(images);
        }
        for (int i = 0; i < 2; i++){
            readInt(labels);
        }

        for(int i = 0; i<numImages; i++){
            for(int r = 0;r<28;r++){
                for(int c = 0; c<28; c++){
                    System.out.printf("%4s", images.read());
                }
                System.out.println();
            }
            System.out.println("label: " + labels.read());
            System.out.println();

        }
    }

    public int[][] returnImage(int numImages) throws IOException{
        int [] result = new int [784];
        for (int i = 0; i < 4; i++){
            readInt(images);
        }
        for (int i = 0; i < 2; i++){
            readInt(labels);
        }
        for (int i = 0; i < 784; i++){
            result[i] = images.read();
        }
        int [][] end = {result, [labels.read()]};
        return
    }


    public double manhattanDistance(){

    }

    public static void main(String[] args) {
        try{
            ReadData test = new ReadData("t10k-images.idx3-ubyte", "t10k-labels.idx1-ubyte");

            for (int i = 0; i < 60000; i++){

            }

        }
        catch(IOException e){
            System.out.println("RIP");
        }


    }
}


