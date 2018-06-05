/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maffek;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.*;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author ROkuński
 */
public class Maffek extends Applet implements KeyListener{
    
    BranchGroup scena;
    Alpha alpha_animacja;
    Alpha alpha_animacja2;
    Alpha alpha_animacja3;
    Alpha alpha_animacja4;
    Alpha alpha_animacja5;
    Alpha alpha_animacja6;
    
   RotationInterpolator obracacz;
   RotationInterpolator obracacz2;
   RotationInterpolator obracacz3;
   RotationInterpolator obracacz4;
   RotationInterpolator obracacz5;
   RotationInterpolator obracacz61;
   RotationInterpolator obracacz62;
    
   float a=0;
  // float aa=0;
   float b=0;
 //  float bb=0;
   float c=0;
 //  float cc=0;
   float d=0;
 //  float dd=0;
   float f=0;
  // float ff=0;
   float g=0;
  // float gg=0;
    
    int[] ktory = {0,0,0,0,0,0};
    int kierunek = 0;  //-1,0,1
    boolean nagrywanie = false, odtwarzanie = false, zatrzask = false ;
    boolean bylo_nag = false;
    int czesc[] = new int[10000];
    float pol[] = new float[10000];
    int l=0, k=0;
    
    
     private java.util.Timer       zegar = new java.util.Timer();
     
     private boolean spin = false;
     
     
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
    Vector3f pos_kamery = new Vector3f(0f,0f,5f);
    Transform3D kamera = new Transform3D();
    
    
    class Zadanie extends TimerTask{
        @Override
         public void run(){
             if(!odtwarzanie){
             obracacz.setMaximumAngle(a); obracacz.setMinimumAngle(a);
             obracacz2.setMaximumAngle(b); obracacz2.setMinimumAngle(b);
             obracacz3.setMaximumAngle(c); obracacz3.setMinimumAngle(c);
             obracacz4.setMaximumAngle(d); obracacz4.setMinimumAngle(d);
             obracacz5.setMaximumAngle(f); obracacz5.setMinimumAngle(f);
             obracacz61.setMaximumAngle(g); obracacz61.setMinimumAngle(g);
             obracacz62.setMaximumAngle(-g); obracacz62.setMinimumAngle(-g); 
             zegar.cancel();
             }else{
                switch(czesc[k]){
                     case 1:
                         obracacz.setMaximumAngle(pol[k]); obracacz.setMinimumAngle(pol[k]);break;
                     case 2:
                         obracacz2.setMaximumAngle(pol[k]); obracacz2.setMinimumAngle(pol[k]);break;
                    case 3:
                         obracacz3.setMaximumAngle(pol[k]); obracacz3.setMinimumAngle(pol[k]);break;
                     case 4:
                         obracacz4.setMaximumAngle(pol[k]); obracacz4.setMinimumAngle(pol[k]);break;
                     case 5:
                         obracacz5.setMaximumAngle(pol[k]); obracacz5.setMinimumAngle(pol[k]);break;
                     case 6:
                         obracacz61.setMaximumAngle(pol[k]); obracacz61.setMinimumAngle(pol[k]);
                         obracacz62.setMaximumAngle(-pol[k]); obracacz62.setMinimumAngle(-pol[k]);break;
                 }
                k++;
                if(k==l){
                     odtwarzanie = false;
                     k=0;
                     l=0;
                     bylo_nag = false;
                     zegar.cancel();
                }
            }  
          }
     }
    
 
    
