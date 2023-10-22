public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isExpired(MyDate expiryDate) {
        if (this.year < expiryDate.getYear()) {
            return false;
        } else if (this.year == expiryDate.getYear()) {
            if (this.month < expiryDate.getMonth()) {
                return false;
            } else if (this.month == expiryDate.getMonth()) {
                return this.day > expiryDate.getDay();
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
