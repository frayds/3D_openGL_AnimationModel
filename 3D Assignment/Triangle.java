/**
 * A class for a triangle, containing the indexes of the vertices (in the Mesh vertex data structure)
 * that make up this triangle, and the triangle normal.
 *
 * @author    Dr Steve Maddock
 * @version   3.1 (28/10/2015)
 */

public class Triangle implements Cloneable {

  private int[] vertexIndices = new int[3]; // Indexes of the 3 vertices that make up this triangle.
                                            // Each index refers to the Mesh data structure for vertices.
  private double[] normal = new double[3];  // The triangle normal

 
  /**
   * Constructor. Initialises all values to 0.
   */  
  public Triangle() {
    vertexIndices[0] = vertexIndices[1] = vertexIndices[2] = 0;
    normal[0] = normal[1] = normal[2] = 0;
  }

  /**
   * Constructor. 
   * 
   * @param i1 index into main vertex data structure
   * @param i2 index into main vertex data structure
   * @param i3 index into main vertex data structure
   */    
  public Triangle(int i1, int i2, int i3) {
    vertexIndices[0] = i1;
    vertexIndices[1] = i2;
    vertexIndices[2] = i3;
    normal[0] = normal[1] = normal[2] = 0;
  }

  /**
   * Constructor. If no triangle normal is supplied, it is initialised to zeroes.
   * 
   * @param vertices Three vertex indices into main mesh structure to form this triangle
   * @param n Triangle normal (x,y,z) values; otherwise, null
   */    
  public Triangle(int[] indices, double[] n) {
    this(indices[0], indices[1], indices[2]);
    if (n != null) {
	    normal[0] = n[0];
	    normal[1] = n[1];
	    normal[2] = n[2];
	  }
  }
  
  /**
   * Set the vertices that make up this triangle. 
   * 
   * @param i1 the index of a vertex in the Mesh vertex data structure
   * @param i2 the index of a vertex in the Mesh vertex data structure
   * @param i3 the index of a vertex in the Mesh vertex data structure
   */  
  public void setVertexIndices(int i1, int i2, int i3) {
    vertexIndices[0] = i1;
    vertexIndices[1] = i2;
    vertexIndices[2] = i3;
  }

  /**
   * Set the vertices that make up this triangle. 
   * 
   * @param indices the index numbers of three vertices in the Mesh vertex data structure
   */       
  public void setVertexIndices(int[] indicies) {
    for (int i=0; i<3; i++)
      vertexIndices[i] = indicies[i];
  }

  /**
   * Get the list of vertex indexes (as a reference) that make up this triangle.
   * 
   * @return a list of vertex indexes into the Mesh vertex data structure
   */   
  public int[] getVertexIndices() {
    return vertexIndices;
  }
  
  /**
   * Get the one of the vertices of this triangle. (ssumes value is 0, 1 or 2. 
   * 
   * @param v This will be 0, 1 or 2 to indicate which vertex to return
   * @return The index of a vertex in the Mesh vertex data structure
   */    
  public int getVertexIndex(int v) {
    return vertexIndices[v];
  }

  /**
   * Set the triangle normal
   * 
   * @param nx the x component of the triangle normal
   * @param ny the y component of the triangle normal
   * @param nz the z component of the triangle normal
   */  
  public void setTriangleNormal(double nx, double ny, double nz) {
    normal[0] = nx;
    normal[1] = ny;
    normal[2] = nz;
  }
  
  /**
   * Set the triangle normal
   * 
   * @param normal the xyz components of the triangle normal
   */ 
  public void setTriangleNormal(double[] normal) {
    for (int i=0; i<3; i++)
      this.normal[i] = normal[i];
  }
  
 /**
   * Get the triangle normal (reference)
   * 
   * @return the triangle normal
   */   
  public double[] getTriangleNormal() {
    return normal;
  }

  /**
   * Get the x component of the triangle normal
   * 
   * @return the x component of the triangle normal
   */    
  public double getTriangleNormalX() {
    return normal[0];
  }

  /**
   * Get the y component of the triangle normal
   * 
   * @return the y component of the triangle normal
   */   
  public double getTriangleNormalY() {
    return normal[1];
  }

  /**
   * Get the z component of the triangle normal
   * 
   * @return the z component of the triangle normal
   */    
  public double getTriangleNormalZ() {
    return normal[2];
  }
 
  /**
   * A new triangle is created which is a copy of the contents of this triangle.
   * @return a clone of this triangle
   */ 
  public Object clone() {
    Triangle res = new Triangle();
    for (int i=0; i<3; i++) {
      res.vertexIndices[i] = vertexIndices[i];
      res.normal[i] = normal[i];
    }
    return res;
  }
  
}
