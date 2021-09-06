**This example was based on the steps described in the documentation below**

# Implementing APIs with REST Builder
**Alexandre de Souza Jr.**
**September 3rd, 2021.** https://github.com/alexandresjunior

## Create Project in Liferay Developer Studio
Create a Liferay Module Project inside a Liferay Workspace (modules directory). Make sure to use the rest-builder project template. For component class name, write HeadlessTest and for package, com.liferay.headless.test. For a headless-test sample project, there will be four submodules.

The bundles (*-api and *-impl) should have the files we are already used to: a build.gradle and a bnd.bnd. The novelty is two YAML files, a configuration file (rest-config.yaml) and the OpenAPI profile (rest-openapi.yaml).  These files must contain all the information necessary for REST Builder to generate the scaffolding code for your API.

## YAML Configuration Files
In the root of the *-impl project we have rest-config.yaml, a YAML file to specify paths and the basic configuration of our new API.

```
apiDir: "../headless-test-api/src/main/java"
apiPackagePath: "com.liferay.headless.test"
application:
    baseURI: "/headless-test"
    className: "HeadlessTestApplication"
    name: "HeadlessTest"
author: "alexandresjunior"
clientDir: "../headless-test-client/src/main/java"
testDir: "../headless-test-test/src/testIntegration/java"
```

This file specifies the path of the *-api bundle, the java package that we use across all the bundles and the information of the JAX-RS application: the path of our application, the name of the class and the JAX-RS name of our API. Also, we have the author’s name and the paths of the generated *-client and *-test bundles by rest-builder template.

**apiDir**: our Java source code folder

**apiPackagePath**: the starting Java package path where REST Builder generates code across all modules

**baseURI**: the context URL for all APIs in this project

**className**: the Java class name for the root resource class (used by JAX-RS)

**name**: the JAX-RS name of the API

The OpenAPI profile (rest-openapi.yaml) will be the source of all our APIs. In this file, we add the paths and entities of our API. Writing YAML files is tricky so it is recommended using the Swagger Editor to do it, which validates the YAML file against YAML syntax and the OpenAPI specification.
All OpenAPI profiles have three different sections: components, info, and paths. When we generate this project using the rest-builder template, rest-openapi.yaml comes only with the information block. It contains the OpenAPI version, the title and the version of our API. The version field defined here becomes part of the URL when our API paths are exposed within our Liferay instance.

```
info:
    description: "HeadlessTest REST API"
    license:
        name: "Apache 2.0"
        url: "http://www.apache.org/licenses/LICENSE-2.0.html"
    title: "HeadlessTest"
    version: v1.0
openapi: 3.0.1
```

**Note**: indentations should be spaces. The Swagger Editor helps with formatting!

Then we must include the other two sections. The components section specifies the schemas for our entities to return or accept on your APIs. REST Builder uses what we define here to create corresponding Java beans to represent these entities.

In our example, a sample schema block called Entity is defined, and it has two string fields: a name and an id. The OpenAPI specification defines many types and fields we can use in our schemas.

```
components:
  schemas:
    Entity:
      description: A sample entity
      properties:
        name:
          description: The entity name.
          type: string
        id:
          description: The entity ID.
          type: integer
      type: object
```

The last block, called paths, defines the URLs that we expose in our APIs, with the type of HTTP verbs, list of parameters, status codes, etc. The other common type is $ref, a reference type that allows us to refer to an existing type. Each path has a responses block beneath the parameters block (and within the get block) that defines at least the response for a successful call (indicated by a 200 response).

```
paths:
  "/entities/{entityId}":
    get:
      parameters:
        - in: path
          name: entityId
          required: true
          schema:
            type: integer
      responses:
        200:
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Entity"
          description: ""
      tags: ["Entity"]
```

The pattern /entities/{entityId} follows a common pattern in REST APIs. This is the endpoint that retrieves one element by its id. For every path, it is mandatory to add a tag that points to an existing schema to indicate where to generate your code. REST Builder creates a method inside the class [TAG]ResourceImpl.java.

## Generate JAX-RS Resources and GraphQL Endpoint

