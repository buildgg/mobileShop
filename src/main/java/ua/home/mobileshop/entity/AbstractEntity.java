package ua.home.mobileshop.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vov on 11.01.2017.
 */
public abstract class AbstractEntity <T> implements Serializable{
    T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity<?> that = (AbstractEntity<?>) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("%s [id = %s]", getClass().getSimpleName(), id);
    }
}
