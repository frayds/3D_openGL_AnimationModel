/**
 * A class for a camera.
 * The camera always remains 'upright' with respect to the world y axis. 
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (26/07/2013)
 */

import com.jogamp.opengl.glu.GLU;
 
public class Camera {

  public static final double[] DEFAULT_EYE_POSITION = {10.0,12.0,8.0};
  public static final double[] DEFAULT_LOOKAT_POSITION = {0.0,0.0,0.0};
  public static final double[] DEFAULT_UP_DIRECTION = {0.0,1.0,0.0};
  
  private double theta, phi, radius;

  private double[] eye = new double[3];
  private double[] lookAt = new double[3];
  private double[] upvec = new double[3];
  
  /**
   * Constructor.
   * @param theta anticlockwise rotation around y axis starting at x axis and rotating 'the long way' round to the z axis
   * @param phi inclination from the gorund plane
   * @param radius distance from world origin to camera postion
   */ 
  public Camera(double theta, double phi, double radius) {
    this.theta = theta;
    this.phi = phi;
    this.radius = radius;
    calcEyePosition();
  }    

  /**
   * Increment the values of theta and phi which control the camera position on a virtual sphere centred on the world origin
   * 
   * @param thetaInc Increment for theta
   * @param phiInc Increment for phi
   */  
  public void updateThetaPhi(double thetaInc, double phiInc) {
    theta += thetaInc;
    phi += phiInc;
    calcEyePosition();
  }

  /**
   * Increment the value of the radius which controls the distance of the camera from the world origin
   * 
   * @param radisInc Increment for radius
   */    
  public void updateRadius(double radiusInc) {
    radius += radiusInc;
    calcEyePosition();
  }

  /**
   * Gets the current world x position of the camera
   * 
   * @return  World x position of the camera
   */ 
  public double getEyeX() { return eye[0]; }

  /**
   * Gets the current world y position of the camera
   * 
   * @return  World y position of the camera
   */ 
  public double getEyeY() { return eye[1]; }

  /**
   * Gets the current world z position of the camera
   * 
   * @return  World z position of the camera
   */ 
  public double getEyeZ() { return eye[2]; }

  /**
   * Gets the current x component of the camera's up vector
   * 
   * @return  World x position of the camera
   */ 
  public double getUpVecX() { return upvec[0]; }

  /**
   * Gets the current y component of the camera's up vector
   * 
   * @return  World y position of the camera
   */ 
  public double getUpVecY() { return upvec[1]; }

  /**
   * Gets the current z component of the camera's up vector
   * 
   * @return  World z position of the camera
   */ 
  public double getUpVecZ() { return upvec[2]; }

  private void calcEyePosition() {
    double cy, cz, sy, sz;
    cy = Math.cos(theta);
    sy = Math.sin(theta);
    cz = Math.cos(phi);
    sz = Math.sin(phi);
    	
    eye[0] = radius*cy*cz;
    eye[1] = radius*sz;
    eye[2] = -radius*sy*cz;
    
    upvec[0] = -cy*sz;
    upvec[1] = cz;
    upvec[2] = sy*sz;

    // To keep the camera always pointing upwards, if the y-component
    // of the up vector is negative, invert the whole up vector    
    if (upvec[1]<0) {
      upvec[0]=-upvec[0];
      upvec[1]=-upvec[1];
      upvec[2]=-upvec[2];
    }
  }

  /**
   * Use the GLU object and the method gluLookAt to set the OpenGL camera postion and direction
   * 
   * @param glu The GLU object
   */   
  public void view(GLU glu) {
    glu.gluLookAt(getEyeX(), getEyeY(), getEyeZ(),
	          0.0f, 0.0f, 0.0f, 
		  getUpVecX(), getUpVecY(), getUpVecZ());
  }

  /**
   * Standard use of toString method
   * 
   * @return A string containing the camera position in world space
   */      
  public String toString() {
    return "["+eye[0]+", "+eye[1]+", "+eye[2]+"]";
  }

  /**
   * Test harness
   */
  public static void main(String[] args) {
    Camera camera = new Camera(Math.toRadians(-45), Math.toRadians(30), 8.0f);
    System.out.println(camera);
  }
}
