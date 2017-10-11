/**
 * A class for a light. Floats are used since the gl light functions use floats.
 * If we used doubles in this class, we would have to convert to floats 
 * for use by the gl light functions. 
 * Floats are fine for the accuracy required.
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (26/07/2013)
 */
 
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.gl2.GLUT;

public class Light implements Cloneable {

  public static final float[] DEFAULT_POSITION = {0f,11.5f,0.0f,1.0f};
  // (x,y,z,w) position of light.
  // If w is 0, then it is a directional light
  // (at infinite distance in the given vector direction).
  // If w is 1, then it is a positional light
  // (at the given position in the scene, and emitting in all directions).
  public static final float[] WHITE_LIGHT = {1.0f,1.0f,1.0f};
  public static final float[] DEFAULT_AMBIENT = {0.1f,0.1f,0.1f};
  
  private int index;
  private float[] position;
  private float[] ambient;
  private float[] diffuse;
  private float[] specular;
  private boolean switchedOn;

  private boolean spotlight = false;
  private float[] direction;
  private float angle;
  
  /**
   * Constructor.
   * Index should be in range GL2.GL_LIGHT0 to GL2.GL_LIGHT8, but not currently checked. 
   * All values set to default.
   * @param i Index value for specific OpenGL light. 
   */
  public Light(int i) {
    this(i, DEFAULT_POSITION, DEFAULT_AMBIENT, WHITE_LIGHT, WHITE_LIGHT, true);
  }    

  /**
   * Constructor. All values set to default except position.
   * Index should be in range GL2.GL_LIGHT0 to GL2.GL_LIGHT8, but not currently checked.
   * @param i Index value for specific OpenGL light. 
   * @param position Position of the light source 
   */
  public Light(int i, float[] position) {
    this(i, position, DEFAULT_AMBIENT, WHITE_LIGHT, WHITE_LIGHT, true);
  }
  
  /**
   * Constructor.
   * Index should be in range GL2.GL_LIGHT0 to GL2.GL_LIGHT8, but not currently checked.
   * @param i Index value for specific OpenGL light.
   * @param position Position of the light source 
   * @param ambient Ambient value of light source
   * @param diffuse Diffuse value of light source
   * @param specular Specular value of light source
   * @param on Whether or not the light source is turned on
   */
  public Light(int i, float[] position, float[] ambient, float[] diffuse, float[] specular, boolean on) {
    index = i;
    this.position = position.clone();
    this.ambient = ambient.clone();
    this.diffuse = diffuse.clone();
    this.specular = specular.clone();
    switchedOn = on;
  } 

  /**
   * Convert the current light object into a spotlight.
   * A better solution would be to initialise this in the constructor, but this works ok.
   * @param direction Direction spotlight is pointing in
   * @param angle Angle of the cut-off. Smaller values means a tighter spotlight.
   * Obviously, distance away from object impacts on the value this should be set to.
   */
  public void makeSpotlight(float[] direction, float angle) {
    if (position[3]!=1) { 
      System.out.println("Error. Position[3] needs to be 1 for a spotlight. Will now change.");
      position[3]=1;
    }
    spotlight = true;
    this.direction = direction.clone();
    this.angle = angle;
  }
  
  public void makeDirectional() {
    position[3]=0f;
  }
  
  public float[] getPosition() {
    return position.clone();
  }
  
  public float[] getAmbient() {
    return ambient.clone();
  }
  
  public float[] getDiffuse() {
    return diffuse.clone();
  }

  public float[] getSpecular() {
    return specular.clone();
  }
  
  public void setSwitchedOn(boolean on) {
    switchedOn = on;
  }
  
  public boolean getSwitchedOn() {
    return switchedOn;
  } 

  public void enable(GL2 gl) {
    gl.glEnable(index); 
  }
  
  public void disable(GL2 gl) {
    gl.glDisable(index); 
  }

  /**
   * If the light is switched on, then call all the relavant OpenGL command to enable
   * and set the parameters for this light.
   * @param gl OpenGL context
   * @param glut GLUT object
   * @param show whether or not to show the light postion visually with a geometric object.
   *        Typically, this will be false. When debugging a program it is useful to set this to true.
   */
  public void use(GL2 gl, GLUT glut, boolean show) {
    if (switchedOn) {
      gl.glEnable(index); 
      // There is no glLightdv, so use glLightfv
      gl.glLightfv(index, GL2.GL_POSITION, position, 0);
      gl.glLightfv(index, GL2.GL_AMBIENT, ambient, 0);
      gl.glLightfv(index, GL2.GL_DIFFUSE, diffuse, 0);
      gl.glLightfv(index, GL2.GL_SPECULAR, specular, 0);
      if (spotlight) {
        gl.glLightf(index, GL2.GL_SPOT_CUTOFF, angle);
        gl.glLightfv(index, GL2.GL_SPOT_DIRECTION, direction, 0);
      }
      if (show) { 
        if (position[3] == 1) displayPosition(gl, glut);
        else displayDirection(gl);
      }
    }
    else gl.glDisable(index);
  }

  private void displayPosition(GL2 gl, GLUT glut) {
    float[] matAmbientDiffuse = {0.1f, 0.1f, 0.1f, 1.0f};
    float[] matSpecular = {0.0f, 0.0f, 0.0f, 0.0f};
    float[] matShininess = {1.0f};
    float[] matEmission = {0.9f, 0.9f, 0.9f, 1.0f};

    // use glMaterialfv. There is no glMaterialdv
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);
    gl.glPushMatrix();
      gl.glTranslated(position[0], position[1], position[2]);
      glut.glutSolidSphere(0.1, 10,10);
    gl.glPopMatrix();
  }
  
  private void displayDirection(GL2 gl) {
    gl.glDisable(GL2.GL_LIGHTING);
    gl.glLineWidth(4);
    double x = position[0];
    double y = position[1];
    double z = position[2];
    double mag = Math.sqrt(x*x+y*y+z*z);
    double radius = 10.0/mag;
    x*=radius;
    y*=radius;
    z*=radius;
    gl.glColor3d(1,1,1);
    gl.glBegin(GL2.GL_LINES);
      gl.glVertex3d(0,0,0);
      gl.glVertex3d(x,y,z);
    gl.glEnd();
    gl.glLineWidth(1);
    gl.glEnable(GL2.GL_LIGHTING);
  }
  
  private void displaySpotlight(GL2 gl, GLUT glut) {
    gl.glDisable(GL2.GL_LIGHTING);
    gl.glLineWidth(4);
    double x = direction[0];
    double y = direction[1];
    double z = direction[2];
    double mag = Math.sqrt(x*x+y*y+z*z);
    x = 0.5*x/mag;
    y = 0.5*y/mag;
    z = 0.5*z/mag;
    x += position[0];
    y += position[1];
    z += position[2];
    gl.glColor3d(1,1,1);
    gl.glBegin(GL2.GL_LINES);
      gl.glVertex3d(position[0], position[1], position[2]);
      gl.glVertex3d(x,y,z);
    gl.glEnd();
    gl.glLineWidth(1);
    gl.glEnable(GL2.GL_LIGHTING);
  }
  
  public String toString() {
    return "["+position[0]+", "+position[1]+", "+position[2]+"]";
  }

}
