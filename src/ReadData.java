import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadData {
    private FileInputStream images;
    private FileInputStream labels;
    private boolean tracing = false;
    public Image [] totalImageData = new Image[60000];
    public int [] totalLabels = new int[60000];
    public ReadData(String imageFileName, String labelFileName, boolean debug) throws IOException{
        this.images = new FileInputStream(imageFileName);
        this.labels = new FileInputStream(labelFileName);
        for (int i = 0; i < 4; i++){
            System.out.println(readInt(images));
        }
        for (int i = 0; i < 2; i++){
            System.out.println(readInt(labels));
        }
        System.out.println("loading data.... this may take a couple minutes");
        for (int i = 0; i < totalImageData.length; i++){
            if (debug && i == 100){
                break;
            }
            totalImageData[i] = getOneImage(i);
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

    public Image getImage (int index){
        return totalImageData[index];
    }

    //same for labels
    public int getLabel(int index){
        return totalLabels[index];
    }



    //getter method for dimension of training data in case bc i dont like hard coded stuff
    public int getDimenson(){
        return this.totalImageData.length;
    }

    public class Image {
        int index;
        int [] raw;
        public Image(int [] raw, int index) throws IOException{
            this.raw = raw;
            this.index = index;
        }

        public int getIndex(){
            return this.index;
        }


        public double sum(){
            double total = 0;
            for (int i = 0; i < this.raw.length; i++){
                total+=this.raw[i];
            }
            return total;
        }

        public Image getImage(int index){
            return totalImageData[index];
        }

        public String toString(){
            String result = "";
            for(int r = 0;r<28;r++){
                for(int c = 0; c<28; c++){
                    int index = 28*r + c;
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

    public Image getOneImage(int index) throws IOException{
        int [] arr = new int [784];
        for(int i = 0; i<784; i++){
            arr[i] = images.read();
        }
//        System.out.println("index " + index);
//        System.out.println("array: ");
//        for(int i = 0; i < arr.length; i++){
//            System.out.println(arr[i]);
//        }
        Image result = new Image(arr, index);
        return result;
    }

    public double distance(Image i1, Image i2){
//        return Math.pow(i1.sum() - i2.sum(), 2);
        double result = 0;
        for (int i = 0; i < i1.raw.length; i++){
//            result += Math.sqrt(Math.abs(Math.pow(i1.raw[i], 2) - Math.pow(i2.raw[i], 2)));
            result += Math.sqrt(Math.abs(Math.pow(i1.raw[i], 2) - Math.pow(i2.raw[i], 2)));
            //result += Math.pow(i1.raw[i] -i2.raw[i], 2);
        }
        return result/i1.raw.length;
    }

    public boolean check(int actual, int test){
        boolean result = (actual == test);
        return result;
    }

    public static void main(String[] args) {
        try{
            ReadData train = new ReadData("train-images.idx3-ubyte", "train-labels.idx1-ubyte", false);
            ReadData test = new ReadData("t10k-images.idx3-ubyte", "t10k-labels.idx1-ubyte", false);
//            for (int i = 0; i < 50; i++){
//                System.out.print(train.totalImageData[i]);
//                System.out.println("label :" + train.totalLabels[i] + "\n");
//            }
            KNN attempt = new KNN(100, train);
            int totalCorrect = 0;
            int total = 10000;
            for (int i = 0; i < total; i++){
                int predicted = attempt.findLabel(test.totalImageData[i]);
                int actual = test.totalLabels[i];
                //System.out.println("actual: " + actual + " predicted: " + predicted);
                if (test.check(actual, predicted)){
                    totalCorrect++;

                }
                int inc = total;
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

            System.out.println(((totalCorrect*1.0)/total)* 100 + "%");
//            System.out.println(train.totalImageData[0]);
//            System.out.println(test.findLabel(train.totalImageData[0]));
//            System.out.println(train.totalLabels[0]);

        }
        catch(IOException e){
            System.out.println("RIP");
        }


    }
}