    public BranchGroup utworzScene(){
      BranchGroup wezel_scena = new BranchGroup();
      BranchGroup wezel_2 = new BranchGroup();
      BranchGroup wezel_3 = new BranchGroup();
      BranchGroup wezel_4 = new BranchGroup();
      BranchGroup wezel_5 = new BranchGroup();
      BranchGroup wezel_6 = new BranchGroup();
      BranchGroup wezel_7 = new BranchGroup();


      TransformGroup obrot_animacja = new TransformGroup();
      obrot_animacja.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_2.addChild(obrot_animacja);
      
      TransformGroup obrot_animacja2 = new TransformGroup();
      obrot_animacja2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_3.addChild(obrot_animacja2);
      
      TransformGroup obrot_animacja3 = new TransformGroup();
      obrot_animacja3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_4.addChild(obrot_animacja3);
      
      TransformGroup obrot_animacja4 = new TransformGroup();
      obrot_animacja4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_5.addChild(obrot_animacja4);
      
      TransformGroup obrot_animacja5 = new TransformGroup();
      obrot_animacja5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_6.addChild(obrot_animacja5);
      
      TransformGroup obrot_animacja61 = new TransformGroup();
      obrot_animacja61.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_7.addChild(obrot_animacja61);
      
      TransformGroup obrot_animacja62 = new TransformGroup();
      obrot_animacja62.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      wezel_7.addChild(obrot_animacja62);
      
      alpha_animacja = new Alpha(-1,3000);
      alpha_animacja2 = new Alpha(-1,3000);
      alpha_animacja3 = new Alpha(-1,3000);
      alpha_animacja4 = new Alpha(-1,3000);
      alpha_animacja5 = new Alpha(-1,3000);
      alpha_animacja6 = new Alpha(-1,3000);
      
      Transform3D  tmp      = new Transform3D();

      
      tmp.set(new Vector3f(0.0f,-0.22f,0.015f));
      obracacz = new RotationInterpolator(alpha_animacja, obrot_animacja,tmp,0,0);
      
      tmp.set(new Vector3f(0.f,-0.17f,0.0f));
      tmp.setRotation(new AxisAngle4d(0,1,1,Math.PI));
      obracacz2 = new RotationInterpolator(alpha_animacja2, obrot_animacja2,tmp,0,0);
      
      tmp.set(new Vector3f(0.02f,0.26f,0.02f));
      tmp.setRotation(new AxisAngle4d(0,1,1,Math.PI));
      obracacz3 = new RotationInterpolator(alpha_animacja3, obrot_animacja3,tmp,0,0);
      
      tmp.set(new Vector3f(0.25f,0.378f,0.02f));
      tmp.setRotation(new AxisAngle4d(1,1,0,Math.PI));
      obracacz4 = new RotationInterpolator(alpha_animacja4, obrot_animacja4,tmp,0,0);
      
      tmp.set(new Vector3f(0.775f,0.38f,0.016f));
      tmp.setRotation(new AxisAngle4d(0,1,1,Math.PI));
      obracacz5 = new RotationInterpolator(alpha_animacja5, obrot_animacja5,tmp,0,0);
      
      tmp.set(new Vector3f(0.76f,0.22f,-0.02f));
      tmp.setRotation(new AxisAngle4d(1,1,0,Math.PI));
      obracacz61 = new RotationInterpolator(alpha_animacja6, obrot_animacja61,tmp,0,0);
      
      tmp.set(new Vector3f(0.76f,0.22f,0.04f));
      tmp.setRotation(new AxisAngle4d(1,1,0,Math.PI));
      obracacz62 = new RotationInterpolator(alpha_animacja6, obrot_animacja62,tmp,0,0);
      
      BoundingSphere bounds = new BoundingSphere();
      
      obracacz.setSchedulingBounds(bounds);
      obrot_animacja.addChild(obracacz);
      obracacz2.setSchedulingBounds(bounds);
      obrot_animacja2.addChild(obracacz2);
      obracacz3.setSchedulingBounds(bounds);
      obrot_animacja3.addChild(obracacz3);
      obracacz4.setSchedulingBounds(bounds);
      obrot_animacja4.addChild(obracacz4);
      obracacz5.setSchedulingBounds(bounds);
      obrot_animacja5.addChild(obracacz5);
      obracacz61.setSchedulingBounds(bounds);
      obrot_animacja61.addChild(obracacz61);
      obracacz62.setSchedulingBounds(bounds);
      obrot_animacja62.addChild(obracacz62);
      

      //ŚWIATŁA

    /*  AmbientLight lightA = new AmbientLight();
      lightA.setInfluencingBounds(bounds);
      wezel_scena.addChild(lightA);

      DirectionalLight lightD = new DirectionalLight();
      lightD.setInfluencingBounds(bounds);
      lightD.setDirection(new Vector3f(0.0f, 0.0f, -1.0f));
      lightD.setColor(new Color3f(1.0f, 1.0f, 1.0f));
      wezel_scena.addChild(lightD);*/
     
      Color3f kolor_swiatla_tla      = new Color3f(15f, 3.f, 2.0f);
         Color3f kolor_swiatla_kier     = new Color3f(0.8f, 1.1f, 0.3f);
         Color3f kolor_swiatla_pnkt     = new Color3f(1.0f, 1.0f, 1.0f);
         Color3f kolor_swiatla_sto      = new Color3f(3.0f, 1.0f, 5f);

         Vector3f kierunek_swiatla_kier = new Vector3f(1f, 6f, 1f);
         Vector3f kierunek_swiatla_kier1 = new Vector3f(3f, 6f, 1f);

         AmbientLight swiatlo_tla = new AmbientLight(kolor_swiatla_tla);
         AmbientLight swiatlo_tla1 = new AmbientLight(kolor_swiatla_tla);
         DirectionalLight swiatlo_kier = new DirectionalLight(kolor_swiatla_kier, kierunek_swiatla_kier1);
         DirectionalLight swiatlo_kier1 = new DirectionalLight(kolor_swiatla_kier, kierunek_swiatla_kier);
         PointLight swiatlo_pnkt = new PointLight(kolor_swiatla_pnkt, new Point3f(2.5f,1.f,1.5f), new Point3f(0.1f,0.1f,.1f));
         SpotLight swiatlo_sto = new SpotLight(kolor_swiatla_sto, new Point3f(5f, 2f, 0f), new Point3f(0.01f,0.01f,0.01f), new Vector3f(0f, 0f, .0f),1.59f, 1);
      
         swiatlo_tla.setInfluencingBounds(bounds);
         swiatlo_kier.setInfluencingBounds(bounds);
         
         wezel_scena.addChild(swiatlo_tla);
       wezel_scena.addChild(swiatlo_kier);
       wezel_2.addChild(swiatlo_kier1);
       wezel_2.addChild(swiatlo_tla1);
       obrot_animacja2.addChild(swiatlo_pnkt);
       wezel_3.addChild(swiatlo_sto);
       
      //----------------------------------------------
      Transform3D  p_podstawy   = new Transform3D();
      p_podstawy.set(new Vector3f(0.0f,-0.5f,0.0f));
       p_podstawy.setScale(0.2);
      
      TransformGroup transformacja_c = new TransformGroup(p_podstawy);
      transformacja_c.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("1.obj");
              transformacja_c.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      
      transformacja_c.setTransform(p_podstawy);
      wezel_scena.addChild(transformacja_c);
      
     //---------------------------------------------
     // ColorCube pierwsze = new ColorCube(0.15f);
      Transform3D  p_pierwsze   = new Transform3D();
      p_pierwsze.set(new Vector3f(-0.015f,-0.22f,0.015f));
      p_pierwsze.setScale(0.16);
      
      TransformGroup transformacja_1 = new TransformGroup(p_pierwsze);
      transformacja_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
      try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("2.obj");
              transformacja_1.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      

      transformacja_1.setTransform(p_pierwsze);
      obrot_animacja.addChild(transformacja_1);
      obrot_animacja.addChild(wezel_3);
      
       //---------------------------------------------
     // ColorCube drugie = new ColorCube(0.1f);
      Transform3D  p_drugie   = new Transform3D();
      p_drugie.set(new Vector3f(+0.025f,0.04f,0.016f));
      p_drugie.setScale(0.3);
      Transform3D tmp_rot = new Transform3D();
      tmp_rot.rotY(Math.PI/2);
      p_drugie.mul(tmp_rot);
      
      TransformGroup transformacja_2 = new TransformGroup(p_drugie);
      transformacja_2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
         try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("3.obj");
              transformacja_2.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      

      transformacja_2.setTransform(p_drugie);
      obrot_animacja2.addChild(transformacja_2);
      obrot_animacja2.addChild(wezel_4);
      
        //---------------------------------------------
   //   ColorCube trzecie = new ColorCube(0.1f);
      Transform3D  p_trzecie   = new Transform3D();
      p_trzecie.set(new Vector3f(0.195f,0.325f,0.017f));
      p_trzecie.setScale(0.28);
      tmp_rot.rotY(Math.PI/2);
      p_trzecie.mul(tmp_rot);
      tmp_rot.rotX(-Math.PI);
      p_trzecie.mul(tmp_rot);
      tmp_rot.rotZ(-Math.PI);
      p_trzecie.mul(tmp_rot);
      
      TransformGroup transformacja_3 = new TransformGroup(p_trzecie);
      transformacja_3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
        try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("4.obj");
              transformacja_3.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      
      
      transformacja_3.setTransform(p_trzecie);
      obrot_animacja3.addChild(transformacja_3);
      obrot_animacja3.addChild(wezel_5);
      
       //---------------------------------------------
    //  ColorCube czwarte = new ColorCube(0.1f);
      Transform3D  p_czwarte   = new Transform3D();
      p_czwarte.set(new Vector3f(0.66f,0.387f,0.01f));
      p_czwarte.setScale(0.19);
      tmp_rot.rotY(Math.PI/2);
      p_czwarte.mul(tmp_rot);
      tmp_rot.rotX(-Math.PI);
      p_czwarte.mul(tmp_rot);
      
      TransformGroup transformacja_4 = new TransformGroup(p_czwarte);
      transformacja_4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
        try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("5.obj");
              transformacja_4.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      

      transformacja_4.setTransform(p_czwarte);
      obrot_animacja4.addChild(transformacja_4);
      obrot_animacja4.addChild(wezel_6);
      
       //---------------------------------------------
    //  ColorCube piate = new ColorCube(0.1f);
      Transform3D  p_piate   = new Transform3D();
      p_piate.set(new Vector3f(0.775f,0.35f,0.016f));
      p_piate.setScale(0.085);
      tmp_rot.rotY(Math.PI/2);
      p_piate.mul(tmp_rot);
      
      TransformGroup transformacja_5 = new TransformGroup(p_piate);
       transformacja_5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
        try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("6.obj");
              transformacja_5.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      

      transformacja_5.setTransform(p_piate);
      obrot_animacja5.addChild(transformacja_5);
      
      
      Transform3D  p_piate1   = new Transform3D();
      
      p_piate1.set(new Vector3f(0.768f,0.24f,0.016f));
      p_piate1.setScale(0.06);
      tmp_rot.rotY(Math.PI);
      p_piate1.mul(tmp_rot);
      
      TransformGroup transformacja_51 = new TransformGroup(p_piate);
       transformacja_51.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
        try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("7.obj");
              transformacja_51.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}      

      
      transformacja_51.setTransform(p_piate1);
      obrot_animacja5.addChild(transformacja_51);
      obrot_animacja5.addChild(wezel_7);
       //---------------------------------------------
    // ColorCube szoste1 = new ColorCube(0.02f);
      Transform3D  p_szoste1   = new Transform3D();
      p_szoste1.set(new Vector3f(0.78f,0.17f,-0.025f));
      p_szoste1.setScale(0.07);
      
