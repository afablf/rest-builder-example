package com.liferay.headless.test.client.serdes.v1_0;

import com.liferay.headless.test.client.dto.v1_0.Entity;
import com.liferay.headless.test.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author me
 * @generated
 */
@Generated("")
public class EntitySerDes {

	public static Entity toDTO(String json) {
		EntityJSONParser entityJSONParser = new EntityJSONParser();

		return entityJSONParser.parseToDTO(json);
	}

	public static Entity[] toDTOs(String json) {
		EntityJSONParser entityJSONParser = new EntityJSONParser();

		return entityJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Entity entity) {
		if (entity == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (entity.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(entity.getId());
		}

		if (entity.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(entity.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EntityJSONParser entityJSONParser = new EntityJSONParser();

		return entityJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Entity entity) {
		if (entity == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (entity.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(entity.getId()));
		}

		if (entity.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(entity.getName()));
		}

		return map;
	}

	public static class EntityJSONParser extends BaseJSONParser<Entity> {

		@Override
		protected Entity createDTO() {
			return new Entity();
		}

		@Override
		protected Entity[] createDTOArray(int size) {
			return new Entity[size];
		}

		@Override
		protected void setField(
			Entity entity, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					entity.setId(Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					entity.setName((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}