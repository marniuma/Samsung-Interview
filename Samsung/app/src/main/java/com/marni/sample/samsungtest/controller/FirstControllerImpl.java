package com.marni.sample.samsungtest.controller;


import com.marni.sample.samsungtest.Network.NetworkInterface;
import com.marni.sample.samsungtest.Network.NetworkService;


public class FirstControllerImpl implements NetworkInterface {

    private FirstController firstController;

    public FirstControllerImpl(FirstController firstController) {
        this.firstController = firstController;
        NetworkService ns = new NetworkService(this);
    }

    @Override
    public void onNetworkSuccess() {
        firstController.onUIUpdate();
    }

    @Override
    public void onNetworkFailure() {

    }
}
