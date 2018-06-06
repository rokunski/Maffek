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
import java.util.TimerTask;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Vector;
import javax.media.j3d.*;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author ROkuński
 */
public class Maffek extends Applet implements KeyListener,ActionListener{
    
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
   
   BranchGroup wezel_scena = new BranchGroup();
   BranchGroup wezel_2 = new BranchGroup();
   BranchGroup wezel_3 = new BranchGroup();
   BranchGroup wezel_4 = new BranchGroup();
   BranchGroup wezel_5 = new BranchGroup();
   BranchGroup wezel_6 = new BranchGroup();
   BranchGroup wezel_7 = new BranchGroup();
   BranchGroup wezel_8 = new BranchGroup();
    
   float a=0;
   float b=0;
   float c=0;
   float d=0;
   float f=0;
   float g=0;
    
    int[] ktory = {0,0,0,0,0,0};
    int kierunek = 0;  //-1,0,1
    boolean nagrywanie = false, odtwarzanie = false, zatrzask = false ;
    boolean bylo_nag = false;
    boolean kolizja= false;
    boolean chwyc = false;
    Vector<Integer> czesc = new Vector<>();
    Vector<Float> pol = new Vector<>();
    int l=0, k=0;
    
    Klawisze panel = new Klawisze();
      
     private java.util.Timer       zegar = new java.util.Timer();
     
     final private boolean spin = false;
     
     
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
    Vector3f pos_kamery = new Vector3f(0f,0f,5f);
    Transform3D kamera = new Transform3D();
    
