package com.example.foodycookbook.BackendRequest;

public interface GenericJobCompletionWithFailureListener {
    default void onJobFailed(Object obj){

    }
    default void onJobSuccess(Object obj){

    };
}


