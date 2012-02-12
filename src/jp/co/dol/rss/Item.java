package jp.co.dol.rss;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	public Item() {
		
	}

    // ˆÄŒNo.
    private String pjNo;
    public String getPjNo() { return pjNo; }
    public void setPjNo(String pjNo) { this.pjNo = pjNo; }
    
    // No.
    private String no;
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }
    
    // ”­¶“ú
    private String postDate;
    public String getPostDate() { return postDate; }
    public void setPostDate(String postDate) { this.postDate = postDate; }
    
    // ŠT—v
    private String title;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    // ‘Î‰‰Â”Û
    private String supportKbn;
    public String getSupportKbn() { return supportKbn; }
    public void setSupportKbn(String supportKbn) { this.supportKbn = supportKbn; }
    
    // —Dæ“x
    private String priorityLv;
    public String getPriorityLv() { return priorityLv; }
    public void setPriorityLv(String priorityLv) { this.priorityLv = priorityLv; }
    
    // d—v“x
    private String importantLv;
    public String getImportantLv() { return importantLv; }
    public void setImportantLv(String importantLv) { this.importantLv = importantLv; }
    
    // ‘Î‰ŠJn“ú
    private String startDate;
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    
    // ‘Î‰I—¹“ú
    private String endDate;
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    
    // ƒŠƒŠ[ƒX“ú
    private String releaseDate;
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    // w“E’S“–
    private String discoveryEmp;
    public String getDiscoveryEmp() { return discoveryEmp; }
    public void setDiscoveryEmp(String discoveryEmp) { this.discoveryEmp = discoveryEmp; }
    
    // ‘Î‰’S“–
    private String supportEmp;
    public String getSupportEmp() { return supportEmp; }
    public void setSupportEmp(String supportEmp) { this.supportEmp = supportEmp; }

    // áŠQ”­¶‰ÓŠ
    private String troubleSpots;
    public String getTroubleSpots() { return troubleSpots; }
    public void setTroubleSpots(String troubleSpots){ this.troubleSpots = troubleSpots; }
    
    // “à—e
    private String failureBody;
    public String getFailureBody() { return failureBody; }
    public void setFailureBody(String failureBody){ this.failureBody = failureBody; }
    
    // áŠQŒ´ˆö
    private String troubleCause;
    public String getTroubleCause() { return troubleCause; }
    public void setTroubleCause(String troubleCause){ this.troubleCause = troubleCause; }
    
    // áŠQŒ´ˆö‹æ•ª
    private String troubleCauseKbn;
    public String getTroubleCauseKbn() { return troubleCauseKbn; }
    public void setTroubleCauseKbn(String troubleCauseKbn){ this.troubleCauseKbn = troubleCauseKbn; }

    // C³“à—e
    private String repairBody;
    public String getRepairBody() { return repairBody; }
    public void setRepairBody(String repairBody){ this.repairBody = repairBody; }

    // ‘Î‰Œ‹‰Ê
    private String results;
    public String getResults() { return results; }
    public void setResults(String results){ this.results = results; }
    
    // C³‘Y
    private String repairSrc;
    public String getRepairSrc() { return repairSrc; }
    public void setRepairSrc(String repairSrc){ this.repairSrc = repairSrc; }
    
    // ”õl
    private String remarks;
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks){ this.remarks = remarks; }
    
    // ƒuƒ‰ƒEƒU
    private String browser;
    public String getBrowser() { return browser; }
    public void setBrowser(String browser){ this.browser = browser; }
}
