/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.models.testscenarios.shared.*;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.uberfire.commons.validation.PortablePreconditions;

import javax.inject.Inject;


public class VerifyFactColumn
        implements IsWidget {

    private VerifyFactColumnView view;

    private org.uberfire.mvp.Command deleteCommand;


    @Inject
    public VerifyFactColumn(final VerifyFactColumnView view) {
        this.view = view;
        view.add(this);


    }

    public VerifyFactColumn() {

    }


    void init(ExecutionTrace executionTrace,
              Scenario scenario,
              boolean showResults,
              Fixture fixture,
              ScenarioParentWidget scenarioWidget,
              AsyncPackageDataModelOracle oracle) {


        view.add(executionTrace,
                scenario,
                showResults,
                fixture,
                scenarioWidget,
                oracle);

    }


    @Override
    public Widget asWidget() {
        return view.asWidget();
    }


    public void onDelete() {

        PortablePreconditions.checkNotNull("deletecommand", deleteCommand);
        deleteCommand.execute();


    }

    public void addDeleteCommand(org.uberfire.mvp.Command deleteCommand) {

        this.deleteCommand = deleteCommand;
    }
}