      TransformGroup transformacja_61 = new TransformGroup(p_szoste1);
      transformacja_61.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
        try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("8_1.obj");
              transformacja_61.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}
      

      transformacja_61.setTransform(p_szoste1);
      obrot_animacja61.addChild(transformacja_61);
      
         //---------------------------------------------
     // ColorCube szoste2 = new ColorCube(0.02f);
      Transform3D  p_szoste2   = new Transform3D();
      p_szoste2.set(new Vector3f(0.78f,0.17f,0.055f));
      p_szoste2.setScale(0.07);
      tmp_rot.rotY(Math.PI);
      p_szoste2.mul(tmp_rot);
      
      TransformGroup transformacja_62 = new TransformGroup(p_szoste2);
     transformacja_62.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      
        try{
          Scene s=null;
          ObjectFile f=new ObjectFile();
          f.setFlags(ObjectFile.RESIZE| ObjectFile.TRIANGULATE|ObjectFile.STRIPIFY);
          if(true){
              s=f.load("8_1.obj");
              transformacja_62.addChild(s.getSceneGroup());
          }
      
      }
      
     catch (FileNotFoundException e) {
	  System.err.println(e);
	  System.exit(1);
	}
    catch (ParsingErrorException e) {
	  System.err.println(e);
          System.err.println(e.getLocalizedMessage());
	  System.exit(1);
	}
    catch (IncorrectFormatException e) {
          System.err.println(e);
	  System.exit(1);
	}

      transformacja_62.setTransform(p_szoste2);
      obrot_animacja62.addChild(transformacja_62);
      
      Appearance  wygladCylindra = new Appearance();
      wygladCylindra.setColoringAttributes(new ColoringAttributes(0.5f,0.5f,0.9f,ColoringAttributes.NICEST));
      Cylinder podloze = new Cylinder(2f,0.01f, Cylinder.GENERATE_NORMALS|Cylinder.GENERATE_TEXTURE_COORDS, wygladCylindra);
      TransformGroup t_podloze = new TransformGroup();
      Transform3D polozenie_p = new Transform3D();
      polozenie_p.set(new Vector3f(0f,-0.65f,0f));
      t_podloze.addChild(podloze);
      t_podloze.setTransform(polozenie_p);
      wezel_scena.addChild(t_podloze);
     wezel_scena.addChild(wezel_2);    
      return wezel_scena;
    }
    
    Maffek(){
        setLayout(new BorderLayout());
        canvas3D.setPreferredSize(new Dimension(1280,960));
        canvas3D.addKeyListener(this);
        add(BorderLayout.CENTER,canvas3D);
       
        setVisible(true);

        scena = utworzScene();
	scena.compile();
        
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

	// add mouse behaviors to the ViewingPlatform
	ViewingPlatform viewingPlatform = simpleU.getViewingPlatform();

	PlatformGeometry pg = new PlatformGeometry();
        
        viewingPlatform.setPlatformGeometry( pg );
      
	// This will move the ViewPlatform back a bit so the
	// objects in the scene can be viewed.
	viewingPlatform.setNominalViewingTransform();

	if (!spin) {
            OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE|OrbitBehavior.STOP_ZOOM);
            orbit.setReverseTranslate(true);
            orbit.setMinRadius(1.0);
            orbit.setRotationCenter(new Point3d(0.0f,-0.5f,0.0f));
            orbit.setRotFactors(0.4,0.4);
            orbit.setTransFactors(0.25,0.25);
            orbit.setZoomFactor(0.25);
            orbit.setSchedulingBounds(bounds);
            viewingPlatform.setViewPlatformBehavior(orbit);	    
	}        
        
        // Ensure at least 5 msec per frame (i.e., < 200Hz)
	simpleU.getViewer().getView().setMinimumFrameCycleTime(5);
        
        kamera.set(pos_kamery);
        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(kamera);
        simpleU.addBranchGraph(scena);
    }
    @Override
    public void keyPressed(KeyEvent e){
       if(!odtwarzanie){ 
        if(e.getKeyCode()== KeyEvent.VK_1){
            ktory[0]=1;
            for(int i=1;i<6;i++)
                ktory[i]=0;
        }
        if(ktory[0]==1 && e.getKeyCode()== KeyEvent.VK_A ){
            a-=0.01f;
           kierunek = -1;
         }else if(ktory[0]==1 && e.getKeyCode()== KeyEvent.VK_D ){
             a+=0.01f;
             kierunek = 1;
         }
         
         if(e.getKeyCode()== KeyEvent.VK_2){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[1]=1;
        }
        if(ktory[1]==1 && e.getKeyCode()== KeyEvent.VK_S ){
            b-=0.01f;
           kierunek = -1;
         }else  if(ktory[1]==1 && e.getKeyCode()== KeyEvent.VK_W ){
             b+=0.01f;
             kierunek = 1;
         }
         
        if(e.getKeyCode()== KeyEvent.VK_3){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[2]=1;
        }
        if(ktory[2]==1 && e.getKeyCode()== KeyEvent.VK_S ){
            c-=0.01f;
           kierunek = -1;
         }else   if(ktory[2]==1 && e.getKeyCode()== KeyEvent.VK_W ){
             c+=0.01f;
             kierunek = 1;
         }
         
         if(e.getKeyCode()== KeyEvent.VK_4){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[3]=1;
        }
        if(ktory[3]==1 && e.getKeyCode()== KeyEvent.VK_A ){
            d-=0.01f;
           kierunek = -1;
         }else  if(ktory[3]==1 && e.getKeyCode()== KeyEvent.VK_D ){
             d+=0.01f;
             kierunek = 1;
         }
         
       if(e.getKeyCode()== KeyEvent.VK_5){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[4]=1;
        }
        if(ktory[4]==1 && e.getKeyCode()== KeyEvent.VK_S ){
            f-=0.01f;
           kierunek = -1;
         }else if(ktory[4]==1 && e.getKeyCode()== KeyEvent.VK_W ){
             f+=0.01f;
             kierunek = 1;
         }
         
         if(e.getKeyCode()== KeyEvent.VK_6){
            for(int i=0;i<5;i++)
                ktory[i]=0;
            ktory[5]=1;
        }
        if(ktory[5]==1 && e.getKeyCode()== KeyEvent.VK_A ){
            g-=0.01f;
           kierunek = -1;
         }else if(ktory[5]==1 && e.getKeyCode()== KeyEvent.VK_D ){
             g+=0.01f;
             kierunek = 1;
         }
        
        if(b>(float)Math.PI/5)b=(float)Math.PI/5;
        if(b<-(float)Math.PI/5)b=-(float)Math.PI/5;
        
        if(c>3*(float)Math.PI/4)c=3*(float)Math.PI/4;
        if(c<-(float)Math.PI/4)c=-(float)Math.PI/4;
        
        if(f>7*(float)Math.PI/6)f=7*(float)Math.PI/6;
        if(f<-(float)Math.PI/6)f=-(float)Math.PI/6;
        
        if(g>(float)Math.PI/6)g=(float)Math.PI/6;
        if(g<-(float)Math.PI/18)g=-(float)Math.PI/18;
         
         if(e.getKeyCode()==KeyEvent.VK_R){
              kamera.set(pos_kamery);
            simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(kamera);
         }
         
         if(e.getKeyCode() == KeyEvent.VK_C){
             for(int i=0;i<6;i++)
                ktory[i]=0;
             a=0;b=0;c=0;
             d=0;f=0;g=0;
             zegar = new java.util.Timer();
             zegar.scheduleAtFixedRate(new Zadanie(),0,20);
         }
         
         if(e.getKeyCode()== KeyEvent.VK_N && !zatrzask){
             nagrywanie=true;
             czesc[0] = 1; pol[0]= a;  
             czesc[1] = 2; pol[1]= b;  
             czesc[2] = 3; pol[2]= c;  
             czesc[3] = 4; pol[3]= d;  
             czesc[4] = 5; pol[4]= f;  
             czesc[5] = 6; pol[5]= g;
             l=6;
             bylo_nag = true;
             zatrzask = true;
         }
         
         if(nagrywanie){
            if(ktory[0]==1){czesc[l] = 1; pol[l]= a; l++; }
            if(ktory[1]==1){czesc[l] = 2; pol[l]= b; l++; }
            if(ktory[2]==1){czesc[l] = 3; pol[l]= c; l++; }
            if(ktory[3]==1){czesc[l] = 4; pol[l]= d; l++; }
            if(ktory[4]==1){czesc[l] = 5; pol[l]= f; l++; }
            if(ktory[5]==1){czesc[l] = 6; pol[l]= g; l++; }
         }
          if(e.getKeyCode()== KeyEvent.VK_D ||e.getKeyCode()== KeyEvent.VK_W ||e.getKeyCode()== KeyEvent.VK_S ||e.getKeyCode()== KeyEvent.VK_A  ){
            zegar = new java.util.Timer();
            zegar.scheduleAtFixedRate(new Zadanie(),0,20);}
       }
         if(e.getKeyCode()== KeyEvent.VK_O && !zatrzask && bylo_nag){
             nagrywanie = false;
             odtwarzanie = true;
             zatrzask = true;
             zegar = new java.util.Timer();
            zegar.scheduleAtFixedRate(new Zadanie(),0,20);
         }
         
         

    }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_N) zatrzask = false;
        if(e.getKeyCode()== KeyEvent.VK_O) zatrzask = false;
    }
    @Override
     public void keyTyped(KeyEvent e){
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Frame Maffi = new MainFrame(new Maffek(),1280,960);
        Maffi.setTitle("Pachwina Project");
        
        Maffi.setVisible(true);
        
    }
    
}
