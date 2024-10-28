package org.example;

import org.lwjgl.opengl.GL11;

public class Pyramid implements Drawable {
    @Override
    public void draw() {
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_LINE_STRIP);

        // Основание пятиугольника
        for (int i = 0; i < 5; i++) {
            double angle = 2 * Math.PI * i / 5;
            GL11.glVertex3d(Math.cos(angle) - 0.5, 0, Math.sin(angle));
        }
        GL11.glVertex3d(Math.cos(0) - 0.5, 0, Math.sin(0)); // Замыкаем основание
        GL11.glEnd();

        // Грани пирамиды
        for (int i = 0; i < 5; i++) {
            GL11.glBegin(GL11.GL_LINES);
            double angle1 = 2 * Math.PI * i / 5;

            // Соединяем вершину с основанием
            GL11.glVertex3d(-0.5, 2, 0); // Вершина
            GL11.glVertex3d(Math.cos(angle1) - 0.5, 0, Math.sin(angle1)); // Угол основания
            GL11.glEnd();
        }
    }

}
