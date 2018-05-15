/*
 * The following license applies and this license notice
 * must be included in all works derived from this project.
 *
 * Copyright (c) 2017 Nishant Patel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Last modified 4/15/18 8:41 PM
 */

package com.example.android.undcricketscorecounter.ui;

import com.example.android.undcricketscorecounter.base.BasePresenter;
import com.example.android.undcricketscorecounter.model.DataManager;

public class ScorePresenter extends BasePresenter<ScoreContract.view>
        implements ScoreContract.Presenter {

    private ScoreMatrix matrix;
    private DataManager manager;

    @Override
    public void attachView(ScoreContract.view view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    ScorePresenter(ScoreMatrix matrix, DataManager manager) {
        this.matrix = matrix;
        this.manager = manager;
    }

    // Reset the game performance matrix
    @Override
    public void resetGame() {
        matrix.resetGame();
        getView().viewPerformanceMatrix(matrix);
        manager.clearPref();
    }

    @Override
    public void saveState() {
        manager.storeDataToPref("TEAM A RUN", String.valueOf(matrix.getTeamARun()));
        manager.storeDataToPref("TEAM A BALL", String.valueOf(matrix.getTeamABall()));
        manager.storeDataToPref("TEAM A WICKET", String.valueOf(matrix.getTeamAWicket()));
        manager.storeDataToPref("TEAM B RUN", String.valueOf(matrix.getTeamBRun()));
        manager.storeDataToPref("TEAM B BALL", String.valueOf(matrix.getTeamBBall()));
        manager.storeDataToPref("TEAM B WICKET", String.valueOf(matrix.getTeamBWicket()));
    }

    // Restore the score state
    public void restoreState() {
        // If data is not available in preference
        if (!manager.isDataAvailableInPref("TEAM A RUN")) {
            return;
        }
        matrix.setTeamARun(Integer.parseInt(manager.loadDataFromPref("TEAM A RUN")));
        matrix.setTeamABall(Integer.parseInt(manager.loadDataFromPref("TEAM A BALL")));
        matrix.setTeamAWicket(Integer.parseInt(manager.loadDataFromPref("TEAM A WICKET")));

        matrix.setTeamBRun(Integer.parseInt(manager.loadDataFromPref("TEAM B RUN")));
        matrix.setTeamBBall(Integer.parseInt(manager.loadDataFromPref("TEAM B BALL")));
        matrix.setTeamBWicket(Integer.parseInt(manager.loadDataFromPref("TEAM B WICKET")));
        getView().loadSavedData(matrix);
    }

    @Override
    public void calculateTeamARun(int run) {
        matrix.setTeamARun(run);
        getView().viewPerformanceMatrix(matrix);
        //manager.storeDataToPref("TEAM A RUN", String.valueOf(matrix.getTeamARun()));
    }

    @Override
    public void calculateTeamABall() {
        matrix.setTeamABall(1);
        getView().viewPerformanceMatrix(matrix);
        //manager.storeDataToPref("TEAM A BALL", String.valueOf(matrix.getTeamABall()));
    }

    @Override
    public void calculateTeamAWicket() {
        matrix.setTeamAWicket(1);
        getView().viewPerformanceMatrix(matrix);
        //manager.storeDataToPref("TEAM A WICKET", String.valueOf(matrix.getTeamAWicket()));
    }

    @Override
    public void calculateTeamBRun(int run) {
        matrix.setTeamBRun(run);
        getView().viewPerformanceMatrix(matrix);
        //manager.storeDataToPref("TEAM B RUN", String.valueOf(matrix.getTeamBRun()));
    }

    @Override
    public void calculateTeamBBall() {
        matrix.setTeamBBall(1);
        getView().viewPerformanceMatrix(matrix);
        //manager.storeDataToPref("TEAM B BALL", String.valueOf(matrix.getTeamBBall()));
    }

    @Override
    public void calculateTeamBWicket() {
        matrix.setTeamBWicket(1);
        getView().viewPerformanceMatrix(matrix);
        //manager.storeDataToPref("TEAM B WICKET", String.valueOf(matrix.getTeamBWicket()));
    }
}
