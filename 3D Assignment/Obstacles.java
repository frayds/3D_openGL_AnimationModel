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

public class Obstacles
{
  private GLU glu = new GLU();
  private GLUT glut = new GLUT();
  private AnimationScene animationScene;

  public Obstacles(AnimationScene animationScene1)
  {
  	animationScene=animationScene1;
  }

  public void displayObstacles(GL2 gl)
  {
    obstacleCube(gl);
    obstacleTorus(gl);
    obstacleCylinder(gl);

  }

  private void obstacleCube(GL2 gl)
  {
    setMaterial(gl,255f,128f,0f);
    gl.glPushMatrix();
      gl.glTranslated(5,1,-3);
      double cubeParam = animationScene.getParam(animationScene.CUBE_PARAM);
      gl.glTranslated(0,0,cubeParam);
      glut.glutSolidCube(2);
    gl.glPopMatrix();
  }

  private void obstacleTorus(GL2 gl)
  {
    setMaterial(gl,34f,139f,34f);
    gl.glPushMatrix();
      gl.glTranslated(10,6,5);
      gl.glRotated(90,0,-1,0);
      glut.glutSolidCylinder(0.3,1.5,30,30);
      gl.glRotated(90,0,1,0);
      gl.glTranslated(-3.9,0,0);
        glut.glutSolidTorus(0.5,2.5,30,30);
    gl.glPopMatrix();
  }

  private void obstacleCylinder(GL2 gl)
  {
    setMaterial(gl,138f,43,226f);
    gl.glPushMatrix();
      gl.glTranslated(-1,0,2);
      gl.glRotated(90,-1,0,0);
      glut.glutSolidCylinder(1,12,30,30);
    gl.glPopMatrix();
  }
  private void setMaterial(GL2 gl, float r, float g, float b) {
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