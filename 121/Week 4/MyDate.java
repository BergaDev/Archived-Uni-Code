class MyDate implements Cloneable, Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    // Constructor
    public MyDate(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getters and setters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    // Method to check if the current date is after the given date
    public boolean isExpired(MyDate expiryDate) {
        if (year > expiryDate.getYear()) {
            return true;
        } else if (year == expiryDate.getYear() && month > expiryDate.getMonth()) {
            return true;
        } else if (year == expiryDate.getYear() && month == expiryDate.getMonth() && day > expiryDate.getDay()) {
            return true;
        }
        return false;
    }

    // Override toString method to convert the entire object to string
    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }

    // Override clone method to support cloning
    @Override
    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    // Override compareTo method to compare dates
    @Override
    public int compareTo(MyDate other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }
}
