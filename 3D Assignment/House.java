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

public class House
{
  
  private Mesh meshPlane1,meshPlane2,meshPlane3,meshPlane4,meshPlane5,meshPlane6,meshPlane7;

  public Render floor,wall1,wall2,wall3,wall4,ceil,window;

  private Texture floorTex,wall1Tex,wall2Tex,wall3Tex,wall4Tex,ceilTex,windowTex;
  
  public Texture lambTex;

 
  public House()
  {
  	
  }

  public void createRenderObjects(GL2 gl)
  {
    floorTex = loadTexture(gl, "wood.jpg");
    wall1Tex = loadTexture(gl, "wall1.jpg");
    wall2Tex = loadTexture(gl, "wall2.jpg");
    wall3Tex = loadTexture(gl, "wall3.jpg");
    wall4Tex = loadTexture(gl, "wall4.jpg");
    ceilTex = loadTexture(gl, "ceil.jpg");
    lambTex = loadTexture(gl, "lamb.jpg");
    windowTex = loadTexture(gl, "windowScene.jpeg");

    meshPlane1 = ProceduralMeshFactory.createPlane(20,20,10,10,1,1);
    meshPlane2 = ProceduralMeshFactory.createPlane(20,12,10,10,1,1);
    meshPlane3 = ProceduralMeshFactory.createPlane(20,12,10,10,1,1);
    meshPlane4 = ProceduralMeshFactory.createPlane(20,12,10,10,1,1);
    meshPlane5 = ProceduralMeshFactory.createPlane(20,12,10,10,1,1);
    meshPlane6 = ProceduralMeshFactory.createPlane(20,20,10,10,1,1);
    meshPlane7 = ProceduralMeshFactory.createPlane(5,4,10,10,1,1);

    floor = new Render(meshPlane1, floorTex);
    wall1 = new Render(meshPlane2, wall1Tex);
    wall2 = new Render(meshPlane3, wall2Tex);
    wall3 = new Render(meshPlane4, wall3Tex);
    wall4 = new Render(meshPlane5, wall4Tex);
    ceil = new Render(meshPlane6, ceilTex);
    window = new Render(meshPlane7, windowTex);

    floor.initialiseDisplayList(gl, true);
    wall1.initialiseDisplayList(gl, true);
    wall2.initialiseDisplayList(gl, true);
    wall3.initialiseDisplayList(gl, true);
    wall4.initialiseDisplayList(gl, true);
    ceil.initialiseDisplayList(gl, true);
    window.initialiseDisplayList(gl,true);
  }

  private Texture loadTexture(GL2 gl, String filename) {
    Texture tex = null;
    // since file loading is involved, must use try...catch
    try {
      File f = new File(filename);

      // The following line results in a texture that is flipped vertically (i.e. is upside down)
      // due to OpenGL and Java (0,0) position being different:
      // tex = TextureIO.newTexture(new File(filename), false);

      // So, instead, use the following three lines which flip the image vertically:
      BufferedImage img = ImageIO.read(f); // read file into BufferedImage
      ImageUtil.flipImageVertically(img);
    
      // No mip-mapping.
      tex = AWTTextureIO.newTexture(GLProfile.getDefault(), img, false);

      // Different filter settings can be used to give different effects when the texture
      // is applied to a set of polygons.
      tex.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
      tex.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
   
    }
    catch(Exception e) {
      System.out.println("Error loading texture " + filename); 
    }
    return tex;
  } 
  
  public void displayHouse(GL2 gl)
  {
    gl.glPushMatrix();
      //floor
      floor.renderDisplayList(gl);
      //wall1
      gl.glPushMatrix();
        gl.glTranslated(10,0,0);
        gl.glRotated(90,0,-1,0);
        gl.glRotated(90,1,0,0);
        gl.glTranslated(0,0,-6);
        wall1.renderDisplayList(gl);
      gl.glPopMatrix();

      //wall2
      gl.glPushMatrix();
        gl.glTranslated(-10,0,0);
        gl.glRotated(90,0,1,0);
        gl.glRotated(90,1,0,0);
        gl.glTranslated(0,0,-6);
        gl.glPushMatrix();
          gl.glTranslated(-5,0,0);
          window.renderDisplayList(gl);
        gl.glPopMatrix();
        wall2.renderDisplayList(gl);
      gl.glPopMatrix();
      //wall3
      gl.glPushMatrix();
        gl.glTranslated(0,0,-10);
        gl.glRotated(90,1,0,0);
        gl.glTranslated(0,0,-6);
        wall3.renderDisplayList(gl);
      gl.glPopMatrix();
      //wall4
      gl.glPushMatrix();
        gl.glTranslated(0,0,10);
        gl.glRotated(180,0,1,0);
        gl.glRotated(90,1,0,0);
        gl.glTranslated(0,0,-6);
        wall4.renderDisplayList(gl);
      gl.glPopMatrix();
      //ceiling
      gl.glPushMatrix();
        gl.glTranslated(0,12,0);
        gl.glRotated(180,1,0,0);
        ceil.renderDisplayList(gl);
      gl.glPopMatrix();
    gl.glPopMatrix();


  }


}