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
 * Last modified 4/15/18 8:39 PM
 */
package com.example.android.undcricketscorecounter.ui.score;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.android.undcricketscorecounter.R;
import com.example.android.undcricketscorecounter.databinding.ActivityMainBinding;
import com.example.android.undcricketscorecounter.model.DataManager;
import com.example.android.undcricketscorecounter.ui.adapter.OverDetailAdapter;
import com.example.android.undcricketscorecounter.ui.model.ScoreMatrix;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ScoreContract.view {
    private ActivityMainBinding binding;
    private ScorePresenter presenter;
    private OverDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setScoreMatrix(new ScoreMatrix());

        presenter = new ScorePresenter(new ScoreMatrix(), DataManager.getInstance(this));
        presenter.attachView(this);
        binding.setPresenter(presenter);

        binding.overDetail.rvOverDetail.setHasFixedSize(true);
        adapter = new OverDetailAdapter(null);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayout.HORIZONTAL, true);
        binding.overDetail.rvOverDetail.setLayoutManager(layoutManager);
        binding.overDetail.rvOverDetail.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.restoreState();
    }

    @Override
    public void viewPerformanceMatrix(ScoreMatrix matrix) {
        binding.setScoreMatrix(matrix);
    }

    /**
     * Load saved game matrix
     *
     * @param matrix performance matrix
     */
    @Override
    public void loadSavedData(final ScoreMatrix matrix) {
        binding.setScoreMatrix(matrix);
    }

    /**
     * Implement this method to update ball view
     *
     * @param ballList detail ball list
     */
    @Override
    public void updateBallView(List<String> ballList) {
        adapter.swapData(ballList);
    }

    /**
     * Display alert dialog for saving data when user presses back button
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.alertDialog)
                        .setTitle(R.string.save_changes)
                        .setMessage(getString(R.string.save_dialog_message))
                        .setPositiveButton(getString(R.string.save_dialog_positive_title)
                                , new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        presenter.saveState();
                                        finish();
                                    }
                                })
                        .setNegativeButton(getString(R.string.save_dialog_negative_button)
                                , new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
        builder.show();
    }
}