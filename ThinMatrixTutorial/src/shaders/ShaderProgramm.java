package shaders;

import java.io.BufferedReader;
import java.io.FileReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class ShaderProgramm {
	
	private int programmID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	public ShaderProgramm(String vertexFile, String fragmentFile){
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programmID = GL20.glCreateProgram();
		GL20.glAttachShader(programmID, vertexShaderID);
		GL20.glAttachShader(programmID, fragmentShaderID);
		GL20.glLinkProgram(programmID);
		GL20.glValidateProgram(programmID);
	}
	
	public void start(){
		GL20.glUseProgram(programmID);
	}
	
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	public void cleanUp(){
		stop();
		GL20.glDetachShader(programmID, fragmentShaderID);
		GL20.glDetachShader(programmID, vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteProgram(programmID);
	}
	
	protected abstract void bindAttributes();
	
	protected void bindAttribute(int atrribut, String variableName){
		GL20.glBindAttribLocation(programmID, atrribut, variableName);
	}
	
	private static int loadShader(String file, int type){
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!= null){
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} catch (Exception e) {
			System.err.println("ShaderProgramm: Could not find File: <" + file + ">");
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("ShaderProgramm: Could not compile shader: <" + file + ">");
		}
		return shaderID;
	}
	
}
