/**
 * A class for maintaining a list of KeyInfo sequence of values. 
 * A KeyInfo sequence is supplied as a normalised sequence in the range 0.0 to 1.0.
 * The start and duration values anchor this in relation to global time.
 * The current implementation uses linear interpolation between pairs of key info data.
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (21/11/2013)
 */


  
public class Anim {
  private KeyInfo[] keys;  
  private double currValue;
  private double start, duration;
  private boolean preUse, postUse;

  /**
   * Constructor.
   *
   * @param keys List of key info, i.e. list of pairs {key frame value, key parameter value}
   */    
  public Anim(double start, double duration, boolean preUse, boolean postUse, KeyInfo[] keyInfo) {
    this.keys = keyInfo; 
    this.start = start;
    this.duration = duration;
    this.preUse = preUse;
    this.postUse = postUse;
    reset();
  }

  public void reset() {
    if (preUse) currValue = keys[0].getValue();
    else currValue = 0;
  }
  
  /**
   * 
   */ 
  public void update(double t) {
    if (t <= start) {
      if (preUse || t==start) currValue = keys[0].getValue();
      else currValue = 0;
    }
    else if (t >= start+duration) {
      if (postUse || t == start+duration) currValue = keys[keys.length-1].getValue();
      else currValue = 0;
    }
    else {
      double normalisedTime = (t-start)/duration;
      int k1 = findKeyFrameBefore(normalisedTime);
      currValue = linearInterpolation(normalisedTime, k1,k1+1);
    }
  }

 /**
   * 
   *
   * @return The current parameter value
   */   
  public double getCurrValue() {
    return currValue;
  }
  
  private int findKeyFrameBefore(double t) {
    int k1 = 0;
    while (k1<keys.length && keys[k1].getKF() < t)
      k1++;
    return k1-1;
  }
  
  private double linearInterpolation(double t, int k1, int k2) {
    double f1 = keys[k1].getKF();
    double f2 = keys[k2].getKF();
    double fraction = (t-f1)/(f2-f1);
    double p1 = keys[k1].getValue();
    double p2 = keys[k2].getValue();
    double p = p1 + fraction*(p2-p1);
    //System.out.println(f1+", "+f2+", "+fraction+ ", " + p1 + ", "+ p2 + ", "+ p);
    return p;
  }
  
  /**
   * Standard use of toString method
   * 
   * @return A string representing the key data
   */      
  public String toString() {
    String s = "Anim: ";
    return s;
  }

  
}
