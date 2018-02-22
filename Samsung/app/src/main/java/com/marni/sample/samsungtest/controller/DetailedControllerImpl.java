package com.marni.sample.samsungtest.controller;

public class DetailedControllerImpl {
    private DetailedController detailedController;

    public DetailedControllerImpl(DetailedController detailedController) {
        this.detailedController = detailedController;
        detailedController.onUIUpdate();
    }


}