  /*  class Kolizja extends Behavior {
        TransformGroup targetTG;
        Shape3D shape;
        BranchGroup localBGCopy;
        boolean isWhileCollision;
        String nazwa;
        
       public Kolizja(TransformGroup targetTG, Shape3D shape, BranchGroup localBGCopy, String nazwa){
            this.targetTG = targetTG;
            this.shape = shape;
            this.localBGCopy = localBGCopy;
            setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
            isWhileCollision = false;
            this.nazwa = nazwa;
        }
 
        @Override
        public void initialize() {
            wakeupOn(new WakeupOnCollisionEntry(shape));
        }
       
         @Override
         public void processStimulus(Enumeration enmrtn) {   
            if (isWhileCollision){
                wakeupOn(new WakeupOnCollisionExit(shape));
                kolizja = false;
                isWhileCollision = false;
            }else{
                wakeupOn(new WakeupOnCollisionEntry(shape));
                System.out.println("Kolizja z "+ nazwa);  
                kolizja = true;
                isWhileCollision = true;
            }
    }
}*/


    
    
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
                switch(czesc.get(k)){
                     case 1:
                         obracacz.setMaximumAngle(pol.get(k)); obracacz.setMinimumAngle(pol.get(k));break;
                     case 2:
                         obracacz2.setMaximumAngle(pol.get(k)); obracacz2.setMinimumAngle(pol.get(k));break;
                    case 3:
                         obracacz3.setMaximumAngle(pol.get(k)); obracacz3.setMinimumAngle(pol.get(k));break;
                     case 4:
                         obracacz4.setMaximumAngle(pol.get(k)); obracacz4.setMinimumAngle(pol.get(k));break;
                     case 5:
                         obracacz5.setMaximumAngle(pol.get(k)); obracacz5.setMinimumAngle(pol.get(k));break;
                     case 6:
                         obracacz61.setMaximumAngle(pol.get(k)); obracacz61.setMinimumAngle(pol.get(k));
                         obracacz62.setMaximumAngle(-pol.get(k)); obracacz62.setMinimumAngle(-pol.get(k));break;
                 }
                k++;
                if(k==l){
                     odtwarzanie = false;
                     czesc.removeAllElements();
                     pol.removeAllElements();
                     k=0;
                     l=0;
                     bylo_nag = false;
                     zegar.cancel();
                     OEnd();
                }
            }  
          }
     }
    
 
    
    public BranchGroup utworzScene(){

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
      
      Sphere kuleczka = new Sphere(0.05f);
      Appearance wygladKuleczki = new Appearance();
      wygladKuleczki.setColoringAttributes(new ColoringAttributes(new Color3f(0.0f, 0.3f, 0.5f),1));
      Material mat = new Material();
      mat.setAmbientColor(new Color3f(0.3f, 0.2f, 1.0f));
      wygladKuleczki.setMaterial(mat);
      kuleczka.setAppearance(wygladKuleczki);
      Transform3D pos1 = new Transform3D();
      pos1.setTranslation(new Vector3f(0.3f, -0.6f, 0.0f));
      TransformGroup kulka = new TransformGroup();
      kulka.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      kulka.setTransform(pos1);
      kulka.addChild(kuleczka);
      wezel_8.addChild(kulka);
      wezel_scena.addChild(wezel_8);
      
      
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
        
        panel.setBackground(Color.DARK_GRAY);  
        panel.A.addActionListener(this);
        panel.C.addActionListener(this);
        panel.D.addActionListener(this);
        panel.N.addActionListener(this);
        panel.O.addActionListener(this);
        panel.R.addActionListener(this);
        panel.S.addActionListener(this);
        panel.W.addActionListener(this);
        panel.cztery.addActionListener(this);
        panel.dwa.addActionListener(this);
        panel.jeden.addActionListener(this);
        panel.piec.addActionListener(this);
        panel.trzy.addActionListener(this);
        panel.szesc.addActionListener(this);
        add("North",panel);
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
    
       
     private void jedenPressed(){
         panel.jeden.setBackground(Color.BLUE);
         panel.dwa.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.szesc.setBackground(new Color(0,153,0));
         
         panel.A.setBackground(new Color(0,153,0));
         panel.D.setBackground(new Color(0,153,0));
         panel.W.setBackground(Color.RED);
         panel.S.setBackground(Color.RED);
         
         panel.A.setEnabled(true);
         panel.D.setEnabled(true);
         panel.S.setEnabled(false);
         panel.W.setEnabled(false);
     }
     
     private void dwaPressed(){
         panel.dwa.setBackground(Color.BLUE);
         panel.jeden.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.szesc.setBackground(new Color(0,153,0));
         
         panel.W.setBackground(new Color(0,153,0));
         panel.S.setBackground(new Color(0,153,0));
         panel.A.setBackground(Color.RED);
         panel.D.setBackground(Color.RED);
         
         panel.W.setEnabled(true);
         panel.S.setEnabled(true);
         panel.A.setEnabled(false);
         panel.D.setEnabled(false);
     }
     
         private void trzyPressed(){
         panel.trzy.setBackground(Color.BLUE);
         panel.jeden.setBackground(new Color(0,153,0));
         panel.dwa.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.szesc.setBackground(new Color(0,153,0));
         
         panel.W.setBackground(new Color(0,153,0));
         panel.S.setBackground(new Color(0,153,0));
         panel.A.setBackground(Color.RED);
         panel.D.setBackground(Color.RED);
         
         panel.W.setEnabled(true);
         panel.S.setEnabled(true);
         panel.A.setEnabled(false);
         panel.D.setEnabled(false);
     }
                
     private void czteryPressed(){
         panel.cztery.setBackground(Color.BLUE);
         panel.dwa.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.jeden.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.szesc.setBackground(new Color(0,153,0));
         
         panel.A.setBackground(new Color(0,153,0));
         panel.D.setBackground(new Color(0,153,0));
         panel.W.setBackground(Color.RED);
         panel.S.setBackground(Color.RED);
         
         panel.A.setEnabled(true);
         panel.D.setEnabled(true);
         panel.S.setEnabled(false);
         panel.W.setEnabled(false);
     }
     
         private void piecPressed(){
         panel.piec.setBackground(Color.BLUE);
         panel.jeden.setBackground(new Color(0,153,0));
         panel.dwa.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.szesc.setBackground(new Color(0,153,0));
         
         panel.W.setBackground(new Color(0,153,0));
         panel.S.setBackground(new Color(0,153,0));
         panel.A.setBackground(Color.RED);
         panel.D.setBackground(Color.RED);
         
         panel.W.setEnabled(true);
         panel.S.setEnabled(true);
         panel.A.setEnabled(false);
         panel.D.setEnabled(false);
     }
         
     private void szescPressed(){
         panel.szesc.setBackground(Color.BLUE);
         panel.dwa.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.jeden.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         
         panel.A.setBackground(new Color(0,153,0));
         panel.D.setBackground(new Color(0,153,0));
         panel.W.setBackground(Color.RED);
         panel.S.setBackground(Color.RED);
         
         panel.A.setEnabled(true);
         panel.D.setEnabled(true);
         panel.S.setEnabled(false);
         panel.W.setEnabled(false);
     }
     
     public void CPressed(){
         panel.jeden.setBackground(new Color(0,153,0));
         panel.dwa.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.jeden.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         panel.N.setBackground(new Color(0,153,0));
         panel.O.setEnabled(false);
         panel.O.setBackground(Color.RED);
         
         panel.W.setBackground(Color.RED);
         panel.S.setBackground(Color.RED);
         panel.A.setBackground(Color.RED);
         panel.D.setBackground(Color.RED);
         
         panel.S.setEnabled(false);
         panel.W.setEnabled(false);
         panel.A.setEnabled(false);
         panel.D.setEnabled(false);
     
     }
     
     public void NPressed(){
         panel.N.setBackground(Color.BLUE);
         panel.O.setEnabled(true);
         panel.O.setBackground(new Color(0,153,0));
     }
     
     public void OPressed(){
         panel.jeden.setBackground(Color.RED);
         panel.dwa.setBackground(Color.RED);
         panel.trzy.setBackground(Color.RED);
         panel.szesc.setBackground(Color.RED);
         panel.piec.setBackground(Color.RED);
         panel.cztery.setBackground(Color.RED);
         panel.N.setBackground(Color.RED);
         panel.W.setBackground(Color.RED);
         panel.S.setBackground(Color.RED);
         panel.A.setBackground(Color.RED);
         panel.D.setBackground(Color.RED);
         panel.C.setBackground(Color.RED);
         panel.R.setBackground(Color.RED);
         panel.O.setBackground(Color.BLUE);
         
         panel.S.setEnabled(false);
         panel.W.setEnabled(false);
         panel.A.setEnabled(false);
         panel.D.setEnabled(false);
         panel.jeden.setEnabled(false);
         panel.dwa.setEnabled(false);
         panel.trzy.setEnabled(false);
         panel.cztery.setEnabled(false);
         panel.piec.setEnabled(false);
         panel.szesc.setEnabled(false);
         panel.R.setEnabled(false);
         panel.C.setEnabled(false);
         panel.N.setEnabled(false);
         panel.O.setEnabled(false);
     }
     
     public void OEnd(){
         panel.jeden.setBackground(new Color(0,153,0));
         panel.dwa.setBackground(new Color(0,153,0));
         panel.trzy.setBackground(new Color(0,153,0));
         panel.szesc.setBackground(new Color(0,153,0));
         panel.piec.setBackground(new Color(0,153,0));
         panel.cztery.setBackground(new Color(0,153,0));
         panel.N.setBackground(new Color(0,153,0));
         panel.C.setBackground(new Color(0,153,0));
         panel.R.setBackground(new Color(0,153,0));
         panel.O.setBackground(Color.RED);
         
         panel.jeden.setEnabled(true);
         panel.dwa.setEnabled(true);
         panel.trzy.setEnabled(true);
         panel.cztery.setEnabled(true);
         panel.piec.setEnabled(true);
         panel.szesc.setEnabled(true);
         panel.R.setEnabled(true);
         panel.C.setEnabled(true);
         panel.N.setEnabled(true);
     }
    @Override
    public void keyPressed(KeyEvent e){
       if(!odtwarzanie){ 
        if(e.getKeyCode()== KeyEvent.VK_1){
            ktory[0]=1;
            for(int i=1;i<6;i++)
                ktory[i]=0;
            jedenPressed();
        }
        if(ktory[0]==1 && e.getKeyCode()== KeyEvent.VK_A ){
            a-=0.01f;
            if(nagrywanie){czesc.add(1); pol.add(a); l++;}
         }else if(ktory[0]==1 && e.getKeyCode()== KeyEvent.VK_D ){
             a+=0.01f; 
             if(nagrywanie){czesc.add(1); pol.add(a); l++;}
         }
         
         if(e.getKeyCode()== KeyEvent.VK_2){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[1]=1;
            dwaPressed();
        }
        if(ktory[1]==1 && e.getKeyCode()== KeyEvent.VK_S ){
            b-=0.01f;
            if(b<-(float)Math.PI/5)b=-(float)Math.PI/5;
            if(nagrywanie){czesc.add(2); pol.add(b); l++;}
         }else  if(ktory[1]==1 && e.getKeyCode()== KeyEvent.VK_W ){
             b+=0.01f;
             if(b>(float)Math.PI/5)b=(float)Math.PI/5;
             if(nagrywanie){czesc.add(2); pol.add(b); l++;}
         }
         
        if(e.getKeyCode()== KeyEvent.VK_3){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[2]=1;
            trzyPressed();
        }
        if(ktory[2]==1 && e.getKeyCode()== KeyEvent.VK_S ){
            c-=0.01f;
            if(c<-(float)Math.PI/4)c=-(float)Math.PI/4;
            if(nagrywanie){czesc.add(3); pol.add(c); l++;}
         }else   if(ktory[2]==1 && e.getKeyCode()== KeyEvent.VK_W ){
             c+=0.01f;
             if(c>3*(float)Math.PI/4)c=3*(float)Math.PI/4;
             if(nagrywanie){czesc.add(3); pol.add(c); l++;}
         }
         
         if(e.getKeyCode()== KeyEvent.VK_4){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[3]=1;
            czteryPressed();
        }
        if(ktory[3]==1 && e.getKeyCode()== KeyEvent.VK_A ){
            d-=0.01f;
            if(nagrywanie){czesc.add(4); pol.add(d); l++;}
         }else  if(ktory[3]==1 && e.getKeyCode()== KeyEvent.VK_D ){
             d+=0.01f;
             if(nagrywanie){czesc.add(4); pol.add(d); l++;}
         }
         
       if(e.getKeyCode()== KeyEvent.VK_5){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[4]=1;
            piecPressed();
        }
        if(ktory[4]==1 && e.getKeyCode()== KeyEvent.VK_S ){
            f-=0.01f;
            if(f<-(float)Math.PI/6)f=-(float)Math.PI/6;
            if(nagrywanie){czesc.add(5); pol.add(f); l++;}
         }else if(ktory[4]==1 && e.getKeyCode()== KeyEvent.VK_W ){
             f+=0.01f;
             if(f>7*(float)Math.PI/6)f=7*(float)Math.PI/6;
             if(nagrywanie){czesc.add(5); pol.add(f); l++;}
         }
         
         if(e.getKeyCode()== KeyEvent.VK_6){
            for(int i=0;i<5;i++)
                ktory[i]=0;
            ktory[5]=1;
            szescPressed();
        }
        if(ktory[5]==1 && e.getKeyCode()== KeyEvent.VK_A ){
            g-=0.01f;
            if(g<-(float)Math.PI/18)g=-(float)Math.PI/18;
            if(nagrywanie){czesc.add(6); pol.add(g); l++;}
         }else if(ktory[5]==1 && e.getKeyCode()== KeyEvent.VK_D ){
             g+=0.01f;
             if(g>(float)Math.PI/6)g=(float)Math.PI/6;
             if(nagrywanie){czesc.add(6); pol.add(g); l++;}
         }

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
             nagrywanie = false;
             bylo_nag = false;
             czesc.removeAllElements();
             pol.removeAllElements();
             CPressed();
         }
         
         if(e.getKeyCode()== KeyEvent.VK_N && !zatrzask && !nagrywanie){
             nagrywanie=true;
             czesc.add(1);pol.add(a);
             czesc.add(2);pol.add(b);
             czesc.add(3);pol.add(c);
             czesc.add(4);pol.add(d);
             czesc.add(5);pol.add(f);
             czesc.add(6);pol.add(g);
             l=6;
             bylo_nag = true;
             zatrzask = true;
             NPressed();
         }
          if(e.getKeyCode()== KeyEvent.VK_D ||e.getKeyCode()== KeyEvent.VK_W ||e.getKeyCode()== KeyEvent.VK_S ||e.getKeyCode()== KeyEvent.VK_A  ){
            zegar = new java.util.Timer();
            zegar.scheduleAtFixedRate(new Zadanie(),0,20);
          }
          
          if(e.getKeyCode()== KeyEvent.VK_O && !zatrzask && bylo_nag){
             nagrywanie = false;
             odtwarzanie = true;
             zatrzask = true;
             OPressed();
             zegar = new java.util.Timer();
             zegar.scheduleAtFixedRate(new Zadanie(),0,20);
         }
          
         if(e.getKeyCode()== KeyEvent.VK_ENTER && !zatrzask){
             chwyc=!chwyc;
             zatrzask = true;
           /*   if(chwyc) {
                 wezel_scena.removeChild(wezel_scena);
                 wezel_7.addChild(wezel_8);
                }
             else 
                  if(wezel_7.numChildren()==3) {
                     wezel_7.removeChild(wezel_8);
                     wezel_scena.addChild(wezel_8);
             }*/
         } 
       }
    }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_N) zatrzask = false;
        if(e.getKeyCode()== KeyEvent.VK_O) zatrzask = false;
        if(e.getKeyCode()== KeyEvent.VK_ENTER) zatrzask = false;
    }
    @Override
     public void keyTyped(KeyEvent e){
    }

     
     @Override
     public void actionPerformed(ActionEvent e){
     if(!odtwarzanie){ 
        if(e.getSource()== panel.jeden){
            ktory[0]=1;
            for(int i=1;i<6;i++)
                ktory[i]=0;
            jedenPressed();
        }
        if(ktory[0]==1 && e.getSource()== panel.A ){
            a-=0.01f;
            if(nagrywanie){czesc.add(1); pol.add(a); l++;}
         }else if(ktory[0]==1 && e.getSource()== panel.D){
             a+=0.01f; 
             if(nagrywanie){czesc.add(1); pol.add(a); l++;}
         }
         
         if(e.getSource()== panel.dwa){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[1]=1;
            dwaPressed();
        }
        if(ktory[1]==1 && e.getSource()== panel.S ){
            b-=0.01f;
            if(b<-(float)Math.PI/5)b=-(float)Math.PI/5;
            if(nagrywanie){czesc.add(2); pol.add(b); l++;}
         }else  if(ktory[1]==1 && e.getSource()== panel.W ){
             b+=0.01f;
             if(b>(float)Math.PI/5)b=(float)Math.PI/5;
             if(nagrywanie){czesc.add(2); pol.add(b); l++;}
         }
         
        if(e.getSource()==panel.trzy){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[2]=1;
            trzyPressed();
        }
        if(ktory[2]==1 && e.getSource()== panel.S ){
            c-=0.01f;
            if(c<-(float)Math.PI/4)c=-(float)Math.PI/4;
            if(nagrywanie){czesc.add(3); pol.add(c); l++;}
         }else   if(ktory[2]==1 && e.getSource()== panel.W ){
             c+=0.01f;
             if(c>3*(float)Math.PI/4)c=3*(float)Math.PI/4;
             if(nagrywanie){czesc.add(3); pol.add(c); l++;}
         }
         
         if(e.getSource()== panel.cztery){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[3]=1;
            czteryPressed();
        }
        if(ktory[3]==1 && e.getSource()== panel.A ){
            d-=0.01f;
            if(nagrywanie){czesc.add(4); pol.add(d); l++;}
         }else  if(ktory[3]==1 && e.getSource()== panel.D ){
             d+=0.01f;
             if(nagrywanie){czesc.add(4); pol.add(d); l++;}
         }
         
       if(e.getSource()== panel.piec){
            for(int i=0;i<6;i++)
                ktory[i]=0;
            ktory[4]=1;
            piecPressed();
        }
        if(ktory[4]==1 && e.getSource()== panel.S ){
            f-=0.01f;
            if(f<-(float)Math.PI/6)f=-(float)Math.PI/6;
            if(nagrywanie){czesc.add(5); pol.add(f); l++;}
         }else if(ktory[4]==1 && e.getSource()== panel.W ){
             f+=0.01f;
             if(f>7*(float)Math.PI/6)f=7*(float)Math.PI/6;
             if(nagrywanie){czesc.add(5); pol.add(f); l++;}
         }
         
         if(e.getSource()== panel.szesc){
            for(int i=0;i<5;i++)
                ktory[i]=0;
            ktory[5]=1;
            szescPressed();
        }
        if(ktory[5]==1 && e.getSource()== panel.A ){
            g-=0.01f;
            if(g<-(float)Math.PI/18)g=-(float)Math.PI/18;
            if(nagrywanie){czesc.add(6); pol.add(g); l++;}
         }else if(ktory[5]==1 && e.getSource()== panel.D ){
             g+=0.01f;
             if(g>(float)Math.PI/6)g=(float)Math.PI/6;
             if(nagrywanie){czesc.add(6); pol.add(g); l++;}
         }

         if(e.getSource()==panel.R){
              kamera.set(pos_kamery);
            simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(kamera);
         }
         
         if(e.getSource() == panel.C){
             for(int i=0;i<6;i++)
                ktory[i]=0;
             a=0;b=0;c=0;
             d=0;f=0;g=0;
             zegar = new java.util.Timer();
             zegar.scheduleAtFixedRate(new Zadanie(),0,20);
             nagrywanie = false;
             bylo_nag = false;
             czesc.removeAllElements();
             pol.removeAllElements();
             CPressed();
         }
         
         if(e.getSource()== panel.N&& !nagrywanie){
             nagrywanie=true;
             czesc.add(1);pol.add(a);
             czesc.add(2);pol.add(b);
             czesc.add(3);pol.add(c);
             czesc.add(4);pol.add(d);
             czesc.add(5);pol.add(f);
             czesc.add(6);pol.add(g);
             l=6;
             bylo_nag = true;
             NPressed();
         }
          if(e.getSource()== panel.D ||e.getSource()== panel.W ||e.getSource()== panel.S ||e.getSource()== panel.A  ){
            zegar = new java.util.Timer();
            zegar.scheduleAtFixedRate(new Zadanie(),0,20);
          }
          
          if(e.getSource()== panel.O && bylo_nag){
             nagrywanie = false;
             odtwarzanie = true;
             OPressed();
             zegar = new java.util.Timer();
             zegar.scheduleAtFixedRate(new Zadanie(),0,20);
         }
          
       /*  if(e.getSource()== KeyEvent.VK_ENTER && !zatrzask){
             chwyc=!chwyc;
             zatrzask = true;*/
           /*   if(chwyc) {
                 wezel_scena.removeChild(wezel_scena);
                 wezel_7.addChild(wezel_8);
                }
             else 
                  if(wezel_7.numChildren()==3) {
                     wezel_7.removeChild(wezel_8);
                     wezel_scena.addChild(wezel_8);
             }*/
       //  } 
       }
     
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
