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

public class M1Scene {

  private GLU glu = new GLU();
  private GLUT glut = new GLUT();

  private Robot robot ;

  private House house=new House();

  private Obstacles obstacles;

  private final double r=1;

  private final double Left=1;
  private final double Right=-1;

  private double rotate=0.0;
  private boolean objectsOn = true;

  private int canvaswidth=0, canvasheight=0;
  //light0:world light
  //light1:robot eye spotlight
  //light2:lamb soptlight1
  //light3:lamb spotlight2
  private Light light0,light1,light2,light3;
  private Camera camera;

  private Axes axes;


  private AnimationScene animationScene;


  public M1Scene(GL2 gl, Camera camera) {

    animationScene =new AnimationScene();
    robot=new Robot(animationScene);
    obstacles=new Obstacles(animationScene);

    light0 = new Light(GL2.GL_LIGHT0);  // Create a default light

    float[] position={0,0,0,1};
    light1= new Light(GL2.GL_LIGHT1,position);
    float[] direction={0,0,-1};
    light1.makeSpotlight(direction,10f);

    float[] position2={0,0,0,1};
    light2= new Light(GL2.GL_LIGHT2,position2);
    float[] direction2={0,0,-1};
    light2.makeSpotlight(direction2,10f);

    float[] position3={0,0,0,1};
    light3= new Light(GL2.GL_LIGHT3,position3);
    float[] direction3={0,0,-1};
    light3.makeSpotlight(direction3,10f);

    this.camera = camera;
    axes = new Axes(2.2, 1.8, 1.6);

    house.createRenderObjects(gl);
  }

  
  
  // called from SG1.reshape() if user resizes the window
  public void setCanvasSize(int w, int h) {
    canvaswidth=w;
    canvasheight=h;
  }

  public void setObjectsDisplay(boolean b) {
    objectsOn = b;
  }

  public Light getLightRobertEye() {
    return light1;
  }

  public Light getLightWorldlight()
  {
    return light0;
  }

  public Light getLightlamblight1()
  {
    return light2;
  }

  public Light getLightlamblight2()
  {
    return light3;
  }


  public Axes getAxes() {
    return axes;
  }
  
  public void reset() {
    animationScene.reset();
    rotate=0.0;
    setObjectsDisplay(true);
  }

  public void startAnimation() {
    animationScene.startAnimation();
  }

  public void pauseAnimation() {
    animationScene.pauseAnimation();
  }


  public void incRotate() {

  }

  public void update() {
    //incRotate();
    animationScene.update();
  }

  private void doLight0(GL2 gl) {
    gl.glPushMatrix();
      light0.use(gl, glut, true);
    gl.glPopMatrix();
  }

  private void adjustmentForRobotEye(GL2 gl) {
    double eyeZ=1;
    gl.glTranslated(0,0,eyeZ); 
  }

  private void doSpotLightRobotEye(GL2 gl)
  {
    gl.glPushMatrix();
      light1.use(gl, glut, true);
    gl.glPopMatrix();

  }

  private void doSpotLightlamb1(GL2 gl)
  {
    gl.glPushMatrix();
      light2.use(gl, glut, true);
    gl.glPopMatrix();
  }

  private void doSpotLightlamb2(GL2 gl)
  {
    gl.glPushMatrix();
      light3.use(gl, glut, true);
    gl.glPopMatrix();
  }

  


  private double poleX = -4;
  private double poleZ = -4;
  private double poleHeight = 4;
  
  public void render(GL2 gl) {
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT|GL2.GL_DEPTH_BUFFER_BIT);
    gl.glLoadIdentity();
    camera.view(glu);      // Orientate the camera
    doLight0(gl);          // Place the default light
    

    displayRobotEyelight(gl);

    displaylambSpotlight(gl);



    if (axes.getSwitchedOn()) 
      axes.display(gl, glut);

