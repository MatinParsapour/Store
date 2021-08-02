package entity;

public interface BaseEntity<T> {

    T getName();

    void setName(T name);
}
