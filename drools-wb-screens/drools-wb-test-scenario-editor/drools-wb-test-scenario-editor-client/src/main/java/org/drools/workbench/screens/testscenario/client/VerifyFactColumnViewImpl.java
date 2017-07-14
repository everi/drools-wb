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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.models.testscenarios.shared.*;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.kie.workbench.common.widgets.client.resources.CommonAltedImages;
import org.uberfire.ext.widgets.common.client.common.ImageButton;


public class VerifyFactColumnViewImpl
        implements IsWidget,
        VerifyFactColumnView {


    private HorizontalPanel column = new HorizontalPanel();
    private VerifyFactColumn presenter;

    @Override
    public void setPresenter(VerifyFactColumn presenter) {
        this.presenter = presenter;
    }

    public void add(ExecutionTrace executionTrace,
                    final Scenario scenario,
                    boolean showResults,
                    Fixture fixture,
                    ScenarioParentWidget scenarioWidget,
                    AsyncPackageDataModelOracle oracle) {

        VerifyFact verifyFact = (VerifyFact) fixture;


        column.add(new VerifyFactWidget(verifyFact,
                scenario,
                oracle,
                (ExecutionTrace) executionTrace,
                showResults));

        column.add(new DeleteButton(verifyFact));

    }

    @Override
    public Widget asWidget() {

        return column;
    }


    class DeleteButton extends ImageButton {

        public DeleteButton(final VerifyFact verifyFact) {
            super(CommonAltedImages.INSTANCE.DeleteItemSmall(),
                    TestScenarioConstants.INSTANCE.DeleteTheExpectationForThisFact());


            addClickHandler(new ClickHandler() {

                public void onClick(ClickEvent event) {
                    if (Window.confirm(confirmRemoval())) {
                        presenter.onDelete();
                    }
                }
            });
        }
    }

    public String confirmRemoval() {
        String confirm = TestScenarioConstants.INSTANCE.AreYouSureYouWantToRemoveThisExpectation();

        return confirm;
    }

}

