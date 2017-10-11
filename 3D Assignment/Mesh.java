/**
 * The Mesh class is used to represent the basic structure that is common to all 3D objects
 * Arrays are used for the vertices and triangles.
 *
 * @author    Dr Steve Maddock
 * @version   3.1 (28/10/2015)
 */
 
public class Mesh implements Cloneable {
  public static final int MAX_NUM_VERTICES = 30000;
  public static final int MAX_NUM_TRIANGLES = 10000;

  private Vertex[] vertices;
  private Triangle[] triangles;
  private Material material = new Material();
  private BoundingBox boundingbox = new BoundingBox();
  private float[] colour = new float[]{1.0f, 1.0f, 1.0f, 1.0f};

  /**
   * Constructor. Sets attributes to default initial values.
   */  
  public Mesh() {
    vertices = null;
    triangles = null;
  }

  /**
   * Gets the number of vertices in the Mesh
   * 
   * @return  the number of vertices in the Mesh
   */ 
  public int getNumVertices() {
    if (vertices != null) return vertices.length;
	else return 0;
  }

  /**
   * Set the vertex data structure. The reference is copied. A clone is not made. 
   * (Consider calling relevant method to set triangles too.)
   * 
   * @param  vertices         the new vertex data structure, passed as an array.
   */   
  public void setVertices(Vertex[] vertices) {
    this.vertices = vertices;
    calcBoundingBox();
  }

  /**
   * Get the vertex data structure as a reference.
   * 
   * @return  a reference to the vertex data structure
   */   
  public Vertex[] getVertices() {
    return vertices;
  }

  /**
   * Gets the vertex (x,y,z) data as an array of doubles. This is a copy of the data stored
   * in the vertex data structure.
   * The data is organised as a 1D array: Vertex 0's x,y,z, vertex 1's x,y,z, etc.
   * 
   * @return  an array of doubles representing the vertex x,y,z data
   */   
  public double[] getVertexList() {
    double[] tempverts = new double[vertices.length*3];
    for (int v=0; v<vertices.length; v++) {
      tempverts[v*3] = vertices[v].getPositionX();
      tempverts[v*3+1] = vertices[v].getPositionY();
      tempverts[v*3+2] = vertices[v].getPositionZ();
    }
    return tempverts;
  }

  /**
   * Gets the vertex normals (x,y,z) data as an array of doubles. This is a copy of the data stored
   * in the vertex data structure.
   * The data is organised as a 1D array: Vertex 0's normal x,y,z, Vertex 1's normal x,y,z, etc.
   * 
   * @return  an array of doubles representing the vertex normals x,y,z data
   */   
  public double[] getNormalList() {
    double[] temp = new double[vertices.length*3];
    for (int v=0; v<vertices.length; v++) {
      temp[v*3] = vertices[v].getNormalX();
      temp[v*3+1] = vertices[v].getNormalY();
      temp[v*3+2] = vertices[v].getNormalZ();
    }
    return temp;
  }
  
   /**
   * Gets the vertex texture coords (u,v) data as an array of doubles. This is a copy of the data stored
   * in the vertex data structure.
   * The data is organised as a 1D array: Vertex 0's texture coords u,v, Vertex 1's texture coords u,v, etc.
   * 
   * @return  an array of doubles representing the vertex texture coords x,y,z data
   */   
  public double[] getTextureCoordsList() {
    double[] temp = new double[vertices.length*2];
    for (int v=0; v<vertices.length; v++) {
      temp[v*2] = vertices[v].getTextureCoordU();
      temp[v*2+1] = vertices[v].getTextureCoordV();
    }
    return temp;
  }

  /**
   * Get a reference to a specific vertex in the Mesh.
   * Users should be aware that this allows original data to be altered.
   * 
   * @param  index  the index of the vertex to return.
   * @return  a reference to the Vertex at the given index value. null is returned if the vertex index does not exist.
   */ 
  public Vertex getVertex(int index) {
    // Return a clone of the vertex and not the original one so that external classes cannot alter the data
    if (index < vertices.length)
      return vertices[index];
    else
      return null;
  }

