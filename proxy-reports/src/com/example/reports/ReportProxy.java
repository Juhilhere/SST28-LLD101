package com.example.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport = null;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[access denied] " + user.getName()
                    + " cannot access report " + reportId
                    + " (classification=" + classification + ")");
            return;
        }
        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }
        realReport.display(user);
    }
}
