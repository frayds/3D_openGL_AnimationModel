
public class AnimationScene {

  public static final int ROBOT_PARAM1 = 0;
  public static final int ROBOT_PARAM2 = 1;
  public static final int ROBOT_PARAM3 = 2;
  public static final int ROBOT_PARAM4 = 3;
  public static final int ROBOT_PARAM5 = 4;
  public static final int ROBOT_PARAM6 = 5;
  public static final int ROBOT_PARAM7 = 6;
  public static final int ROBOT_PARAM8 = 7;
  public static final int ROBOT_PARAM9 = 8;
  public static final int ROBOT_PARAM10= 9;
  public static final int ROBOT_PARAM11 =10;
  public static final int ROBOT_PARAM12 =11;

  public static final int CUBE_PARAM  = 12;

  
  public static final int MAX_PARAMS = 13;
  private Anim[] param;
  private int numParams;
  private double globalStartTime, localTime, repeatTime, savedLocalTime; 
    
  /**
   * Constructor.
   *
   * @param keys List of key info, i.e. list of pairs {key frame value, key parameter value}
   */    
  public AnimationScene() {
    /* I declare that this code is my own work */
    param = new Anim[MAX_PARAMS];
    param[ROBOT_PARAM1] = create(0.0, 6.0, true, true,   // robot moveprocess1 move x co.
                                new double[]{0.0,0.0,1.0,10.3}); 
    param[ROBOT_PARAM2] = create(0.0, 6.0, true, true,  // robot moveprocess1 move y co.
                               new double[]{0.0,0.0,0.2,-3,0.5,-1,0.75,-2,1.0,1.5});
    param[ROBOT_PARAM3] = create(7.0, 4, true, true,  // robot moveprocess2 move rotate arm
                                new double[]{0.0,0.0, 0.25,-90,0.75,-90,1,0});
    param[ROBOT_PARAM4] = create(6.0, 5.0, true, true,  // robot moveprocess2 move z co.
                               new double[]{0.0,0.0, 1,12});
    param[ROBOT_PARAM5] = create(7.5, 1, true, true,  //robot moveprocess2 move rotate body
                               new double[]{0.0,0.0, 1.0,90});
    param[ROBOT_PARAM6] = create(11, 10, true, true,  //robot moveprocess3 move z co.
                               new double[]{0.0,0.0, 1.0,12.5});
    param[ROBOT_PARAM7] = create(13, 5, true, true,  //robot moveprocess3 move rotate body
                               new double[]{0.0,0.0, 0.5,180,1.0,270});
    param[ROBOT_PARAM8] = create(13.5, 1, true, true,  //robot moveprocess3 move y co.
                               new double[]{0.0,0.0, 1.0,1.2});
    param[ROBOT_PARAM9] = create(17.5, 3, true, true,  //robot moveprocess3 move rotate body
                               new double[]{0.0,0.0, 0.5,180,1.0,270});
    param[ROBOT_PARAM10] = create(21, 9, true, true,  //robot moveprocess4 move z co.
                               new double[]{0.0,0.0, 1,-10.8});
    param[ROBOT_PARAM11] = create(25, 5, true, true,  //robot moveprocess4 move x co.
                               new double[]{0.0,0.0, 1,2.2});
    param[ROBOT_PARAM12] = create(25, 5, true, true,  //robot moveprocess4 move y co.
                               new double[]{0.0,0.0, 1,-1.5});
    param[CUBE_PARAM] = create(0.0, 30.0, true, true,  // cube move
                               new double[]{0.0,0.0, Math.sqrt(1f/6f)/2f,-6,Math.sqrt(2f/3f),12,1.0,0.0}); 
    /*Author <Yifan Pu><ypu6@sheffield.ac.uk>*/ 
    numParams = CUBE_PARAM+1;
    localTime = 0;
    savedLocalTime = 0;
    repeatTime = 30;
    globalStartTime = getSeconds();
  }
  
  public Anim create (double start, double duration, boolean pre, boolean post, double[] data) {
    KeyInfo[] k = new KeyInfo[data.length/2];
    for (int i=0; i<data.length/2; ++i) {
      k[i] = new KeyInfo(data[i*2], data[i*2+1]);
    }    
    return new Anim(start, duration, pre, post, k);
  }
  
  public void startAnimation() {
    globalStartTime = getSeconds() - savedLocalTime;
  }
  
  public void pauseAnimation() {
    savedLocalTime = getSeconds() - globalStartTime;
  }
  
  public void reset() {
    globalStartTime = getSeconds();
    savedLocalTime = 0;
    for (int i=0; i<numParams; ++i) {
      param[i].reset();
    }
  }
  
  private double getSeconds() {
    return System.currentTimeMillis()/1000.0;
  }
  
  /**
   * 
   */ 
  public void update() {
    localTime = getSeconds() - globalStartTime;
    if (localTime > repeatTime) {
      globalStartTime = getSeconds();
      localTime = 0;
      savedLocalTime = 0;
    }  
    for (int i=0; i<numParams; ++i) {
      param[i].update(localTime);
    }
  }

 /**
   * 
   *
   * @return The current parameter value
   */   
  public double getParam(int i) {
    if (i<0 || i>=numParams) {
      System.out.println("EEError: parameter out of range");
      return 0;
    }
    else {
      return param[i].getCurrValue();
    }
  }
  
  /**
   * Standard use of toString method
   * 
   * @return A string representing the key data
   */      
  public String toString() {
    String s = "Anim manager: ";
    return s;
  }


  
}
