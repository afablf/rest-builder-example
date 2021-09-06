package com.liferay.headless.test.internal.resource.v1_0;

import com.liferay.headless.test.dto.v1_0.Entity;
import com.liferay.headless.test.resource.v1_0.EntityResource;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author me
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/entity.properties",
	scope = ServiceScope.PROTOTYPE, service = EntityResource.class
)
public class EntityResourceImpl extends BaseEntityResourceImpl {
	
	Map<Integer, Entity> entities = new HashMap<>();

	@Override
	public Entity getEntity(Integer entityId) throws Exception {
	return entities.get(entityId);
	}

	@Override
	public Page<Entity> getEntitiesPage() throws Exception {
	return Page.of(entities.values());
	}

	@Override
	public void deleteEntity(Integer entityId) throws Exception {
	entities.remove(entityId);
	}

	@Override
	public Entity postEntity(Entity entity) throws Exception {
	entities.put(entity.getId(), entity);
	return entity;
	}

	@Override
	public Entity putEntity(Integer entityId, Entity entity) throws Exception {
	entities.put(entity.getId(), entity);
	return entity;
	}


}