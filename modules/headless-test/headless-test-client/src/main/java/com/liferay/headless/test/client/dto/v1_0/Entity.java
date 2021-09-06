package com.liferay.headless.test.client.dto.v1_0;

import com.liferay.headless.test.client.function.UnsafeSupplier;
import com.liferay.headless.test.client.serdes.v1_0.EntitySerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author me
 * @generated
 */
@Generated("")
public class Entity implements Cloneable, Serializable {

	public static Entity toDTO(String json) {
		return EntitySerDes.toDTO(json);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Integer, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	@Override
	public Entity clone() throws CloneNotSupportedException {
		return (Entity)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity)object;

		return Objects.equals(toString(), entity.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return EntitySerDes.toJSON(this);
	}

}