 /**
   * Get a clone of a specific vertex in the Mesh.
   * A clone is returned, not the original Vertex, so that external classes cannot alter the data.
   * 
   * @param  index  the index of the vertex to return.
   * @return  a clone of the Vertex at the given index value. null is returned if the vertex index does not exist.
   */ 
  public Vertex getVertexClone(int index) {
    // Return a clone of the vertex and not the original one so that external classes cannot alter the data
    if (index < vertices.length)
      return (Vertex) vertices[index].clone();
    else
      return null;
  }

  /**
   * Gets the number of triangles in the Mesh
   * 
   * @return  the number of triangles in the Mesh
   */   
  public int getNumTriangles() {
    if (triangles != null) return triangles.length;
    else return 0;
  }

  /**
   * Set the triangle data structure. The reference is copied. A clone is not made. 
   * (Consider calling relevant method to set vertices too.)
   * 
   * @param  triangles         the new triangle data structure, passed as an array.
   */   
  public void setTriangles(Triangle[] triangles) {
    this.triangles = triangles;
  }
   
  /**
   * Get a reference to the triangle data structure.
   * 
   * @return  a reference to the triangle data structure
   */   
  public Triangle[] getTriangles() {
    return triangles;
  }

  /**
   * Gets the triangle data as an array of vertex indices stored as ints. 
   * This is a copy of the data stored in the triangle data structure.
   * The data is organised as a 1D array: triangle 0's vertex indexes v0,v1,v2,
   * triangle 1's vertex indexes v0,v1,v2, etc.
   * 
   * @return  an array of ints representing the triangle vertex index data
   */   
  public int[] getTriangleList() {
    int[] temptris = new int[triangles.length*3];
    for (int t=0; t<triangles.length; t++)
      for (int i=0; i<3; i++)
        temptris[t*3+i]=triangles[t].getVertexIndex(i);
    return temptris;
  }
  
  /**
   * Get a reference to a specific triangle in the Mesh.
   * Users should be aware that the original data can thus be altered.
   * 
   * @param  index  the index of the triangle to return.
   * @return  a reference to the Triangle at the given index value.  null is returned if the triangle index does not exist.
   */   
  public Triangle getTriangle(int index) {
    if (index<triangles.length)
      return triangles[index];
    else
      return null;
  }
  
    /**
   * Get a clone of a specific triangle in the Mesh.
   * A clone is returned, not the original triangle, so that external users cannot alter the data.
   * 
   * @param  index  the index of the triangle to return.
   * @return  a copy of the Triangle at the given index value.  null is returned if the triangle index does not exist.
   */   
  public Triangle getTriangleClone(int index) {
    if (index<triangles.length)
      return (Triangle) triangles[index].clone();
    else
      return null;
  }

  /**
   * Change the Material for the Mesh object.
   * 
   * @param  material  the new Material for this Mesh.
   */     
  public void setMaterial(Material material) {
    this.material = material;
  }
  
  /**
   * Return a reference to the material object.
   * 
   * @return  a reference of the Material for the Mesh
   */   
  public Material getMaterial() {
    return material;
  }
  
  /**
   * Return a clone of the material object and not the original so that external classes cannot alter the data.
   * External classes must use setMaterial(...) method to change the whole Material for the Mesh object.
   * 
   * @return  a clone of the Material for the Mesh
   */   
  public Material getMaterialClone() {
    return (Material) material.clone();
  }

  /**
   * Set the colour for the Mesh.
   * If Material properties are being used to render the object, then this colour is irrelevant.
   * 
   * @param colour a colour for the Mesh
   */     
  public void setColour(float[] colour) {
    for (int i=0; i<4; i++)
      this.colour[i] = colour[i];
  }

