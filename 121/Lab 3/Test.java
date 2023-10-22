public class Test {

    public static void main(String[] args) {
        final double flatRate = 100;

        ThirdPartyPolicy policy1 = new ThirdPartyPolicy("John Doe", 1, new Car("Honda Civic", CarType.SED, 2022, 20000), 0, "No comments");
        ComprehensivePolicy policy2 = new ComprehensivePolicy("Jane Doe", 2, new Car("Toyota Camry", CarType.LUX, 2021, 30000), 1, 1);
        ComprehensivePolicy policy3 = new ComprehensivePolicy("Mike Smith", 3, new Car("BMW 3 Series", CarType.SED, 2020, 40000), 2, 2);

        List<InsurancePolicy> policies = Arrays.asList(policy1, policy2, policy3);

        System.out.println("Printing policies using print method:");
        for (InsurancePolicy policy : policies) {
            policy.print();
        }

        System.out.println("\nPrinting policies using toString method:");
        for (InsurancePolicy policy : policies) {
            System.out.println(policy);
        }

        double totalPremium = 0;
        for (InsurancePolicy policy : policies) {
            totalPremium += policy.calcPayment();
        }

        System.out.println("\nThe total premium is " + totalPremium);
    }
}
