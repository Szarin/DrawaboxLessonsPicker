package src.java;

public class Exercice {
    public String name;
    private String link;
    private boolean isLinked = false;

    public Exercice(String name) {
        this.name = name;
    }

    public Exercice(String name, String link) {
        if (link.equals("") || link == null) this.name = name;
        else {
            this.name = name;
            this.link = link;
            this.isLinked = true;
        }
    }

    public String getLink() { 
        if (this.link == null || this.link == "")
            return name + " doesn't have any link";
        return this.link;
    }
    public void setLink(String link) {
        if (this.link == null || this.link == "") return;
        this.isLinked = true;
        this.link = link;
    }

    public boolean isLinked() { return this.isLinked; }
}
