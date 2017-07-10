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

import com.google.gwt.user.client.ui.VerticalPanel;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.models.testscenarios.shared.Fixture;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.drools.workbench.models.testscenarios.shared.VerifyFact;
import org.jboss.errai.ioc.client.container.IOC;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.uberfire.mvp.Command;

public class VerifyFactsPanel extends VerticalPanel {


    private final ScenarioParentWidget parent;

    private VerifyFactColumnView view;

    public VerifyFactsPanel(FixtureList verifyFacts,
                            ExecutionTrace executionTrace,
                            final Scenario scenario,
                            ScenarioParentWidget scenarioWidget,
                            boolean showResults,
                            AsyncPackageDataModelOracle oracle) {

        this.parent = scenarioWidget;


        for (Fixture fixture : verifyFacts) {
            if (fixture instanceof VerifyFact) {


                VerifyFactColumn verifyFactColumn = IOC.getBeanManager().lookupBean(VerifyFactColumn.class).getInstance();

                verifyFactColumn.init(executionTrace,
                        scenario,
                        showResults,
                        fixture,
                        scenarioWidget,
                        oracle);

                VerifyFact verifyFact = (VerifyFact) fixture;


                verifyFactColumn.addDeleteCommand(new Command() {


                    @Override
                    public void execute() {

                        scenario.removeFixture(verifyFact);
                        parent.renderEditor();


                    }
                });

                add(verifyFactColumn);

            }
        }
    }


}
