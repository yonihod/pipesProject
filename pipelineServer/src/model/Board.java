package model;

public interface Board<T> {
	T getXY(Integer x, Integer y);
	Integer getHieght();
	Integer getWidth();

}
