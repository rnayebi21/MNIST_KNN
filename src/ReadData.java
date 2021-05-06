import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadData {
    private FileInputStream images;
    private FileInputStream labels;
    private boolean tracing = false;
    public Image [] totalImageData = new Image[60000];
    public int [] totalLabels = new int[60000];
    public ReadData(String imageFileName, String labelFileName) throws IOException{
        this.images = new FileInputStream(imageFileName);
        this.labels = new FileInputStream(labelFileName);
        for (int i = 0; i < 4; i++){
            readInt(images);
        }
        for (int i = 0; i < 2; i++){
            readInt(labels);
        }
        System.out.println("loading data.... this may take a couple minutes");
        for (int i = 0; i < totalImageData.length; i++){
            totalImageData[i] = getOneImage();
            totalLabels[i] = labels.read();
            int inc = 6000;
            if(i%inc ==0){
                if (i == inc){
                    System.out.print("10%...");
                }
                else if (i == 2*inc){
                    System.out.print("20%...");
                }
                else if (i == 3*inc){
                    System.out.print("30%...");
                }
                else if (i == 4*inc){
                    System.out.print("40%...");
                }
                else if (i == 5*inc){
                    System.out.print("50%...");
                }
                else if (i == 6*inc){
                    System.out.print("60%...");
                }
                else if (i == 7*inc){
                    System.out.print("70%...");
                }
                else if (i == 8*inc){
                    System.out.print("80%...");
                }
                else if (i == 9*inc){
                    System.out.print("90%...");
                }
            }
        }
        System.out.println("done!");
    }

    public class Image {
        int [] raw;
        public Image(int [] raw) throws IOException{
            this.raw = raw;
            for (int i = 0; i < 784; i++){
                raw[i] = images.read();
            }
        }

        public double sum(){
            double total = 0;
            for (int i = 0; i < this.raw.length; i++){
                total+=this.raw[i];
            }
            return total;
        }

        public String toString(){
            String result = "";
            for(int r = 0;r<28;r++){
                for(int c = 0; c<28; c++){
                    int index = 28*r + c;
//                    String update =
//                    result = String.format(update, "%4s");
//                    result += this.raw[index] + "\t\t";
                    result += String.format("%3d",this.raw[index] );
                }
                result+= "\n";
            }
            return result;
        }
    }

    //Mr. Paige's method
    public int readInt(InputStream input) throws IOException{
        int result = 0;
        for (int i = 0; i < 4; i++){
            int value = input.read();
            result = 256 * result + value;
        }
        return result;

    }

//    public void getAllImages() throws IOException{
//        getImages(60000);
//    }

    //for printing things out
    public void getImages(int numImages) throws IOException{

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

    public Image getOneImage() throws IOException{
        int [] arr = new int [784];
        for(int i = 0; i<784; i++){
            arr[i] = images.read();
        }
        Image result = new Image(arr);
        return result;
    }
/*
    public int[][] returnImage() throws IOException{
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
        int [] thing = {labels.read()};
        int [][] end = {result, thing};
        return end;
    }
*/

    public double distance(Image i1, Image i2){
//        return Math.pow(i1.sum() - i2.sum(), 2);
        double result = 0;
        for (int i = 0; i < i1.raw.length; i++){
            result += Math.sqrt(Math.abs(Math.pow(i1.raw[i], 2) - Math.pow(i2.raw[i], 2)));
        }
        return result/i1.raw.length;
    }

    public static void main(String[] args) {
        try{
//            ReadData test = new ReadData("t10k-images.idx3-ubyte", "t10k-labels.idx1-ubyte");
            ReadData train = new ReadData("train-images.idx3-ubyte", "train-labels.idx1-ubyte");
            System.out.println(train.totalImageData[1]);
            System.out.println("label: " + train.totalLabels[1]);
            System.out.println();
            System.out.println(train.totalImageData[751]);
            System.out.println("label: " + train.totalLabels[751]);
//            System.out.println("748: " + train.totalLabels[748]);
//            System.out.println("749: " + train.totalLabels[749]);
//            System.out.println("750: " + train.totalLabels[750]);
//            System.out.println("752: " + train.totalLabels[752]);
//            System.out.println("753: " + train.totalLabels[753]);
//            System.out.println("754: " + train.totalLabels[754]);
        }
        catch(IOException e){
            System.out.println("RIP");
        }


    }
}
// this is a testy test


