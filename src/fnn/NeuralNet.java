package fnn;

import java.util.Arrays;

/**
 * 1-hidden neural network for _classification_
 *
 */

public class NeuralNet {
  private float[][] trainingData;
  private float[][] testingData;
  private int nbInstances;
  private int nbFeatures;
  private int nbClasses;
  private int batchSize;
  
  private float[][] X_train, Y_train, X_test, Y_test, W1, W2, b1, b2;
  private float eta = 0.01f;

  /**
   * Class constructor
   * @param data      data read from input file
   * @param batchSize number of instances in each batch for training
   * @param K         number of output class (e.g. Iris dataset => 3)
   */
  public NeuralNet(float[][] data, int batchSize, int K /*nb classes*/) {
    nbInstances = data.length;
    nbFeatures  = data[0].length;
    this.batchSize = batchSize;
    this.nbClasses = K;
    
    int trainingSize = (int) (nbInstances*0.75);
    int testingSize  = nbInstances-trainingSize;
    trainingData = new float[trainingSize][nbFeatures];
    testingData  = new float[testingSize][nbFeatures];
    
    // Copy data into training & testing set
    for (int i = 0; i < trainingSize; i++)
      for (int j = 0; j < data[0].length; j++)
        trainingData[i][j] = data[i][j];
    
    for (int i = 0; i < testingSize; i++)
      for (int j = 0; j < data[0].length; j++)
        testingData[i][j] = data[i+trainingSize][j];
    
    
    X_train = new float[this.nbFeatures-1][this.batchSize];  // -1 because the last column is the label
    Y_train = new float[this.nbClasses][this.batchSize];
    
    X_test  = new float[this.nbFeatures-1][this.batchSize];
    Y_test  = new float[this.nbClasses][this.batchSize];
    
    W1 = new float[3][4]; NNLib.initMatrix(W1);
    b1 = new float[3][1]; for (int i = 0; i < b1.length; i++) Arrays.fill(b1[i], 0.f);
    
    W2 = new float[3][3]; NNLib.initMatrix(W2);
    b2 = new float[3][1]; for (int i = 0; i < b2.length; i++) Arrays.fill(b2[i], 0.f);
  }
  
  @SuppressWarnings("unused")
  private void printData(float[][] data){
    for (int i = 0; i < data.length;i++){
      for (int j = 0; j < data[0].length; j++)
        System.out.print(data[i][j] + " ");
      System.out.println();
    }
  }
  
  /**
   * Create one-hots vector for classification
   * @param k target      class to set to 1
//   * @param batchInstance current data point in Y (a column index)
   * @param Y             matrix of one-hots
   */
  private void createOneHot(int k, int indexInBatch, float[][] Y){
    if (k > Y.length) throw new RuntimeException("There is only " + Y.length + " possible classes");
    /* TODO Part 3, Q.1 */
  }
  
  /**
   * Load one instance of data
   * Separate features and labels #dataIndex in X and Y
   * @param dataSet   training or testing
   * @param X         matrix for inputs
   * @param Y         matrix for outputs
   * @param dataIndex index in dataSet of data to copy
   */
  private void loadAttributesAndLabels(float[][] dataSet, float[][] X, float[][] Y, int dataIndex, int batchSize){
    // 1. Load data attributes in X
    int i = 0; // index to load the features (data[ex][i] => feature #i of instance #ex)
    int indexInBatch = dataIndex%batchSize;
    /* TODO Part 3, Q.2 */
    
    // 2. Load data labels in Y (create a one-hot for class prediction)
    createOneHot((int) dataSet[dataIndex][i], indexInBatch, Y);
  }
  
  /**
   * Shuffle the set of training data
   * Fisher–Yates shuffle :
   * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
   * Note : NNLib.rnd.nextInt(max) returns an integer in [0;max[
   */
  private void shuffleTrainingData() {
    /* TODO Part 4, Q.1 */
  }
  
  /**
   * Feed the testing set to the model and measure error / accuracy
   * @returns cost on testing set
   */
  public float testPrediction() {
    int currentDataIndex = 0;
    float cost = 0.f;
    int countPredictions = 0;
    
    /* TODO Part 4, Q.3 */
    
    cost /= testingData.length;
    float accuracy = 100.f*countPredictions/testingData.length;
    System.out.println("  CE cost on test data: "  + cost);
    System.out.println("  Accuracy on test data: " + accuracy);
    return cost; //accuracy;
  }
  
  /**
   * Perform 1 epoch of training
   *  1/ load a minibatch of data into X_train and Y_train
   *  2/ forward pass
   *  3/ compute the gradient of the loss
   *  4/ update the parameters
   */
  private float trainingEpoch(){
    int seenTrainingData = 0;
    @SuppressWarnings("unused")
    float trainingError  = 0.f;
    float testingError   = 0.f;
    
    shuffleTrainingData(); // shuffle the data before training
    
    /* TODO Part 4, Q.2 */
    
    return testingError;
  }
  
  /**
   * Train the neural network
   * @param nbEpoch number of epoch to run
   */
  public void train(int nbEpoch){
    String trainingProgress = "";
    for (int e = 0; e < nbEpoch; e++){
      System.out.println(" [ Epoch " +e+ "]");
      trainingProgress += e + "," + trainingEpoch() + "\n";
    }
    // Export the error per epoch in output file
    DataLib.exportDataToCSV("training.out", trainingProgress);
  }

}
