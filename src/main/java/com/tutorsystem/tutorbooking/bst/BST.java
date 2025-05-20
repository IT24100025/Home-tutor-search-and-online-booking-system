package com.tutorsystem.tutorbooking.bst;

import com.tutorsystem.tutorbooking.model.Tutor;

public class BST {
    private BSTNode root;

    public BST() {
        root = null;
    }

    // Insert a new tutor
    public void insert(Tutor tutor) {
        root = insertRec(root, tutor);
    }

    private BSTNode insertRec(BSTNode root, Tutor tutor) {
        if (root == null) {
            root = new BSTNode(tutor);
            return root;
        }

        if (tutor.getId().compareTo(root.tutor.getId()) < 0)
            root.left = insertRec(root.left, tutor);
        else if (tutor.getId().compareTo(root.tutor.getId()) > 0)
            root.right = insertRec(root.right, tutor);

        return root;
    }

    // Search by ID
    public Tutor searchById(String id) {
        return searchByIdRec(root, id);
    }

    private Tutor searchByIdRec(BSTNode root, String id) {
        if (root == null) return null;

        if (id.equals(root.tutor.getId()))
            return root.tutor;
        else if (id.compareTo(root.tutor.getId()) < 0)
            return searchByIdRec(root.left, id);
        else
            return searchByIdRec(root.right, id);
    }

    // Search by email
    public Tutor searchByEmail(String email) {
        return searchByEmailRec(root, email);
    }

    private Tutor searchByEmailRec(BSTNode root, String email) {
        if (root == null) return null;

        if (email.equalsIgnoreCase(root.tutor.getEmail()))
            return root.tutor;

        Tutor leftResult = searchByEmailRec(root.left, email);
        if (leftResult != null) return leftResult;

        return searchByEmailRec(root.right, email);
    }

    // In-order traversal to get all tutors
    public void inOrder(java.util.List<Tutor> list) {
        inOrderRec(root, list);
    }

    private void inOrderRec(BSTNode root, java.util.List<Tutor> list) {
        if (root != null) {
            inOrderRec(root.left, list);
            list.add(root.tutor);
            inOrderRec(root.right, list);
        }
    }
}