  /**
   * Set the colour for the Mesh.
   * If Material properties are being used to render the object, then this colour is irrelevant.
   * 
   * @param  red  the red colour for the Mesh
   * @param  green  the green colour for the Mesh
   * @param  blue  the blue colour for the Mesh
   * @param  alpha  the alpha colour for the Mesh
   */    
  public void setColour(float red, float green, float blue, float alpha) {
    colour[0] = red;
    colour[1] = green;
    colour[2] = blue;
    colour[3] = alpha;
  }

  /**
   * Return a clone of the base colour and not the original one so that external classes cannot alter the data
   * If Material properties are being used to render the object, then this colour is irrelevant.
   * 
   * @return  a clone of the colour for the Mesh
   */   
  public float[] getColour() {
    return (float[]) colour.clone();
  }

  /**
   * Return a clone of the bounding box and not the original one so that external classes cannot alter the data
   * 
   * @return  a clone of the bounding box for the Mesh
   */    
  public BoundingBox getBoundingBox() {
    return (BoundingBox) boundingbox.clone();
  }
 
  protected void calcBoundingBox() {
    int i;
    double min[] = new double[3];
    double max[] = new double[3];
    
    if (vertices.length>0) {
      for (i=0; i<3; i++)
        max[i] = min[i] = vertices[0].getPosition()[i];
         
      for (i=1; i<vertices.length; i++)
        for (int j=0; j<3; j++)
          if (vertices[i].getPosition()[j]<min[j])
            min[j] = vertices[i].getPosition()[j];
          else if (vertices[i].getPosition()[j]>max[j])
            max[j] = vertices[i].getPosition()[j];
      boundingbox.setBounds(min, max);
    }
  }

  public void calcSmoothNormals() {
    int i, j;
    double mag;
    double nx, ny, nz;
    double avec[] = new double[3];
    double bvec[] = new double[3];
    
    // Reset the vertex normals back to {0, 0, 0}
    for (i=0; i<vertices.length; i++)
      vertices[i].setNormal(0, 0, 0);
    
    for (i=0; i<triangles.length; i++) {
      for (j=0; j<3; j++) {
	      double d = vertices[triangles[i].getVertexIndex(0)].getPosition()[j]; 
        avec[j] = d - vertices[triangles[i].getVertexIndex(1)].getPosition()[j];
        bvec[j] = d - vertices[triangles[i].getVertexIndex(2)].getPosition()[j];
      }
      // Cross product of avec and bvec to determine the triangle normal
      nx = avec[1]*bvec[2]-avec[2]*bvec[1];
      ny = avec[2]*bvec[0]-avec[0]*bvec[2];
      nz = avec[0]*bvec[1]-avec[1]*bvec[0];
      mag = Math.sqrt(nx*nx+ny*ny+nz*nz);
      if (mag!=0.0f) {
        nx /= mag;
        ny /= mag;
        nz /= mag;
        triangles[i].setTriangleNormal(nx, ny, nz);
      }
      else triangles[i].setTriangleNormal(0,0,0);
      for (j=0; j<3; j++)
        vertices[triangles[i].getVertexIndex(j)].addNormal(nx, ny, nz);
    }    
    // normalise the normals
    for (i=0; i<vertices.length; ++i)
      vertices[i].normaliseNormal();
  }

  /**
   * A clone of the Mesh.
   * 
   * @return  A clone of the Mesh.
   */ 
  public Object clone() {
    Mesh res = new Mesh();
    
    if (vertices != null) {
      res.vertices = new Vertex[vertices.length];
      for (int i=0; i<vertices.length; i++)
        res.vertices[i] = (Vertex) vertices[i].clone();
    }
    
    if (triangles != null) {
      res.triangles = new Triangle[triangles.length];
      for (int i=0; i<triangles.length; i++)
        res.triangles[i] = (Triangle) triangles[i].clone();
    }
    
    if (material != null)
      res.material = (Material) material.clone();          
   
    res.colour = (float[]) colour.clone();

    if (boundingbox != null)
      res.boundingbox = (BoundingBox) boundingbox.clone();

    return res;
  }
  
}
