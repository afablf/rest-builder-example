package com.liferay.headless.test.internal.graphql.query.v1_0;

import com.liferay.headless.test.dto.v1_0.Entity;
import com.liferay.headless.test.resource.v1_0.EntityResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author me
 * @generated
 */
@Generated("")
public class Query {

	public static void setEntityResourceComponentServiceObjects(
		ComponentServiceObjects<EntityResource>
			entityResourceComponentServiceObjects) {

		_entityResourceComponentServiceObjects =
			entityResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {entities{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EntityPage entities() throws Exception {
		return _applyComponentServiceObjects(
			_entityResourceComponentServiceObjects,
			this::_populateResourceContext,
			entityResource -> new EntityPage(entityResource.getEntitiesPage()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {entity(entityId: ___){name, id}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Entity entity(@GraphQLName("entityId") Integer entityId)
		throws Exception {

		return _applyComponentServiceObjects(
			_entityResourceComponentServiceObjects,
			this::_populateResourceContext,
			entityResource -> entityResource.getEntity(entityId));
	}

	@GraphQLName("EntityPage")
	public class EntityPage {

		public EntityPage(Page entityPage) {
			actions = entityPage.getActions();

			items = entityPage.getItems();
			lastPage = entityPage.getLastPage();
			page = entityPage.getPage();
			pageSize = entityPage.getPageSize();
			totalCount = entityPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Entity> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(EntityResource entityResource)
		throws Exception {

		entityResource.setContextAcceptLanguage(_acceptLanguage);
		entityResource.setContextCompany(_company);
		entityResource.setContextHttpServletRequest(_httpServletRequest);
		entityResource.setContextHttpServletResponse(_httpServletResponse);
		entityResource.setContextUriInfo(_uriInfo);
		entityResource.setContextUser(_user);
		entityResource.setGroupLocalService(_groupLocalService);
		entityResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<EntityResource>
		_entityResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}