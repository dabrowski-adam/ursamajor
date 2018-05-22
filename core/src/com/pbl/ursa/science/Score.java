package com.pbl.ursa.science;

class Score { // ADD CONVERSION TO CURRENCY
    int value;

    Score() {
        value = 0;
    }

    int getScore(){ return value; }

    void IncreaseValue(int byNumber) {
        value += byNumber;
    }

    int convertToCurrency() { //what the conversion rate should be?
        return value;
    }
}
