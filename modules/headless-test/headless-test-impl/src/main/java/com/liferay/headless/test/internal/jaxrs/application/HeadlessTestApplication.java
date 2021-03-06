package com.liferay.headless.test.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author me
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/headless-test",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=HeadlessTest"
	},
	service = Application.class
)
@Generated("")
public class HeadlessTestApplication extends Application {
}