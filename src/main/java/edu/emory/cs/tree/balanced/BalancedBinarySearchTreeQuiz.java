package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.BinaryNode;

/** @author Jinho D. Choi */
public class BalancedBinarySearchTreeQuiz<T extends Comparable<T>> extends AbstractBalancedBinarySearchTree<T, BinaryNode<T>> {
    @Override
    public BinaryNode<T> createNode(T key) {
        return new BinaryNode<>(key);
    }

    @Override
    protected void balance(BinaryNode<T> node) {
        BinaryNode<T> parent = node.getParent();
        BinaryNode<T> grandParent = node.getGrandParent();
        BinaryNode<T> uncle = node.getUncle();

        if (!parent.hasBothChildren() && grandParent.isRightChild(parent) && !uncle.hasBothChildren()) {
            if (node.isLeftChild(parent)) {
                this.rotateRight(parent);
            }

            this.rotateLeft(grandParent);

            if (uncle.hasRightChild()) {
                this.rotateLeft(uncle);
            }

            this.rotateRight(grandParent);
        }
    }
}