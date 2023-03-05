package umnik.com.engine.render;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;
import static org.lwjgl.opengl.GL46C.glClearColor;

public class Render {
    int arraysID;
    private final List<FloatBuffer> triangle = new ArrayList<>();

    public static FloatBuffer convertToBuffer(float[] data){
        return (FloatBuffer) MemoryUtil.memAllocFloat(data.length).put(data).flip();
    }
    public void clear(float red,float green, float blue, float alpha) {
        glClearColor(red,green,blue,alpha);
        //glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
    public void addTriangle(FloatBuffer buffer) {
        arraysID = glGenVertexArrays();
        int buffersID = glGenBuffers();
        triangle.add(buffer);
        glBindVertexArray(arraysID);
        glBindBuffer(GL_ARRAY_BUFFER,buffersID);
        glBufferData(GL_ARRAY_BUFFER,triangle.get(triangle.size() - 1),GL_STATIC_DRAW);
        glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);
        MemoryUtil.memFree(buffer);
        glBindBuffer(GL_ARRAY_BUFFER,buffersID);
        glBindVertexArray(arraysID);
    }
    public void update(){
        glClear(GL_COLOR_BUFFER_BIT);
        glBindVertexArray(arraysID);
        glEnableVertexAttribArray(0);
        glDrawArrays(GL_TRIANGLES,0,6);
        glDisableVertexAttribArray(0);
        glBindVertexArray(arraysID);
    }
}
