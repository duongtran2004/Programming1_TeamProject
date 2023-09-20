public class distance_calculation {
    public static void main(String[] args) {
        double lat1 = 11.593050570216564;
        double lon1 = 102.97761914843173;
        double lat2 = 3.8017726184842022;
        double lon2 = 100.98508365208035;
        System.out.println(distance(lat1, lon1, lat2, lon2));
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // convert to kilometers
    }
}
