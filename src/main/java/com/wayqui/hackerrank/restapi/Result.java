package com.wayqui.hackerrank.restapi;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class MatchResult {
    private String competition;
    private Integer year;
    private String round;
    private String team1;
    private String team2;
    private String team1goals;
    private String team2goals;

    public MatchResult() {

    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1goals() {
        return team1goals;
    }

    public void setTeam1goals(String team1goals) {
        this.team1goals = team1goals;
    }

    public String getTeam2goals() {
        return team2goals;
    }

    public void setTeam2goals(String team2goals) {
        this.team2goals = team2goals;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "competition='" + competition + '\'' +
                ", year=" + year +
                ", round='" + round + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", team1goals='" + team1goals + '\'' +
                ", team2goals='" + team2goals + '\'' +
                '}';
    }
}

class Response {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<MatchResult> data;

    public Response() {

    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<MatchResult> getData() {
        return data;
    }

    public void setData(List<MatchResult> data) {
        this.data = data;
    }
}

class Result {

    /*
     * Complete the 'getTotalGoals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING team
     *  2. INTEGER year
     */

    public static int getTotalGoals(String team, int year) throws IOException {

        List<MatchResult> resultsAsHome = callService(team, year, "team1");
        List<MatchResult> resultsAsVisitor = callService(team, year, "team2");

        int scoresAsHome = resultsAsHome.stream().mapToInt(matchResult ->
                new Integer(matchResult.getTeam1goals())).sum();

        int scoresAsVisitor = resultsAsVisitor.stream().mapToInt(matchResult ->
                new Integer(matchResult.getTeam2goals())).sum();

        return scoresAsHome + scoresAsVisitor;
    }

    private static List<MatchResult> callService(String team, int year, String situation) throws IOException {
        URL url = new URL("https://jsonmock.hackerrank.com/api/football_matches?year="+year+"&"+situation+"="+team);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);


        }
        conn.disconnect();

        return new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        Result.getTotalGoals("Barcelona", 2011);
    }

}