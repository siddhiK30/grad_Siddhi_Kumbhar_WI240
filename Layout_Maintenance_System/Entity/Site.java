package Entity;



public class Site {

    private int siteId;
    private SiteType siteType;
    private int length;
    private int width;
    private SiteStatus status;
    private Integer ownerId;

    public int getSiteId() { return siteId; }
    public void setSiteId(int siteId) { this.siteId = siteId; }

    public SiteType getSiteType() { return siteType; }
    public void setSiteType(SiteType siteType) { this.siteType = siteType; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public SiteStatus getStatus() { return status; }
    public void setStatus(SiteStatus status) { this.status = status; }

    public Integer getOwnerId() { return ownerId; }
    public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }
}
