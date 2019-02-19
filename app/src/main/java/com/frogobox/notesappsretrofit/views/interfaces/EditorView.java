package com.frogobox.notesappsretrofit.views.interfaces;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * NotesAppsRetrofit
 * Copyright (C) 19/02/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public interface EditorView {

    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);

}
