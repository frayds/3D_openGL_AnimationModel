/**
 * Methods to create mesh instances
 * Need to add a sphere method too.
 *
 * @author    Dr Steve Maddock
 * @version   2.1 (28/10/2015)
 */

public class ProceduralMeshFactory {

/* Mesh plane ******************************************************************
*/

  /**
   * The plane lies in the x-z axis, with a y value of 0, and centred at the world origin.
   * Creates a default Plane of width (x range) and depth (z range) of 1, with the
   * origin at the midpoint of the mesh, 
   * and 10 grid points in the x direction and 10 grid points in the z direction.
   * When viewed from above (i.e. somehwere along the positive y axis looking back to the origin),
   * the plane runs from bottom left coordinates of (-0.5,0,0.5) to 
   * top right coordinates of (0.5,0,-0.5)
   */
  public static Mesh createPlane() {
    return createPlane(1, 1, 10, 10, 1, 1);
  }

  /**
   * The plane lies in the x-z axis, with a y value of 0, and centred at the world origin.
   * @param width width (in x direction)
   * @param depth  depth (in z direction)
   * @param m number of points along the x axis
   * @param n number of points along the z axis
   * @param tilingM number of texture pieces along the x axis
   * @param tilingN number of texture pieces along the z axis
   
   */ 
  // creates uv coordinates irrespective of what was asked for.
  // Assumes Vertex class has u,v attributes
  public static Mesh createPlane(double width, double depth, int m, int n,
                                 double tilingM, double tilingN)  {
    if (m<2) m=2; 
    if (n<2) n=2;

    int vertoffset=0, trioffset=0;
    double texu;
    double startM=-width/2.0;
    double startN=-depth/2.0;
    double stepM=width/(m-1);
    double stepN=depth/(n-1);
    double txStepX=tilingM/(m-1);
    double txStepZ=tilingN/(n-1);

    int numVertices=m*n;
    Vertex[] vertices=new Vertex[numVertices];
 
    int numTriangles=2*(m-1)*(n-1);
    Triangle[] triangles=new Triangle[numTriangles];
 
    // negate all z coordinates so that they run from depth/2 to -depth/2
    // and be careful of winding order

    for (int i=0; i<n; i++) {
      vertices[i] = new Vertex(startM, 0.0, -(startN+i*stepN), 0, i*txStepZ);
    }

    for (int j=1; j<m; j++) {
      vertoffset+=n;
      texu=j*txStepX;
      vertices[vertoffset] = new Vertex(startM+j*stepM, 0.0, -startN, texu, 0);
      for (int i=1; i<n; i++) {
        vertices[vertoffset+i] = new Vertex(startM+j*stepM, 0.0, -(startN+i*stepN), texu, i*txStepZ);
        int vi1 = vertoffset+i, vi2 = vertoffset+i-n-1,
        vi3 = vertoffset+i-1, vi4 = vertoffset+i-n;
        triangles[trioffset++] = new Triangle(vi1, vi2, vi3);
        triangles[trioffset++] = new Triangle(vi2, vi1, vi4);
      }
    }

    Mesh mesh = new Mesh();
    mesh.setVertices(vertices);
    mesh.setTriangles(triangles);    
    
    // Set Normals;
    for (int i=0; i<numVertices; ++i) {
      vertices[i].setNormal(0,1,0);
    }
    for (int i=0; i<numTriangles; ++i) {
      triangles[i].setTriangleNormal(0,1,0);
    }
	
    //Calc Normals; - not required, as all the normals point in the same direction so can just be set
    //mesh.calcSmoothNormals();
	
    return mesh;
  }


/* Hard cube ******************************************************************
*/

  /**
   * Default is width 1, height 1 , depth 1, with origin at centre of hard cube.
   * Automatically creates uv coordinates.
   */
  public static Mesh createHardCube() {
    return createMeshHardCube(1, 1, 1, true);
  }
  
  /**
   * Origin is at centre of hard cube.
   * @param width width (in x direction) of cube
   * @param height height (in y direction) of cube
   * @param depth  depth (in z direction) of cube
   */ 
  public static Mesh createHardCube(double width, double height, double depth) {
    return createMeshHardCube(width, height, depth, true);
  }
  
