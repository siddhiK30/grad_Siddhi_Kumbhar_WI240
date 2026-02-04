package Utility;

import Entity.SiteStatus;

public class MaintenanceUtil {

    public static int calculate(int length, int width, SiteStatus status) {

        int area = length * width;

        if (status == SiteStatus.OCCUPIED) {
            return area * 9;
        } else {
            return area * 6;
        }
    }
}
