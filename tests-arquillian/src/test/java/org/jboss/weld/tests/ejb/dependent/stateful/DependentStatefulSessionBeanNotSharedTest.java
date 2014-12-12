/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.tests.ejb.dependent.stateful;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.config.ConfigurationKey;
import org.jboss.weld.tests.category.Integration;
import org.jboss.weld.tests.util.PropertiesBuilder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

/**
 * See http://lists.jboss.org/pipermail/cdi-dev/2014-December/005800.html for details
 * @author Steve Millidge
 *
 */
@RunWith(Arquillian.class)
@Category(Integration.class)
public class DependentStatefulSessionBeanNotSharedTest {

    @Inject
    private Injection injection;

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create(WebArchive.class).addPackage(DependentStatefulSessionBeanNotSharedTest.class.getPackage()).addAsResource(
                PropertiesBuilder.newBuilder().set(ConfigurationKey.INJECTABLE_REFERENCE_OPTIMIZATION.get(), "false")
                .build(), "weld.properties");
    }

    @Test
    public void testDependentStatefulSessionBeanNotSharedBetweenInjectionPoints() {
        Assert.assertEquals("init", injection.invokeStatefulService());
    }
}