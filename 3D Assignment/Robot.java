/* I declare that this code is my own work */
/*Author <Yifan Pu><ypu6@sheffield.ac.uk>*/

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;
import com.jogamp.opengl.util.awt.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.awt.*;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

public class Robot{
  private GLU glu = new GLU();
  private GLUT glut = new GLUT();
  private AnimationScene animationScene;
  private final double r=1;
  private final double Left=1;
  private final double Right=-1;


  public Robot(AnimationScene animationScene1)
  {
    animationScene=animationScene1;
  }

  public void moveRobotArm(GL2 gl,double direction)
  {
    double robotMove3 =animationScene.getParam(animationScene.ROBOT_PARAM3);
    gl.glRotated(robotMove3,0,direction,0);
  }

  
  public void displayRobot(GL2 gl)
  {

    gl.glPushMatrix();
      displayHead(gl);
      //eyes
        gl.glPushMatrix();
          displayEyes(gl); 
        gl.glPopMatrix();
      //hat
        gl.glPushMatrix();
          displayHat(gl); 
        gl.glPopMatrix();
      //HatTop
        gl.glPushMatrix();
          displayHatTop(gl);
        gl.glPopMatrix();
      //Leftarm
      gl.glPushMatrix();
        displayPostArm(gl,Left);
        //leftForearm
        gl.glPushMatrix();
          gl.glTranslated(0,0,r*1.25);
          displayForeArm(gl,Left);
          //leftHand
          gl.glPushMatrix();
            gl.glTranslated(0,0,r*1.25);
            displayHand(gl,Left);
          gl.glPopMatrix();

        gl.glPopMatrix();

      gl.glPopMatrix();
      //RightArm
      gl.glPushMatrix();
        displayPostArm(gl,Right);
        //RightForeArm
        gl.glPushMatrix();
          gl.glTranslated(0,0,r*1.25);
          displayForeArm(gl,Right);
          //rightHand
          gl.glPushMatrix();
            gl.glTranslated(0,0,r*1.25);
            displayHand(gl,Right);
          gl.glPopMatrix();
        gl.glPopMatrix();
      gl.glPopMatrix();
    gl.glPopMatrix(); 
  }

  public void displayFinger(GL2 gl)
  {
    setMaterial(gl,160f,102f,211f);

    double SphereFinger=r*0.2;
      gl.glPushMatrix();
        gl.glRotated(45,0,-1,1);
        gl.glTranslated(SphereFinger*2,0,0);
        gl.glScaled(1.25,0.3,0.3);
        glut.glutSolidSphere(SphereFinger,30,30);
      gl.glPopMatrix();

      gl.glPushMatrix();
        gl.glRotated(45,0,0,-1);
        gl.glRotated(225,0,1,0);
        gl.glTranslated(SphereFinger*2,0,0);
        gl.glScaled(1.25,0.3,0.3);
        glut.glutSolidSphere(SphereFinger,30,30);
      gl.glPopMatrix();

      gl.glPushMatrix();
        gl.glRotated(90,0,0,-1);
        gl.glRotated(45,0,-1,0);
        gl.glTranslated(SphereFinger*2,0,0);
        gl.glScaled(1.25,0.3,0.3);
        glut.glutSolidSphere(SphereFinger,30,30);
      gl.glPopMatrix();
    
  }

  public void displayHand(GL2 gl,double direction)
  {
    setMaterial(gl,210f,105f,30f);

    double SphereHand=r*0.20;
    glut.glutSolidSphere(SphereHand,30,30);
    displayFinger(gl);
  }

  public void displayForeArm(GL2 gl,double direction)
  {
    setMaterial(gl,34f,139f,34f);

    double SphereShoulder=r*0.23;
    double CylinderR=r*0.15;
    glut.glutSolidSphere(SphereShoulder,30,30);
    gl.glRotated(90*direction,0,-1,0);
    gl.glTranslated(0,0,SphereShoulder*0.8);
    glut.glutSolidCylinder(CylinderR,r*1.3,30,30);
  }

  public void displayPostArm(GL2 gl,double direction)
  { 
    setMaterial(gl,106f,90f,205f);

    double CylinderR=r*0.15;
    gl.glRotated(90*direction,0,1,0);
    gl.glTranslated(0,0,r*0.95);
    moveRobotArm(gl,direction);
    glut.glutSolidCylinder(CylinderR,r*1.3,30,30);
  }

  public void displayHatTop(GL2 gl)
  {
    setMaterial(gl,255f,255f,0f);

    gl.glTranslated(0,2*r,0);
    gl.glScaled(0.15,0.15,0.15);
    glut.glutSolidSphere(r,30,30);
  }

  public void displayHat(GL2 gl)
  {
    setMaterial(gl,250f,240f,230f);

    double d=r*Math.sqrt(24f/25f);

    gl.glTranslated(0,d,0);
    gl.glRotated(90,-1,0,0);
    glut.glutSolidCone(0.2,r,30,30);

  }

  public void displayEyes(GL2 gl)
  {
    setMaterial(gl,0f,199f,140f);

    double eyeZ=1;

    gl.glTranslated(0,0,eyeZ);
    glut.glutSolidSphere(0.2*r,30,30);

  }
  public void displayHead(GL2 gl) {
    setMaterial(gl,240f,65f,85f);
    glut.glutSolidSphere(r,50,50);

  }
  public void setMaterial(GL2 gl, float r, float g, float b) {
    float[] matAmbientDiffuse = {r/255f, g/255f, b/255f, 1.0f};
    float[] matSpecular = {0.5f,0.5f,0.5f, 1.0f};
    float[] matShininess = {64f};
    float[] matEmission = {0.0f, 0.0f, 0.0f, 1.0f};
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matAmbientDiffuse, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);
  }

}

