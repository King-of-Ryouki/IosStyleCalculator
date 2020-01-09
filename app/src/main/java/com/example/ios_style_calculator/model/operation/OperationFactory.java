package com.example.ios_style_calculator.model.operation;

public class OperationFactory {

    public static Operation CreateOperation(String oper_type) {

        switch (oper_type) {
            case " + ": return new Add();
            case " - ": return new Sub();
            case " * ": return new Mul();
            case " / ": return new Div();
            case " % ": return new Mod();
        }

        return null;
    }
}