    if (objectsOn) {  // Render the objects
      gl.glPushMatrix();
        gl.glPushMatrix();
          gl.glTranslated(5,0,-5);
          displayLamb(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
          gl.glTranslated(-5,0,5);
          displayLamb(gl);
        gl.glPopMatrix();
      gl.glPopMatrix();

      displayLamb(gl);

      house.displayHouse(gl);

      obstacles.displayObstacles(gl);

      gl.glPushMatrix();
        transformationsForRobert(gl);
        moveRobot(gl);
        robot.displayRobot(gl);
      gl.glPopMatrix();
                
    }
  }

  private void moveRobot(GL2 gl)
  {
    double robotMove1 = animationScene.getParam(animationScene.ROBOT_PARAM1);
    double robotMove2 = animationScene.getParam(animationScene.ROBOT_PARAM2);
    gl.glTranslated(robotMove1,robotMove2,0);

    
    double robotMove4 = animationScene.getParam(animationScene.ROBOT_PARAM4);
    gl.glTranslated(0,0,robotMove4);

    double robotMove5 = animationScene.getParam(animationScene.ROBOT_PARAM5);
    gl.glRotated(robotMove5,0,-1,0);
    gl.glRotated(robotMove5,0,0,1);

    double robotMove6 = animationScene.getParam(animationScene.ROBOT_PARAM6);
    gl.glTranslated(0,0,robotMove6);

    double robotMove8 = animationScene.getParam(animationScene.ROBOT_PARAM8);
    gl.glTranslated(0,robotMove8,0);

    double robotMove7 = animationScene.getParam(animationScene.ROBOT_PARAM7);
    gl.glRotated(robotMove7,0,0,1);

    double robotMove9 = animationScene.getParam(animationScene.ROBOT_PARAM9);
    gl.glRotated(robotMove9,0,-1,0);

    double robotMove10 = animationScene.getParam(animationScene.ROBOT_PARAM10);
    double robotMove11 = animationScene.getParam(animationScene.ROBOT_PARAM11);
    double robotMove12 = animationScene.getParam(animationScene.ROBOT_PARAM12);
    gl.glTranslated(robotMove11,robotMove12,robotMove10);
  }

  private void displaylambSpotlight(GL2 gl)
  {
    gl.glPushMatrix();
      gl.glTranslated(5,11.5,-5);
      rotationForlight(gl,5,7,-1);
      doSpotLightlamb1(gl);
    gl.glPopMatrix();

    gl.glPushMatrix();
      gl.glTranslated(-5,11.5,5);
      rotationForlight(gl,-5,7,-1);
      doSpotLightlamb2(gl);
    gl.glPopMatrix();
  }

  private void displayRobotEyelight(GL2 gl)
  { 
    gl.glPushMatrix();
      transformationsForRobert(gl);
      moveRobot(gl);
      adjustmentForRobotEye(gl);
      rotationForlight(gl,poleX+4.5155,poleHeight,poleZ);
      doSpotLightRobotEye(gl);
    gl.glPopMatrix();
  }

  private void transformationsForRobert(GL2 gl) {
    gl.glTranslated(poleX,poleHeight,poleZ);
  }

  private void rotationForlight(GL2 gl, double x, double y, double z) {
    x = -x; 
    y = -y; 
    z = -z;
    double r = Math.sqrt(x*x+y*y+z*z);
    double phi = Math.asin(y/r); 
    double theta = Math.atan2(x,z); 
    gl.glRotated(180+theta*180/Math.PI,0,1,0);
    gl.glRotated(phi*180/Math.PI,1,0,0);          
  }

  private void displayLamb(GL2 gl)
  {
    setMaterial(gl,255f,255f,255f);
    house.lambTex.enable(gl);
    house.lambTex.bind(gl);
    house.lambTex.setTexParameteri(gl, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);

    GLUquadric quadric = glu.gluNewQuadric();
    glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
    glu.gluQuadricTexture(quadric, true);        
    glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);

    gl.glPushMatrix();
      gl.glTranslated(0,11.5,0);
      gl.glRotated(90,-1,0,0);
      glu.gluCylinder(quadric, 0.5, 0.2, 0.5, 20, 20);
    gl.glPopMatrix();

    glu.gluDeleteQuadric(quadric);  
  
    house.lambTex.disable(gl);


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


