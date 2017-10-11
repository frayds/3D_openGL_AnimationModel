 /**
 * This class stores a bounding box for a Mesh
 *
 * @author    Dr Steve Maddock
 * @version   2.0 (09/09/2011)
 */

public class BoundingBox implements Cloneable {

  private double[] min=new double[3];
  private double[] max=new double[3];
  private double[] range=new double[3];
  private double[] centre=new double[3];

  /**
   * Constructor. Sets attributes to default initial values.
   */    
  public BoundingBox(){
    min[0]=min[1]=min[2]=0.0;
    max[0]=max[1]=max[2]=0.0;
  }

  /**
   * Gets the minimum (x,y,z) of the bounding box.
   * 
   * @return  (x,y,z) representing the minimum bounds, where x is the 0th element of the 
   *          array, y is the array item with index 1, and z with index 2
   */  
  public double[] getMinimumBounds() {
    return min;
  }

  /**
   * Gets the maximum (x,y,z) of the bounding box.
   * 
   * @return  (x,y,z) representing the maximum bounds 
   */    
  public double[] getMaximumBounds() {
    return max;
  }


  /**
   * Sets the minimum and maximum bounds of the bounding box.
   * 
   * @param min  (x,y,z) representing the minimum bounds 
   * @param max  (x,y,z) representing the maximum bounds 
   */  
  public void setBounds(double[] min, double[] max) {
    for (int i=0; i<3; i++) {
      this.min[i]=min[i];
      this.max[i]=max[i];

      range[i]=max[i]-min[i];
      centre[i]=(min[i]+max[i])*0.5;
    }
  }
  
  /**
   * Gets the x range for the bounding box, calculated as max x - min x
   * 
   * @return  x range of the bounding box
   */   
  public double getRangeX() { return range[0]; }
  
  /**
   * Gets the y range for the bounding box, calculated as max y - min y
   * 
   * @return  y range of the bounding box
   */   
  public double getRangeY() { return range[1]; }

  /**
   * Gets the z range for the bounding box, calculated as max z - min z
   * 
   * @return  z range of the bounding box
   */   
  public double getRangeZ() { return range[2]; }
  
  /**
   * Gets the x centre for the bounding box, calculated as (min x+max x)*0.5
   * 
   * @return  x centre of the bounding box
   */   
  public double getCentreX() { return centre[0]; }

  /**
   * Gets the y centre for the bounding box, calculated as (min y+max y)*0.5
   * 
   * @return  y centre of the bounding box
   */   
  public double getCentreY() { return centre[1]; }

  /**
   * Gets the z centre for the bounding box, calculated as (min z+max z)*0.5
   * 
   * @return  z centre of the bounding box
   */   
  public double getCentreZ() { return centre[2]; }

  /**
   * Returns a clone of the bounding box
   * 
   * @return  clone of the bounding box
   */   
  public Object clone() {
    BoundingBox res=new BoundingBox();
    res.min=(double[]) min.clone();
    res.max=(double[]) max.clone();
    res.range=(double[]) range.clone();
    res.centre=(double[]) centre.clone();
    return res;
  }

  /**
   * Standard toString method
   * 
   * @return  String containing min and max bounds of the bounding box
   */     
  public String toString() {
    return "Min={"+min[0]+", "+min[1]+", "+min[2]+"}; Max={"+max[0]+", "+max[1]+", "+max[2]+"}";
  }
}