Once we have our OpenAPI configuration and profile defined, it’s time to generate your scaffolding for REST and GraphQL. Inside the *-impl module folder, run blade gw buildREST command, or just simply run the respective gradle task.
If everything is indented properly and the OpenAPI profile validates, REST Builder generates our JAX-RS resources and the GraphQL endpoint. Make sure to refresh your gradle project to be able to see the generated packages and classes. The *-api submodule contains the interfaces for our resources and the POJOs of our schemas, whereas *-impl contains our implementation and the JAX-RS application.
Here is a complete example that defines all CRUD operations in OpenAPI.
```
components:
  schemas:
    Entity:
      description: A sample entity
      properties:
        name:
          description: The entity name.
          type: string
        id:
          description: The entity ID.
          type: integer
      type: object
info:
    description: "HeadlessTest REST API"
    license:
        name: "Apache 2.0"
        url: "http://www.apache.org/licenses/LICENSE-2.0.html"
    title: "HeadlessTest"
    version: v1.0
openapi: 3.0.1
paths:
  "/entities":
    get:
      responses:
        200:
          content:
            application/json:
              schema:
                items:
                    $ref: "#/components/schemas/Entity"
                type: array
          description: ""
      tags: ["Entity"]
    post:
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Entity"
      responses:
        200:
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Entity"
          description: ""
      tags: ["Entity"]
  "/entities/{entityId}":
    get:
      parameters:
        - in: path
          name: entityId
          required: true
          schema:
            type: integer
      responses:
        200:
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Entity"
          description: ""
      tags: ["Entity"]
    put:
      parameters:
        - in: path
          name: entityId
          required: true
          schema:
            type: integer
      requestBody:
          content:
              application/json:
                  schema:
                      $ref: "#/components/schemas/Entity"
      responses:
          200:
              content:
                  application/json:
                      schema:
                          $ref: "#/components/schemas/Entity"
              description: ""
      tags: ["Entity"]
    delete:
      parameters:
        - in: path
          name: entityId
          required: true
          schema:
              type: integer
      responses:
        204:
            content:
                application/json: {}
            description: ""
      tags: ["Entity"]
```      
### Note: we can disable GraphQL generation by adding generateGraphQL: false to our rest-config.yaml (generateREST controls the generation of the REST endpoints).

# Implement the Business Logic
As mentioned previously, the *-api submodule contains the interfaces for our resources and the POJOs of our schemas. The generated EntityResource is an interface which contains generated methods we have defined in the OpenAPI profile. In *-impl submodule, REST builder generates EntityResourceImpl, which is located beside the base class BaseEntityResourceImpl. The EntityResourceImpl is the one where we implement our business logic for each service with new methods, by overriding the base class implementation and returning our code.
Here is a prototype implementation storing entities in a Map:
```
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
```

For the collection, you return a Page object based on a list but there are also utility methods that return the pagination information, like:
Page.of(list, pagination, totalCount)
Note: don’t touch the interfaces or the base classes (those are regenerated every time you run REST Builder). Like Service Builder, we only have to maintain the implementation classes, and if we change the API, by adding parameters or other paths, we must run REST Builder again and the interfaces will be updated. In addition, our business logic could call other REST APIs, use Service Builder or another persistence mechanism.
 
## Deploy
After implementing the business logic for each service, we are able to deploy our modules. First run `blade gw initBundle` in the project root directory, then `blade server start -d -t` (-d -t are optional). After this steps, run `blade gw deploy` with local environment running, and then our APIs will be available at this URL:

http://localhost:8080/o/headless-test/v1.0/

Or more generally:

http://localhost:8080/o/[application class name]/[OpenAPI version]/

We can also execute jaxrs:check in the OSGi console (GoGo Shell) to see all the JAX-RS endpoints.

When everything is ready, we might want to consider publishing our HeadlessTest API to Swaggerhub so others can consume it. We can use the following URL pattern for that:
http://localhost:8080/o/[application name]/[application version]/openapi.yaml
The URL for the our example above, therefore, would be:
http://localhost:8080/o/headless-test/v1.0/openapi.yaml
This URL has the content of rest-openapi.yaml plus the classes that REST Builder generated for us. [CHECK THIS LATER]
 
**CHECK THIS LATER**: set false to generateBatch endpoints? (Reference n. 3)
generateBatch (boolean):
Generate batch endpoints. Defaults to true
 
**Check also**:
* https://help.liferay.com/hc/en-us/articles/360039425691-Managing-Collections-in-REST-Builder
* https://help.liferay.com/hc/en-us/articles/360039425711-REST-Builder-Scaffolding
* https://help.liferay.com/hc/en-us/articles/360039425731-Support-for-oneOf-anyOf-and-allOf
* https://help.liferay.com/hc/en-us/articles/360039425751-REST-Builder-Liferay-Conventions
 
**References**:
* Introduction to REST Builder – Liferay Help Center https://help.liferay.com/hc/en-us/articles/360028708852-Introduction-to-REST-Builder
* REST Builder – Liferay Help Center - https://help.liferay.com/hc/en-us/articles/360036343312-REST-Builder
* REST Builder: Do you know the hidden configuration parameters? - https://liferay.dev/blogs/-/blogs/rest-builder-do-you-know-the-hidden-configuration-parameters-
* Implementing a New API with REST Builder - https://learn.liferay.com/dxp/latest/en/headless-delivery/producing-apis-with-rest-builder/implementing-a-new-api-with-rest-builder.html
