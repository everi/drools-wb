/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.testscenario.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.models.testscenarios.shared.Fixture;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(GwtMockitoTestRunner.class)
public class VerifyFactColumnTest {

    private VerifyFactColumnView view;
    private VerifyFactColumn presenter;
    private ExecutionTrace executionTrace;
    private Scenario scenario;
    private boolean showResults;
    private Fixture fixture;
    private ScenarioParentWidget scenarioWidget;
    private AsyncPackageDataModelOracle oracle;

    @Mock
    private org.uberfire.mvp.Command deleteCommand;


    @Before
    public void setUp() {
        view = mock(VerifyFactColumnView.class);
        presenter = new VerifyFactColumn(view);


    }

    @Test
    public void testPresenter() {


        verify(view).setPresenter(presenter);


    }

    @Test
    public void testInit() {
        presenter.init(executionTrace,
                scenario,
                showResults,
                fixture,
                scenarioWidget,
                oracle);

        verify(view).add(executionTrace,
                scenario,
                showResults,
                fixture,
                scenarioWidget,
                oracle);
    }

    @Test
    public void testOnDeleteWhenDeleteCommandIsSet() {

        presenter.init(executionTrace,
                scenario,
                showResults,
                fixture,
                scenarioWidget,
                oracle);

        presenter.addDeleteCommand(deleteCommand);
        presenter.onDelete();


        verify(deleteCommand).execute();

    }


    @Test(expected = IllegalArgumentException.class)
    public void testOnDeleteWhenDeleteCommandIsNotSet() {


        presenter.onDelete();

    }


}