  private static Mesh createMeshHardCube(double width, double height, double depth,
                                         boolean textured) {
    Vertex[] vertices=new Vertex[24];
    Triangle[] triangles=new Triangle[12];

    // Need 3 different copies of each vertex
    for (int i=0; i<3; i++) {
      int offset=i*8;
      vertices[offset]=new Vertex(-width/2, -height/2, -depth/2);
      vertices[offset+1]=new Vertex(-width/2, -height/2, depth/2);
      vertices[offset+2]=new Vertex(-width/2, height/2, -depth/2);
      vertices[offset+3]=new Vertex(-width/2, height/2, depth/2);
      vertices[offset+4]=new Vertex(width/2, -height/2, -depth/2);
      vertices[offset+5]=new Vertex(width/2, -height/2, depth/2);
      vertices[offset+6]=new Vertex(width/2, height/2, -depth/2);
      vertices[offset+7]=new Vertex(width/2, height/2, depth/2);
    }

    // Left (x -ve)
    triangles[0]=new Triangle(0, 1, 3);
    triangles[1]=new Triangle(3, 2, 0);
    triangles[0].setTriangleNormal(-1,0,0);
    triangles[1].setTriangleNormal(-1,0,0);	
    for (int i=0; i<4; ++i) {
      vertices[i].setNormal(-1,0,0);
    }
    vertices[0].setTextureCoord(0.0f, 0.0f);
    vertices[1].setTextureCoord(1.0f, 0.0f);
    vertices[2].setTextureCoord(0.0f, 1.0f);
    vertices[3].setTextureCoord(1.0f, 1.0f);
    
    // Right (x +ve)
    triangles[2]=new Triangle(4, 6, 7);
    triangles[3]=new Triangle(7, 5, 4);
    triangles[2].setTriangleNormal(1,0,0);
    triangles[3].setTriangleNormal(1,0,0);
    for (int i=4; i<8; ++i) {
      vertices[i].setNormal(1,0,0);
    }
    vertices[4].setTextureCoord(1.0f, 0.0f);
    vertices[5].setTextureCoord(0.0f, 0.0f);
    vertices[6].setTextureCoord(1.0f, 1.0f);
    vertices[7].setTextureCoord(0.0f, 1.0f);
    
    // Front (z +ve)
    triangles[4]=new Triangle(9, 13, 15);
    triangles[5]=new Triangle(15, 11, 9);
    triangles[4].setTriangleNormal(0,0,1);
    triangles[5].setTriangleNormal(0,0,1);
    vertices[9].setNormal(0,0,1);
    vertices[11].setNormal(0,0,1);
    vertices[13].setNormal(0,0,1);
    vertices[15].setNormal(0,0,1);
    vertices[9].setTextureCoord(0.0f, 0.0f);
    vertices[11].setTextureCoord(0.0f, 1.0f);
    vertices[13].setTextureCoord(1.0f, 0.0f);
    vertices[15].setTextureCoord(1.0f, 1.0f);
    
    // Back (z -ve)
    triangles[6]=new Triangle(8, 10, 14);
    triangles[7]=new Triangle(14, 12, 8);
    triangles[6].setTriangleNormal(0,0,-1);
    triangles[7].setTriangleNormal(0,0,-1);
    vertices[8].setNormal(0,0,-1);
    vertices[10].setNormal(0,0,-1);
    vertices[12].setNormal(0,0,-1);
    vertices[14].setNormal(0,0,-1);
    vertices[8].setTextureCoord(1.0f, 0.0f);
    vertices[10].setTextureCoord(1.0f, 1.0f);
    vertices[12].setTextureCoord(0.0f, 0.0f);
    vertices[14].setTextureCoord(0.0f, 1.0f);
    
    // Bottom (y -ve)
    triangles[8]=new Triangle(16, 20, 21);
    triangles[9]=new Triangle(21, 17, 16);
    triangles[8].setTriangleNormal(0,-1,0);
    triangles[9].setTriangleNormal(0,-1,0);
    vertices[16].setNormal(0,-1,0);
    vertices[17].setNormal(0,-1,0);
    vertices[20].setNormal(0,-1,0);
    vertices[21].setNormal(0,-1,0);
    vertices[16].setTextureCoord(0.0f, 0.0f);
    vertices[17].setTextureCoord(0.0f, 1.0f);
    vertices[20].setTextureCoord(1.0f, 0.0f);
    vertices[21].setTextureCoord(1.0f, 1.0f);
    
    // Top (y +ve)
    triangles[10]=new Triangle(23, 22, 18);
    triangles[11]=new Triangle(18, 19, 23);
    triangles[10].setTriangleNormal(0,1,0);
    triangles[11].setTriangleNormal(0,1,0);
    vertices[18].setNormal(0,1,0);
    vertices[19].setNormal(0,1,0);
    vertices[22].setNormal(0,1,0);
    vertices[23].setNormal(0,1,0);
    vertices[18].setTextureCoord(0.0f, 1.0f);
    vertices[19].setTextureCoord(0.0f, 0.0f);
    vertices[22].setTextureCoord(1.0f, 1.0f);
    vertices[23].setTextureCoord(1.0f, 0.0f);

    Mesh mesh = new Mesh();
    
    mesh.setVertices(vertices);
    mesh.setTriangles(triangles);    

    // Calc Normals; - not necessary as easy to set normals for faces of a cube
    // mesh.calcSmoothNormals();
	
    return mesh;
  }
  
  /* Cylinder ******************************************************************
*/

