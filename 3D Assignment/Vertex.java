/**
 * A class for a vertex, containing an xyz position, a normal, and (u,v) texture coordinates.
 *
 * @author    Dr Steve Maddock
 * @version   3.1 (28/10/2015)
 */

public class Vertex implements Cloneable {

  private double[] xyz      = new double[3];  // xyz position of a vertex
  private double[] normal   = new double[3];  // vertex normal
  private double[] texCoord = new double[2];  // texture coordinates
  
  /**
   * Constructor. Initialises all values to 0.
   */
  public Vertex() {
    xyz[0] = xyz[1] = xyz[2] = 0;
    normal[0] = normal[1] = normal[2] = 0;
    texCoord[0] = texCoord[1] = 0;
  }

  /**
   * Constructor. Assumes parameter p is well-formed. Vertex normal values and texture coordinates are initialised to zero.
   * 
   * @param x x position of the new vertex
   * @param y y position of the new vertex
   * @param z z position of the new vertex
   */   
  public Vertex(double x, double y, double z) {
    this();
	  xyz[0] = x;
    xyz[1] = y;
    xyz[2] = z;    
  }
  
  /**
   * Constructor. Assumes parameter p is well-formed. Vertex normal values and texture coordinates are initialised to zero.
   * 
   * @param x x position of the new vertex
   * @param y y position of the new vertex
   * @param z z position of the new vertex
   */   
  public Vertex(double[] p) {
    this(p, null, null);
  }

  /**
   * Constructor. Assumes parameter p is well-formed. Vertex normal values and texture coordinates are initialised to zero.
   * 
   * @param x x position of the new vertex
   * @param y y position of the new vertex
   * @param z z position of the new vertex
   * @param u u texture coordinate of the new vertex
   * @param v v texture coordinate of of the new vertex
   */   
  public Vertex(double x, double y, double z, double u, double v) {
    this();
	  xyz[0] = x;
    xyz[1] = y;
    xyz[2] = z;    
	  texCoord[0] = u;
	  texCoord[1] = v;	
  }

  /**
   * Constructor. Assumes parameter p is well-formed. Parameters n and t may be null, in which case they are initialised to zero values.
   * 
   * @param p array containing (x,y,z) position of the new vertex
   * @param n array containing (x,y,z) vertex normal values of the new vertex; alternatively, null
   * @param t array containing (u,v) texture coordinates of the new vertex; otherwise, null
   */   
  public Vertex(double[] p, double[] n, double[] t) {
    xyz[0] = p[0];
    xyz[1] = p[1];
    xyz[2] = p[2];    
    if (n==null) normal[0] = normal[1] = normal[2] = 0;
	  else {
	    normal[0] = n[0];
	    normal[1] = n[1];
	    normal[2] = n[2];
	  }
    if (t==null) texCoord[0]=texCoord[1]=0;
	  else {
	    texCoord[0] = t[0];
	    texCoord[1] = t[1];
	  }
  }

  /**
   * Sets the xyz position of a vertex
   * 
   * @param x the x position of a vertex
   * @param y the y position of a vertex
   * @param z the z position of a vertex
   */ 
  public void setPosition(double x, double y, double z) {
    xyz[0] = x;
    xyz[1] = y;
    xyz[2] = z;
  }

  /**
   * Sets the xyz position of a vertex
   * 
   * @param p array containing (x,y,z) position
   */ 
  public void setPosition(double[] p) {
    xyz[0] = p[0];
    xyz[1] = p[1];
    xyz[2] = p[2];
  }
  
  /**
   * Gets the xyz position of a vertex (as a reference) as a double array, where index 0 = x, index 1 = y, and index 2 = z.
   * 
   * @return the xyz position of a vertex
   */ 
  public double[] getPosition() {
    return xyz;
  }

  /**
   * Gets the x position of a vertex
   * 
   * @return the x position of a vertex
   */  
  public double getPositionX() {
    return xyz[0];
  }

