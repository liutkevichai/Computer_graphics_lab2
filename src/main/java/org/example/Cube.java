package org.example;

import org.lwjgl.opengl.GL11;

public class Cube implements Drawable {
    // Массив вершин
    float[][] vertices = {
            // Правая грань
            {2.5f, 0.0f, -0.5f},
            {2.5f, 0.0f, 0.5f},
            {2.5f, 1.0f, 0.5f},
            {2.5f, 1.0f, -0.5f},

            // Левая грань
            {1.5f, 0.0f, -0.5f},
            {1.5f, 0.0f, 0.5f},
            {1.5f, 1.0f, 0.5f},
            {1.5f, 1.0f, -0.5f},

            // Нижняя грань
            {1.5f, 0.0f, 0.5f},
            {1.5f, 0.0f, -0.5f},
            {2.5f, 0.0f, -0.5f},
            {2.5f, 0.0f, 0.5f},

            // Верхняя грань
            {1.5f, 1.0f, -0.5f},
            {2.5f, 1.0f, -0.5f},
            {2.5f, 1.0f, 0.5f},
            {1.5f, 1.0f, 0.5f},

            // Задняя грань
            {1.5f, 1.0f, -0.5f},
            {2.5f, 1.0f, -0.5f},
            {2.5f, 0.0f, -0.5f},
            {1.5f, 0.0f, -0.5f},

            // Передняя грань
            {1.5f, 0.0f, 0.5f},
            {1.5f, 1.0f, 0.5f},
            {2.5f, 1.0f, 0.5f},
            {2.5f, 0.0f, 0.5f},
    };

    @Override
    public void draw() {
        GL11.glColor3f(1.0f, 1.0f, 0.0f); // Устанавливаем цвет куба - желтый
        GL11.glBegin(GL11.GL_QUADS);
        for (float[] vertex : vertices) {
            GL11.glVertex3f(vertex[0], vertex[1], vertex[2]);
        }
        GL11.glEnd();
    }
}