  /**
   * Creates a default cylinder with 30 slices and 1 stack, with end caps.
   * Automatically creates uv coordinates.
   */   
  public static Mesh createCylinder() {
    return createMeshCylinder(30, 1, true, true);
  }
  
  /**
   * @param  slices  number of subdividions around the long axis (z-axis)
   * @param  stacks  number of subdivisions along the long axis (z-axis)
   * @param  caps    true if the cylinder is to have caps at the ends of the cylinder, 
   *                 otherwise false for no end caps
   */
  public static Mesh createCylinder(int slices, int stacks, boolean caps) {
    return createMeshCylinder(slices, stacks, caps, true);
  }
  
  private static Mesh createMeshCylinder(int slices, int stacks, boolean caps, 
                                         boolean textured) {
    if (stacks<2)
      stacks=2;
    if (slices<3)
      slices=3;

    int r, z;
    double s, c, angle, height, texu, texv;
    double stepz=1.0/(stacks-1);
    double stepr=2.0*Math.PI/slices;
    int stackoffset=0, lowerstack, trioffset=0, trioffset2;
    slices++;
	
    Vertex[] vertices=new Vertex[slices*stacks+(caps?2*(1+slices):0)];
    Triangle[] triangles=new Triangle[2*slices*(stacks-1)+(caps?2*slices:0)];

    // Create the cylinder length facets
    for (r=0; r<slices; r++) {
      s = Math.sin(angle=r*stepr)*0.5;
      c = Math.cos(angle)*0.5;
      vertices[r]=new Vertex(s, c, 0, 1.0-(double)r/(slices-1), 0.0);  // need to run 1.0-0.0 in u 
	                                                                     // because x and y axes are flipped in the coordinate system
    }

    for (z=1; z<stacks; z++) {
      vertices[stackoffset=z*slices]=new Vertex(vertices[0].getPositionX(), vertices[0].getPositionY(), height=z*stepz, 1.0, texv=(double) z/(stacks-1));
      lowerstack=stackoffset-slices;
      
      triangles[trioffset]=new Triangle(stackoffset, lowerstack, lowerstack+slices-1);
      triangles[trioffset+1]=new Triangle(lowerstack+slices-1, stackoffset+slices-1, stackoffset);
      for (r=1; r<slices; r++) {
        vertices[stackoffset+r]=new Vertex(vertices[r].getPositionX(), vertices[r].getPositionY(), height, vertices[r].getTextureCoord()[0], texv);
        triangles[trioffset+2*r]=new Triangle(stackoffset+r, lowerstack+r, lowerstack+r-1);
        triangles[trioffset+2*r+1]=new Triangle(lowerstack+r-1, stackoffset+r-1, stackoffset+r);
      }

      trioffset+=2*slices;
    }
    
    // Create the caps if specified
    if (caps) { // when capping the cylinder the upper and lowermost ring of vertices are copied to create a hard edge at the top and bottom of the cylinder
      int ep1, ep2;

      vertices[ep1=vertices.length-2]=new Vertex(0.0, 0.0, 0.0, 0.5, 0.5);
      vertices[ep2=vertices.length-1]=new Vertex(0.0, 0.0, 1.0, 0.5, 0.5);
      vertices[lowerstack=stacks*slices]=(Vertex) vertices[0].clone();
      vertices[lowerstack].setTextureCoord(texu=0.5, texv=0.0);
      vertices[stackoffset=lowerstack+slices]=(Vertex) vertices[stackoffset-2*slices].clone();
      vertices[stackoffset].setTextureCoord(texu, texv);
      
      triangles[trioffset]=new Triangle(ep1, lowerstack+slices-1, lowerstack);
      triangles[trioffset2=trioffset+slices]=new Triangle(stackoffset, stackoffset+slices-1, ep2);
      for (r=1; r<slices; r++) {
        vertices[lowerstack+r]=(Vertex) vertices[r].clone();
        vertices[lowerstack+r].setTextureCoord(texu=0.5-Math.sin(angle=r*stepr)*0.5, texv=0.5-Math.cos(angle)*0.5);
        vertices[stackoffset+r]=(Vertex) vertices[stackoffset-2*slices+r].clone();
        vertices[stackoffset+r].setTextureCoord(1-texu, texv);
        triangles[trioffset+r]=new Triangle(lowerstack+r-1, lowerstack+r, ep1);
        triangles[trioffset2+r]=new Triangle(stackoffset+r, stackoffset+r-1, ep2);
      }
    }
    
    Mesh mesh = new Mesh();
    
    mesh.setVertices(vertices);
    mesh.setTriangles(triangles);    

    // Calc Normals;
    mesh.calcSmoothNormals();
	
    // Instead of calculating the normals, they could easily be set mathematically,
    // given that it is a cylinder.
    // This would also hide the join that is visible when rendering.
    return mesh;
  }

}