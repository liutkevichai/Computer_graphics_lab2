package org.example;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    private long window;
    private double[] mouseX;
    private double[] mouseY;
    private float rotationX = 0.0f; // Угол вращения по X
    private float rotationY = 45.0f;   // Угол вращения по Y
    private boolean mousePressed = false;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        // Инициализация GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Не удалось инициализировать GLFW");
        }

        // Настройка окна
        window = glfwCreateWindow(800, 600, "Лабораторная работа 2", 0, 0);
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();

        // Настройка OpenGL
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

        // Настройка проекции
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(-3, 3, -3, 3, 0.1, 10); // Установка ортографической проекции

        // Возвращаемся к матрице модели
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // Инициализация массивов для хранения позиции мыши
        mouseX = new double[1];
        mouseY = new double[1];

        // Установка коллбэка для движения мыши
        glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                if (mousePressed) {
                    float deltaX = (float) (xpos - mouseX[0]);
                    float deltaY = (float) (ypos - mouseY[0]);
                    rotationX += deltaY * 0.5f;
                    rotationY += deltaX * 0.5f;
                }
                mouseX[0] = xpos;
                mouseY[0] = ypos;
            }
        });

        // Установка коллбэка для нажатия кнопки мыши
        glfwSetMouseButtonCallback(window, (windowHandle, button, action, mods) -> {
            if (button == GLFW_MOUSE_BUTTON_LEFT) {
                mousePressed = action == GLFW_PRESS; // Запоминаем, нажата ли кнопка
                if (mousePressed) {
                    glfwGetCursorPos(window, mouseX, mouseY); // Получаем начальную позицию мыши
                }
            }
        });
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Установка матрицы вида
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0f, 0.0f, -4.0f); // Перемещаем камеру назад

            // Обновляем угол и скорость вращения
            float rotationSpeed = 0.3f;
            rotationX += rotationSpeed;

            GL11.glRotatef(rotationY, 1, 0, 0);
            GL11.glRotatef(rotationX, 0, 1, 0);

            new Pyramid().draw();
            new Cube().draw();
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void cleanup() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
