import math.Matrix4Factories;
import math.Vector3;
import models.Line3D;
import screen.ScreenConverter;
import screen.ScreenPoint;
import third.Camera;
import third.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    private ScreenConverter sc;
    private Scene scene;
    private Camera camera;

    Point  last;
    public DrawPanel() {
        sc=new ScreenConverter(800,600,-1,1,2,2);
        scene = new Scene();
        camera=new Camera();
        scene.getModels().add(new Line3D(new Vector3(0,0,0),new Vector3(1,0,0)));
        scene.getModels().add(new Line3D(new Vector3(0,0,0),new Vector3(0,1,0)));
        scene.getModels().add(new Line3D(new Vector3(0,0,0),new Vector3(0,0,1)));

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point cur = e.getPoint();
                if (last != null){
                    int dx = cur.x-last.x;
                    int dy = cur.y-last.y;
                    double da = dx*Math.PI/180;
                    double db = dy*Math.PI/180;
                    camera.modifyRotate(
                            Matrix4Factories.rotationXYZ(da, Matrix4Factories.Axis.Y)
                                    .mul(Matrix4Factories.rotationXYZ(db, Matrix4Factories.Axis.X))
                    );
                }
                last=cur;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                last = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        sc.setSize(getWidth(),getHeight());
        BufferedImage bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        //
        scene.draw(gr,sc,camera);
        //
        g.drawImage(bi,0,0,null);
        gr.dispose();
    }
}
