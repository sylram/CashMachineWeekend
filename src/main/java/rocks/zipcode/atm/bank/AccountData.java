package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    private final Double balance;

    AccountData(int id, String name, String email, Double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        if(balance<=0.0) {
            return "Insufficient balance";
        }else {
            return "Account id: " + id;
        }
    }
    public String nameInfo() {
        return
                "Name: " + name;

    }
    public String emailInfo() {
        return
                "Email: " + email;
//                "Balance: " + balance; + '\n' +
    }
    public String balance() {

        return
                "Balance: " + balance;

    }
}
