
 /**
 * This class stores the Material properties for a Mesh
 *
 * @author    Dr Steve Maddock
 * @version   2.0 (09/09/2011)
 */

public class Material implements Cloneable {

  public static final float[] DEFAULT_AMBIENT = {0.2f, 0.2f, 0.2f, 1.0f};
  public static final float[] DEFAULT_DIFFUSE = {0.8f, 0.8f, 0.8f, 1.0f};
  public static final float[] DEFAULT_SPECULAR = {0.5f, 0.5f, 0.5f, 1.0f};
  public static final float[] DEFAULT_EMISSION = {0.0f, 0.0f, 0.0f, 1.0f};
  public static final float DEFAULT_SHININESS = 32;
  
  private float[] ambient;
  private float[] diffuse;
  private float[] specular;
  private float[] emission;
  private float shininess;
  
  /**
   * Constructor. Sets attributes to default initial values.
   */    
  public Material() {
    ambient = DEFAULT_AMBIENT.clone();
    diffuse = DEFAULT_DIFFUSE.clone();
    specular = DEFAULT_SPECULAR.clone();
    emission = DEFAULT_EMISSION.clone();
    shininess = DEFAULT_SHININESS;
  }
  
  /**
   * Sets the ambient value (as used in Phong local reflection model)
   * 
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
   * @param  alpha  the alpha value in the range 0.0..1.0
   */    
  public void setAmbient(float red, float green, float blue, float alpha) {
    ambient[0] = red;
    ambient[1] = green;
    ambient[2] = blue;
    ambient[3] = alpha;
  }  
  
  /**
   * Sets the ambient value (as used in Phong local reflection model)
   * 
   * @param  rgba  array of 4 values, where the first 3 values are the values for red, green and blue, 
                   in the range 0.0..1.0, and the last value is an alpha term, which is always 1.
   */    
  public void setAmbient(float[] rgba) {
    for (int i=0; i<4; i++)
      ambient[i] = rgba[i];
  }
  
  /**
   * Gets the ambient value (as a clone) (as used in Phong local reflection model)
   * 
   * @return  4 values, where the first 3 values are the values for red, green and blue, 
              and the last value is an alpha term, which is always 1.
   */  
  public float[] getAmbient() {
    return ambient.clone();
  }

  /**
   * Sets the diffuse value (as used in Phong local reflection model)
   * 
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
   * @param  alpha  the alpha value in the range 0.0..1.0
  */  
  public void setDiffuse(float red, float green, float blue, float alpha) {
    diffuse[0] = red;
    diffuse[1] = green;
    diffuse[2] = blue;
    diffuse[3] = alpha;
  }
  
  /**
   * Sets the diffuse value (as used in Phong local reflection model)
   * 
   * @param  rgba  array of 4 values, where the first 3 values are the values for red, green and blue, 
                   in the range 0.0..1.0, and the last value is an alpha term, which is always 1.
   */      
  public void setDiffuse(float[] rgba) {
    for (int i=0; i<4; i++)
      diffuse[i] = rgba[i];
  }

  /**
   * Gets the diffuse value (clone) (as used in Phong local reflection model)
   * 
   * @return  4 values, where the first 3 values are the values for red, green and blue, 
              and the last value is an alpha term, which is always 1.
   */    
  public float[] getDiffuse() {
    return diffuse.clone();
  }

  /**
   * Sets the specular value (as used in Phong local reflection model)
   * 
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
   * @param  alpha  the alpha value in the range 0.0..1.0
  */    
  public void setSpecular(float red, float green, float blue, float alpha) {
    specular[0] = red;
    specular[1] = green;
    specular[2] = blue;
    specular[3] = alpha;
  }

  /**
   * Sets the specular value (as used in Phong local reflection model)
   * 
   * @param  rgba  array of 4 values, where the first 3 values are the values for red, green and blue, 
                   in the range 0.0..1.0, and the last value is an alpha term, which is always 1.
   */    
  public void setSpecular(float[] rgba) {
    for (int i=0; i<4; i++)
      specular[i] = rgba[i];
  }
    
  /**
   * Gets the specular value (clone) (as used in Phong local reflection model)
   * 
   * @return  4 values, where the first 3 values are the values for red, green and blue, 
              and the last value is an alpha term, which is always 1.
   */  
  public float[] getSpecular() {
    return specular.clone();
  }

  /**
   * Sets the emission value (as used in OpenGL lighting model)
   * 
   * @param  red    the red value in the range 0.0..1.0
   * @param  green  the green value in the range 0.0..1.0
   * @param  blue   the blue value in the range 0.0..1.0
   * @param  alpha  the alpha value in the range 0.0..1.0
   */    
  public void setEmission(float red, float green, float blue, float alpha) {
    emission[0] = red;
    emission[1] = green;
    emission[2] = blue;
    emission[3] = alpha;
  }
  
  /**
   * Sets the emission value (as used in OpenGL lighting model)
   * 
   * @param  rgba  array of 4 values, where the first 3 values are the values for red, green and blue, 
                   in the range 0.0..1.0, and the last value is an alpha term, which is always 1.
   */    
  public void setEmission(float[] rgba) {
    for (int i=0; i<4; i++)
      emission[i] = rgba[i];
  }

  /**
   * Gets the emission value (clone) (as used in OpenGL lighting model)
   * 
   * @return  4 values, where the first 3 values are the values for red, green and blue, 
              and the last value is an alpha term, which is always 1.
   */ 
  public float[] getEmission() {
    return emission.clone();
  }
    
  /**
   * Sets the shininess value (as used in Phong local reflection model)
   * 
   * @param  shininess  the shininess value.
   */   
  public void setShininess(float shininess) {
    this.shininess = shininess;
  }
  
  /**
   * Gets the shininess value (as used in Phong local reflection model)
   * 
   * @return  the shininess value.
   */   
  public float getShininess() {
    return shininess;
  }

  /**
   * Returns a clone of the Material
   * 
   * @return  a clone of the Material
   */   
  
  public Object clone() {
    Material res = new Material();
    for (int i=0; i<4; i++) {
      res.ambient[i] = ambient[i];
      res.diffuse[i] = diffuse[i];
      res.specular[i] = specular[i];
      res.emission[i] = emission[i];
    }
    res.shininess = shininess;
    return res;
  }

  private String floatArrayToString(float[] a) {
    String s = "[";
    for (int i=0; i<a.length; ++i) {
      s+=a[i];
      if (i<a.length-1) s+=",";
    }
    s+="]";
    return s;
  }
  
  public String toString() {
    return floatArrayToString(ambient)+", "+floatArrayToString(diffuse)
           +", "+floatArrayToString(specular)+", "+floatArrayToString(emission)
           +", "+shininess;
  }  
  
  public static void main(String[] args) {
    Material m = new Material();
    System.out.println(m);
  }

}