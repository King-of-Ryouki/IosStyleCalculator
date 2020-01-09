package com.example.ios_style_calculator.model.calculation;

import com.example.ios_style_calculator.model.operation.OperationFactory;

public class Calculation {

    public Calculation() { InitCalculation(); }

    // * Init

    private Double num_;  // 保存的數字
    private String oper_; // 保存之前輸入的運算子

    private StringBuilder input_num_; // 保存數字
    private StringBuilder history_;   // 保存算式

    private boolean flag_input_num_;  // 輸入過數字
    private boolean flag_input_oper_; // 輸入過運算子

    public void InitCalculation() {

        num_  = Double.valueOf(0);
        oper_ = String.valueOf("");

        input_num_ = null;
        input_num_ = new StringBuilder("0");

        history_ = null;
        history_ = new StringBuilder();

        flag_input_num_  = false;
        flag_input_oper_ = false;
    }

    // * set/get

    public String get_oper_() { return oper_; }

    public StringBuilder get_input_num_() { return input_num_; }
    public StringBuilder get_history_() { return history_; }

    // * 本體

    // 輸入數字
    public void InputNum(char input_num) {

        if (input_num_.toString().equals("0")) { // 首次輸入，且輸入 0
            input_num_.setCharAt(0, input_num);
        } else if (flag_input_num_ == true) { // 有輸入過數字
            input_num_.append(input_num);
        } else if (flag_input_num_ == false) { // 沒輸入過數字
            input_num_ = null;
            input_num_ = new StringBuilder(String.valueOf(input_num));
        }

        flag_input_num_  = true;
        flag_input_oper_ = false;
    }

    // 計算
    public void Cale(String oper_type) {

        if (flag_input_num_ == true) { // 有輸入數字 // "顯示歷史紀錄 輸入數字"

            Double input_num = Double.parseDouble(input_num_.toString());

            // * 計算

            if (!oper_.equals("")) { // 非首次輸入
                num_ = OperationFactory.CreateOperation(oper_).getResult(num_, input_num);
            } else { // 首次輸入
                num_ = input_num;
            }

            // * 顯示

            // 刪除小數點
            history_.append(oper_ + RemoveLastZeroAndDot(input_num.toString()));

            input_num_ = null;
            input_num_ = new StringBuilder(RemoveLastZeroAndDot(num_.toString()));

        } else if (history_.toString().equals("")) { // 完全沒有輸入
            return;
        }

        oper_ = oper_type; // 變更運算子

        // flag
        flag_input_num_  = false;
        flag_input_oper_ = true;
    }

    // 等於
    public void Equal() {

        if (oper_.equals("")) { return; }

        if (flag_input_num_ == true) { // 有輸入數字

            Double input_num = Double.parseDouble(input_num_.toString());

            // 計算
            num_ = OperationFactory.CreateOperation(oper_).getResult(num_, input_num);

            // * 顯示

            history_.append(oper_ + RemoveLastZeroAndDot(input_num.toString()) +
                            " = " + RemoveLastZeroAndDot(num_.toString()));

            input_num_ = null;
            input_num_ = new StringBuilder(RemoveLastZeroAndDot(num_.toString()));

            oper_ = String.valueOf(""); // 清空運算子

            // flag
            flag_input_num_  = false;
            flag_input_oper_ = true;
        }
    }

    // 負號
    public void Minus() {

        Double input_num = -Double.parseDouble(input_num_.toString());

        if (input_num != 0) {
            input_num_ = null;
            input_num_ = new StringBuilder(RemoveLastZeroAndDot(input_num.toString()));
        }
    }

    // 小數點
    public void Dot() {

        if (input_num_.toString().indexOf('.') < 1) { // 沒有小數點

            if (input_num_.toString().equals("0")) {
                flag_input_num_  = true;
                flag_input_oper_ = false;
            }

            input_num_.append(".");
        }
    }

    // Input 刪除一位
    public void DeleteNum() {

        if (input_num_.length() > 1) { // 刪除一位
            input_num_.setLength(input_num_.length() - 1);
        } else if (input_num_.length() == 1) { // 只剩一位
            input_num_.setCharAt(0, '0');

            flag_input_num_  = false;
            flag_input_oper_ = true;
        }
    }

    // * Other Func

    // 刪除小數尾數的 0 & .
    private String RemoveLastZeroAndDot(String str) {

        StringBuilder sb_str = new StringBuilder(str.toString());

        while (true) {

            if (sb_str.indexOf(".") > 0) { // 有小數點

                if (sb_str.charAt(sb_str.length() - 1) == '0') { // 尾數為 0
                    sb_str.setLength(sb_str.length() - 1);       // 移除尾數
                } else if (sb_str.charAt(sb_str.length() - 1) == '.') {
                    sb_str.setLength(sb_str.length() - 1); // 移除小數點
                } else {
                    return sb_str.toString();
                }

            } else {
                return sb_str.toString();
            }
        }
    }
}