  /**
   * Gets the y position of a vertex
   * 
   * @return the y position of a vertex
   */    
  public double getPositionY() {
    return xyz[1];
  }

  /**
   * Gets the z position of a vertex
   * 
   * @return the z position of a vertex
   */    
  public double getPositionZ() {
    return xyz[2];
  }

    /**
   * Sets the xyz components of the vertex normal. Assumed to be normalised.
   * 
   * @param nx the x component of the vertex normal
   * @param ny the y component of the vertex normal
   * @param nz the z component of the vertex normal
   */  
  public void setNormal(double nx, double ny, double nz) {
    normal[0] = nx;
    normal[1] = ny;
    normal[2] = nz;
  }

  /**
   * Sets the xyz components of the vertex normal. Assumed to be normalised.
   * 
   * @param normal array of values for the vertex normal
   */       
  public void setNormal(double[] normal) {
    for (int i=0; i<3; i++)
      this.normal[i] = normal[i];
  } 
  
  /**
   * Gets the vertex normal (as a reference)
   * 
   * @return the vertex normal
   */  
  public double[] getNormal() {
    return normal;
  }

  /**
   * Gets the x component of the vertex normal
   * 
   * @return the x component of the vertex normal
   */   
  public double getNormalX() {
    return normal[0];
  }

  /**
   * Gets the y component of the vertex normal
   * 
   * @return the y component of the vertex normal
   */
  public double getNormalY() {
    return normal[1];
  }

  /**
   * Gets the z component of the vertex normal
   * 
   * @return the z component of the vertex normal
   */    
  public double getNormalZ() {
    return normal[2];
  }

  /**
   * Adds a vector to the current normal. This results in
   * an unnormalised normal.
   * 
   * @param nx x component of vector to add
   * @param ny y component of vector to add
   * @param nz z component of vector to add
   */  
  public void addNormal(double nx, double ny, double nz) {
    normal[0] += nx;
    normal[1] += ny;
    normal[2] += nz;
  }

  /**
   * Normalise the vertex normal
   */    
  public void normaliseNormal() {
    double mag=Math.sqrt(normal[0]*normal[0]+normal[1]*normal[1]+normal[2]*normal[2]);
    if (mag!=0.0)  // should test within epsilon
      for (int i=0; i<3; i++)
        normal[i]/=mag;
  }

  /**
   * Set the vertex texture coordinates
   * 
   * @param  u texture coordinate
   * @param  v texture coordinate
   */  
  public void setTextureCoord(double u, double v) {
    texCoord[0] = u;
    texCoord[1] = v;
  }
  
  /**
   * set the vertex texture coordinates
   * 
   * @param  uv  u and v texture coordinates
   */  
  public void setTextureCoord(double[] uv) {
    texCoord[0] = uv[0];
    texCoord[1] = uv[1];
  }
  
  /**
   * Get the vertex texture coordinates (as a reference)
   * 
   * @return  u and v texture coordinates
   */  
  public double[] getTextureCoord() {
    return texCoord;
  }
  
  /**
   * Gets the u component of the vertex texture coords
   * 
   * @return the u component of the vertex texture coords
   */   
  public double getTextureCoordU() {
    return texCoord[0];
  }

    /**
   * Gets the v component of the vertex texture coords
   * 
   * @return the v component of the vertex texture coords
   */   
  public double getTextureCoordV() {
    return texCoord[1];
  }

  /**
   * A new vertex is created which is a copy of the contents of this vertex.
   * @return a clone of this vertex
   */  
  public Object clone() {
    Vertex res = new Vertex();
    for (int i=0; i<3; i++) {
      res.xyz[i] = xyz[i];
      res.normal[i] = normal[i];
    }
    res.texCoord[0] = texCoord[0];
    res.texCoord[1] = texCoord[1];
    return res;
  }
  
}
