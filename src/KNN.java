
import java.util.Comparator;
import java.util.PriorityQueue;

public class KNN {
    private int numNeighbors;
    private ReadData data;
    public KNN(int numNeighbors, ReadData data){
      
         this.numNeighbors = numNeighbors;
        this.data = data;
    }
        public int findLabel(Image letter){/*ik this is a bad name feel free to rename*/
            
        //create priority queue to hold k nearest neighbors
        // I want this to be a MAX queue but not sure if it is so might need to switch ordering
        
        // this is just the comparator for the prioriy queue
        Comparator c = new Comparator (){
            public int compare (Object a, Object b){
              if ((a instanceof Image )&& (b instanceof Image))return compareImage(a,b);
              else throw new ClassCastException(); 
                
            }
            
            public int compareImage(Image a, Image b){
                double distA = letter.distance(a);
                double distB = letter.distance(b);
                
                if (distA<distB)return -1;//A is closer to B therefore a= -1
                else if (distA == distB)return 0;//distances are equal
                else return 1; //a is greater than b so farther away                
            }
            
        };
        PriorityQueue<Image> queue = new PriorityQueue (numNeighbors , c);
        // for each image find distance
        
        for(int i = 0; i < data.getDimension(); i++){
            
            if(i<numNeighbors){
                queue.add(data.getImage(i));
                continue;
                //this is just to make sure there is stuff in the PQ
            }
          double dist = letter.distance(data.getImage(i));
             
        //if dist< queue.max, add it to queue and get rid of max
        if(dist<letter.distance(queue.peek())){
          queue.add(data.getImage(i));
          queue.poll();
        }
        
        }
        //find majority of nearest neighbors to pick label
         int[] counts = new int[numNeighbors];
        for(Image i: queue){
            int label = data.getLabel(i.getIndex());
            assert label>=0;
            assert label <=9;
           
               counts[label] ++;
       
         }  
        //find the most common label
        int majority = 0;
        for(int i= 0; i< 10; i++){
            if(counts[majority]<counts[i]) majority = i;
        }
        
        
       return majority;
    }
    // using the distance function within the ReadData class


}
