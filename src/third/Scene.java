package third;

import math.Vector3;
import screen.ScreenConverter;
import screen.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    private List<IModel> models = new ArrayList<>();
    public List<IModel> getModels() {
        return models;
    }

    public void draw(Graphics2D gr, ScreenConverter sc, ICamera camera) {
        List<Polyline3D> toDraw = new LinkedList<>();
        for (IModel m : models){
            for (Polyline3D p : m.getLines()){
                List<Vector3> poiints= new LinkedList<>();
                for (Vector3 v : p.getPoints()){
                    poiints.add(camera.w2s(v));
                }
                toDraw.add(new Polyline3D(poiints,p.isClosed()));
            }
        }
        gr.setColor(Color.white);
        gr.fillRect(0,0,sc.getWidth(),sc.getHeight());
        gr.setColor(Color.BLACK);
        for (Polyline3D p : toDraw){
            drawPolyline(gr,sc,p);
        }
    }
    private static void drawPolyline(Graphics2D g, ScreenConverter sc,Polyline3D p){
        List<ScreenPoint> list = new LinkedList<>();
        for ( Vector3 v : p.getPoints()){
            list.add(sc.r2s(v));
        }
        if (p.isClosed()){
            list.add(list.get(0));
        }
        for (int i=0;i<list.size()-1;i++){
            ScreenPoint a = list.get(i);
            ScreenPoint b = list.get(i+1);
            g.drawLine(a.getX(),a.getY(),b.getX(), b.getY());
        }
    }
}
