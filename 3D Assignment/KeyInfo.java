/**
 * Minimal implementation to support keyframe class.
 * Future work: Could be separated out with more parameters supplied as part of the key data
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (21/11/2013)
 */
  
public class KeyInfo {
  private double kf;
  private double value;
  
  public KeyInfo(double f, double v) {
    kf = f;
    value = v;
  }
 
  public double getKF() { return kf; }
  public double getValue() { return value; }
}

