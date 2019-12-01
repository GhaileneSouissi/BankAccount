package models;

import java.time.LocalDateTime;

/***
 * Operation class, it contains all information about an operation
 */
public class Operation {
    //operation time
    private LocalDateTime operationDate;
    //operation type
    private Types type;
    //operation amount
    private int amount;


    public Operation(LocalDateTime operationDate, Types type, int amount) {
        this.operationDate = operationDate;
        this.type = type;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "operationDate =" + operationDate +
                ", type =" + type +
                ", amount =" + amount;
    }
}
