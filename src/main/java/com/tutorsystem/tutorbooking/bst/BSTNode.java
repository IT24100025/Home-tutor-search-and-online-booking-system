package com.tutorsystem.tutorbooking.bst;

import com.tutorsystem.tutorbooking.model.Tutor;

public class BSTNode {
    Tutor tutor;
    BSTNode left, right;

    public BSTNode(Tutor tutor) {
        this.tutor = tutor;
        left = right = null;
    }
}